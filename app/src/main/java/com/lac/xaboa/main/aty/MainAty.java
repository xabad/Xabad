// +----------------------------------------------------------------------
// | FileName:   ${file_name}  
// +----------------------------------------------------------------------
// | CreateTime: ${date}  ${time}
// +----------------------------------------------------------------------
// | Author:     xab(admin@xueyong.net.cn)
// +----------------------------------------------------------------------
// | Description:
// +----------------------------------------------------------------------


package com.lac.xaboa.main.aty;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.gc.materialdesign.views.ButtonFloat;
import com.gc.materialdesign.views.LayoutRipple;
import com.gc.materialdesign.widgets.SnackBar;
import com.jeremyfeinstein.slidingmenu.lib.SlidingMenu;
import com.lac.xab.utils.DialogUtils;
import com.lac.xab.utils.L;
import com.lac.xab.utils.PreferenceUtils;
import com.lac.xaboa.R;
import com.lac.xaboa.base.A;
import com.lac.xaboa.base.BaseActivity;
import com.lac.xaboa.base.BusProvider;
import com.lac.xaboa.db.model.Member;
import com.lac.xaboa.db.model.Notice;
import com.lac.xaboa.db.service.MemberService;
import com.lac.xaboa.db.service.NoticeService;
import com.lac.xaboa.jpush.PushModel;
import com.lac.xaboa.main.adp.MainTaskNoticeADP;
import com.lac.xaboa.main.task.NoticeTask;
import com.lac.xaboa.member.LoginAty;
import com.lac.xaboa.utils.K;
import com.lac.xaboa.utils.S;
import com.lac.xaboa.utils.V;
import com.squareup.otto.Subscribe;
import com.tencent.bugly.crashreport.CrashReport;


import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.OnClick;
import xaboa.lac.com.mylibrary.U;

public class MainAty extends BaseActivity implements View.OnClickListener{

    View leftmenu;
    LayoutInflater inflater ;
    @InjectView(R.id.buttonFloat)
    ButtonFloat buttonFloat;
    @InjectView(R.id.lv_main_task_notice)
    ListView lv_main_task_notice;
    @InjectView(R.id.ivTitleBtnLeft)
    ImageView ivTitleBtnLeft;
    LayoutRipple lr_logout;
    TextView tv_member_name;
    SlidingMenu menu ;
    List<Notice> noticeList = new ArrayList<Notice>();
    @Inject
    MemberService memberService;
    @Inject
    NoticeService noticeService;
    @Inject
    PreferenceUtils preference ;
    Member member ;
    MainTaskNoticeADP noticeADP =null;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.aty_main);
        ButterKnife.inject(this);
        A.inject(this);
        inflater = this.getLayoutInflater();
        leftmenu =  inflater.inflate(R.layout.inc_leftmenu, null);
        L.i(S.member_id);
        member = memberService.displayById(S.member_id);
        initSlideMenu();
        initTaskNotice();
        A.resumePush();
    }

    private void initTaskNotice() {
        noticeADP = new MainTaskNoticeADP(this,noticeList);
        lv_main_task_notice.setAdapter(noticeADP);

        refrenceNotice();
    }
    private void refrenceNotice(){
        noticeList.clear();
        List<Notice> nList = noticeService.getList(3);
        for(Notice notice:nList){
            noticeList.add(notice);
        }
        noticeADP.notifyDataSetChanged();
    }
    Handler handler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what){
                case V.ERROR_RANDID:
                    L.i(V.ERROR_RANDID);
                    finish();
                    break;
                case V.NOTICE_LIST_NODATA:
                    break;
                case V.NOTICE_LIST_HAVADATA:
                    List<Notice> nList= (List<Notice>)msg.obj;
                    noticeService.syncList(nList);
                    refrenceNotice();
                    break;

            }
        }
    };
    private void initSlideMenu() {
        DisplayMetrics dm = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(dm);
        menu = new SlidingMenu(this);
        menu.setMenu(leftmenu);
        menu.setMode(SlidingMenu.LEFT);
        menu.setTouchModeAbove(SlidingMenu.TOUCHMODE_FULLSCREEN);
        menu.setShadowWidthRes(R.dimen.shadow_width);
        menu.setShadowDrawable(R.drawable.shadow);
        menu.setBehindOffsetRes(R.dimen.slidingmenu_offset);
        menu.setBehindOffset(dm.widthPixels / 3);
        menu.setFadeDegree(0.35f);
        menu.attachToActivity(this,SlidingMenu.LEFT);

        lr_logout = (LayoutRipple)leftmenu.findViewById(R.id.lr_logout);
        lr_logout.setOnClickListener(this);
        tv_member_name = (TextView)leftmenu.findViewById(R.id.tv_member_name);
        tv_member_name.setText(member.getName());

    }
    @OnClick({
            R.id.ivTitleBtnRight,R.id.buttonFloat,R.id.ivTitleBtnLeft
    })
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.buttonFloat:
                break;
            case R.id.lr_logout:
                S.member_emp_no = "";
                S.member_emp_randid="";
                Intent intent = new Intent();
                intent.setClass(this, LoginAty.class);
                startActivity(intent);
                finish();
                break;
            case R.id.ivTitleBtnLeft:
                menu.toggle();
                break;
            default:
                break;
        }
    }



    @Override
    public void onClick(View v) {
        onViewClicked(v);
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    @Override
    protected void onPause() {
        super.onPause();
    }
}
