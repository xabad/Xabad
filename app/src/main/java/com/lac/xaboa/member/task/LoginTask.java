// +----------------------------------------------------------------------
// | FileName:   Xaboa  
// +----------------------------------------------------------------------
// | CreateTime: 2015/1/29  13:54
// +----------------------------------------------------------------------
// | Author:     xab(admin@xueyong.net.cn)
// +----------------------------------------------------------------------
// | Description:
// +----------------------------------------------------------------------


package com.lac.xaboa.member.task;

import android.os.AsyncTask;
import android.os.Handler;
import android.os.Message;

import com.lac.xab.utils.L;
import com.lac.xaboa.base.OaITF;
import com.lac.xaboa.db.model.Member;
import com.lac.xaboa.db.service.MemberService;
import com.lac.xaboa.utils.E;
import com.lac.xaboa.utils.K;
import com.lac.xaboa.utils.RTF;
import com.lac.xaboa.utils.S;
import com.lac.xaboa.utils.V;

import org.apache.commons.lang3.StringUtils;

import javax.inject.Inject;


public class LoginTask extends AsyncTask<String,Integer,Member> {

    Handler loginHandler;
    MemberService memberService;
    public LoginTask( Handler loginHandler, MemberService memberService) {
        this.loginHandler = loginHandler;
        this.memberService = memberService;
    }

    @Override
    protected Member doInBackground(String... params) {
        OaITF oaITF = RTF.createOaITF();
        Member member = oaITF.login(params[0],params[1]);
        if(member == null ){
            loginHandler.sendEmptyMessage(V.LOGIN_ERROR_UNDEFINED);
        }else{
            String error = member.getError();
            if(StringUtils.isNotEmpty(error)){
                if(StringUtils.equals(E.LOGIN_EMPTY_EMPNO,error)){
                    loginHandler.sendEmptyMessage(V.LOGIN_EMPTY_EMPNO);
                }else if(StringUtils.equals(E.LOGIN_EMPTY_EMPPW,error)){
                    loginHandler.sendEmptyMessage(V.LOGIN_EMPTY_EMPPW);
                }else if(StringUtils.equals(E.LOGIN_ERROR_UPE,error)){
                    loginHandler.sendEmptyMessage(V.LOGIN_ERROR_UPE);
                }else{
                    loginHandler.sendEmptyMessage(V.LOGIN_ERROR_UNDEFINED);
                }
            }else{
                memberService.syncModel(member);
                S.member_id = member.getId();
                S.member_emp_no = member.getEmp_no();
                S.member_emp_randid = member.getEmp_randid();
                loginHandler.sendEmptyMessage(V.LOGIN_SUCCESS);
            }
        }
        return null;
    }

}
