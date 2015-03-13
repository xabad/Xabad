// +----------------------------------------------------------------------
// | FileName:   Xaboa  
// +----------------------------------------------------------------------
// | CreateTime: 2015/2/13  16:51
// +----------------------------------------------------------------------
// | Author:     xab(admin@xueyong.net.cn)
// +----------------------------------------------------------------------
// | Description:
// +----------------------------------------------------------------------


package com.lac.xaboa.jpush;

import android.content.Context;
import android.os.AsyncTask;

import com.lac.xaboa.db.model.PushLog;
import com.lac.xaboa.db.service.PushLogService;
import com.lac.xaboa.utils.V;


public class SynPushData extends AsyncTask<String,Integer,Void> {
    PushLog pushLog ;
    PushLogService pushLogService ;
    public SynPushData(Context context,PushLog pushLog) {
        this.pushLog = pushLog;
        pushLogService = PushLogService.getInstance(context);
    }

    @Override
    protected Void doInBackground(String... params) {
        pushLog.setStatus(V.PUSH_LOG_STATUS_FINISH);
        pushLogService.syncModel(pushLog);
        return null;
    }

    @Override
    protected void onPostExecute(Void aVoid) {

    }
}
