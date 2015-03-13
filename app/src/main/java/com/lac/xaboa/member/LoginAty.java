// +----------------------------------------------------------------------
// | FileName:   ${file_name}  
// +----------------------------------------------------------------------
// | CreateTime: ${date}  ${time}
// +----------------------------------------------------------------------
// | Author:     xab(admin@xueyong.net.cn)
// +----------------------------------------------------------------------
// | Description:
// +----------------------------------------------------------------------


package com.lac.xaboa.member;

import android.app.Activity;
import android.app.Notification;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.gc.materialdesign.views.CheckBox;
import com.gc.materialdesign.widgets.SnackBar;
import com.google.gson.Gson;
import com.lac.xab.ui.NumberProgressBar;
import com.lac.xab.ui.progressbtn.CircularProgressButton;
import com.lac.xab.utils.L;
import com.lac.xab.utils.PreferenceUtils;
import com.lac.xaboa.R;
import com.lac.xaboa.base.A;
import com.lac.xaboa.base.BaseActivity;
import com.lac.xaboa.bean.AppInfo;
import com.lac.xaboa.db.model.Member;
import com.lac.xaboa.db.model.Notice;
import com.lac.xaboa.db.service.MemberService;
import com.lac.xaboa.db.service.NoticeService;
import com.lac.xaboa.main.aty.MainAty;
import com.lac.xaboa.main.task.NoticeTask;
import com.lac.xaboa.member.task.LoginTask;
import com.lac.xaboa.utils.K;
import com.lac.xaboa.utils.S;
import com.lac.xaboa.utils.V;
import com.tencent.bugly.crashreport.CrashReport;

import org.apache.commons.lang3.StringUtils;

import java.util.List;
import java.util.Set;

import javax.inject.Inject;

import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.OnClick;
import cn.jpush.android.api.BasicPushNotificationBuilder;
import cn.jpush.android.api.CustomPushNotificationBuilder;
import cn.jpush.android.api.JPushInterface;
import cn.jpush.android.api.TagAliasCallback;

public class LoginAty extends BaseActivity {

    Activity activity;
    Context context;
    @InjectView(R.id.ev_login_no)
    EditText ev_login_no;
    @InjectView(R.id.cb_rember_pw)
    CheckBox cb_rember_pw;
    @InjectView(R.id.ev_login_pw)
    EditText ev_login_pw;
    @InjectView(R.id.npb_login)
    NumberProgressBar npb_login;
    @InjectView(R.id.btn_login)
    CircularProgressButton btn_login;
    @InjectView(R.id.tv_login_status)
    TextView tv_login_status;
    @InjectView(R.id.tv_forget_pw)
    TextView tv_forget_pw;

    @Inject
    PreferenceUtils preference ;

    LoginTask loginTask;
    boolean checkResult = false;
    String username;
    String password;
    boolean isremenber = false;

    int syncount = 0;
    int syncnum = 5;
    @Inject
    AppInfo appInfo ;
    @Inject
    MemberService memberService;
    @Inject
    NoticeService noticeService;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.aty_login);
        activity = this;
        context = this;

        ButterKnife.inject(this);
        A.inject(this);
        initViewAndData();
    }

    public void initViewAndData(){
        tv_login_status.setVisibility(View.GONE);
        npb_login.setVisibility(View.GONE);
        cb_rember_pw.setEnabled(true);
        isremenber = preference.getBoolean(K.LOGIN_ISREMENBER);
        cb_rember_pw.set(true);
        if(cb_rember_pw.isCheck()){
            username = preference.getString(K.MEMBER_NO);
            password = preference.getString(K.MEMBER_PW);
        }
        ev_login_no.setText(username);
        ev_login_pw.setText(password);

        npb_login.setMax(syncnum);
        npb_login.setProgress(syncount);
    }
    @OnClick({R.id.btn_login,R.id.tv_forget_pw})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.btn_login:
                syncount = 0;
                btn_login.setIndeterminateProgressMode(true);
                if(!S.isConnNet){
                    SnackBar.init(activity,R.string.error_net);
                    btn_login.setProgress(V.LOGINDFT);
                    break;
                }
                checkMemberInfo();
                if (checkResult) {
                    loginTask = new LoginTask(loginHandler,memberService);
                    loginTask.execute(username, password);
                    btn_login.setProgress(V.LOGINING);
                }
                break;
            case R.id.tv_forget_pw:
                updateNpb();
                break;
            default:
                break;
        }
    }

    public void syncData(){
        npb_login.setVisibility(View.VISIBLE);
        tv_login_status.setVisibility(View.VISIBLE);
        NoticeTask nt =new NoticeTask();
        nt.getList(syncDataHandler,noticeService);
        JPushInterface.setAlias(context,S.member_id, new TagAliasCallback(){
            @Override
            public void gotResult(int i, String s, Set<String> strings) {}
        });
    }

    Handler syncDataHandler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what){
                case V.ERROR_RANDID:
                    L.i(V.ERROR_RANDID);
                    finish();
                    break;
                case V.SYNC_NOTICE:
                    tv_login_status.setText(R.string.sync_notice);
                    updateNpb();
                    break;
                default:
                    break;
            }

        }
    };

    private void updateNpb(){
        syncount++ ;
        npb_login.setProgress(syncount);
        if(syncount == syncnum){
            startActivity(MainAty.class);
        }
    }

    Handler loginHandler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what){
                case V.LOGIN_EMPTY_EMPNO:
                    break;
                case V.LOGIN_EMPTY_EMPPW:
                    break;
                case V.LOGIN_ERROR_UPE:
                    SnackBar.init(activity,getString(R.string.login_error_upe));
                    break;
                case V.LOGIN_ERROR_UNDEFINED:
                    SnackBar.init(activity,getString(R.string.login_error_undefined));
                    break;
                case V.LOGIN_SUCCESS:

                    if(isremenber) {
                        preference.saveString(K.MEMBER_NO,username);
                        preference.saveString(K.MEMBER_PW,password);
                    }else{
                        preference.saveString(K.MEMBER_NO,"");
                        preference.saveString(K.MEMBER_PW,"");
                    }
                    btn_login.setProgress(V.LOGINDFT);
                    syncData();
                    break;
                default:
                    break;
            }
        }
    };
    private void checkMemberInfo() {
        username = ev_login_no.getText().toString();
        password = ev_login_pw.getText().toString();
        isremenber = cb_rember_pw.isCheck();
        preference.saveBoolean(K.LOGIN_ISREMENBER,isremenber);
        if (StringUtils.isEmpty(username)) {
            checkResult = false;
            SnackBar.init(this, getString(R.string.emp_no_not_null));
        } else if (StringUtils.isEmpty(password)) {
            checkResult = false;
            SnackBar.init(this, getString(R.string.emp_pw_not_null));
        } else {
            checkResult = true;
        }
    }
    private void startActivity(Class cls){
        Intent intent =new Intent();
        intent.setClass(context, cls);
        startActivity(intent);
        finish();
    }
}
