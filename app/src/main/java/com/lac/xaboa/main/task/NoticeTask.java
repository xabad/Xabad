// +----------------------------------------------------------------------
// | FileName:   Xaboa  
// +----------------------------------------------------------------------
// | CreateTime: 2015/2/3  15:09
// +----------------------------------------------------------------------
// | Author:     xab(admin@xueyong.net.cn)
// +----------------------------------------------------------------------
// | Description:
// +----------------------------------------------------------------------


package com.lac.xaboa.main.task;

import android.os.Handler;
import android.os.Message;

import com.google.gson.Gson;
import com.lac.xab.utils.L;
import com.lac.xaboa.base.OaITF;
import com.lac.xaboa.db.model.Notice;
import com.lac.xaboa.db.service.NoticeService;
import com.lac.xaboa.utils.RTF;
import com.lac.xaboa.utils.S;
import com.lac.xaboa.utils.V;

import java.util.List;

import retrofit.Callback;
import retrofit.RetrofitError;
import retrofit.client.Header;
import retrofit.client.Response;

public class NoticeTask {
    NoticeService noticeService;
    public NoticeTask() {
    }

    public void getList(final Handler handler,final NoticeService noticeService){
        this.noticeService = noticeService;
        OaITF oaITF = RTF.createOaITF();
        Callback<List<Notice>> cb = new Callback<List<Notice>>() {
            @Override
            public void success(List<Notice> notices, Response response) {
                if(RTF.checkRandid(response)){

                    if(notices ==null ||notices.size() == 0){
                        handler.sendEmptyMessage(V.NOTICE_LIST_NODATA);
                    }else{
                        noticeService.syncList(notices);
                        handler.sendEmptyMessage(V.NOTICE_LIST_HAVADATA);
                    }
                    handler.sendEmptyMessage(V.SYNC_NOTICE);
                }else{
                    handler.sendEmptyMessage(V.ERROR_RANDID);
                }
            }
            @Override
            public void failure(RetrofitError error) {

            }
        };
        oaITF.getNoticeList(S.member_emp_no,S.member_emp_randid,noticeService.lastId(),cb);
    }
}
