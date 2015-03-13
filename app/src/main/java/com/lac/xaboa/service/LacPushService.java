// +----------------------------------------------------------------------
// | FileName:   Xaboa  
// +----------------------------------------------------------------------
// | CreateTime: 2015/2/13  10:30
// +----------------------------------------------------------------------
// | Author:     xab(admin@xueyong.net.cn)
// +----------------------------------------------------------------------
// | Description:
// +----------------------------------------------------------------------


package com.lac.xaboa.service;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.lac.xab.utils.L;
import com.lac.xab.utils.StringUtils;
import com.lac.xaboa.base.BusProvider;

import com.lac.xaboa.db.model.PushLog;
import com.lac.xaboa.jpush.PushModel;
import com.lac.xaboa.jpush.SynPushData;
import com.lac.xaboa.utils.V;
import com.squareup.otto.Subscribe;

import java.util.ArrayList;
import java.util.List;

public class LacPushService extends Service {

    @Override
    public void onCreate() {
        BusProvider.getInstance().register(this);
    }

    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    /**
     * 接收PUSH返回的信息
     */
    @Subscribe
    public void onReceiverPush(PushModel pushModel) {
        SynPushData synPushData =null;
        Gson gson = new Gson();
        L.i(pushModel.getALERT());
        PushLog pushLog=gson.fromJson(pushModel.getEXTRA(), PushLog.class);
        if (StringUtils.equals(V.PUSH_DATABASE, pushLog.getPushtype())) {
            pushLog.setStatus(V.PUSH_LOG_STATUS_PREPARE);
            synPushData = new SynPushData(this,pushLog);
            synPushData.execute();
        }
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        return super.onStartCommand(intent, flags, startId);
    }
}
