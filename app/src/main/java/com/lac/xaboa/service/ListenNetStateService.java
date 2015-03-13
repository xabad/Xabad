package com.lac.xaboa.service;

import android.app.Service;
import android.content.BroadcastReceiver;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.ConnectivityManager;
import android.os.IBinder;

import com.lac.xab.utils.L;
import com.lac.xaboa.base.BusProvider;
import com.lac.xaboa.jpush.PushModel;
import com.lac.xaboa.receiver.NetStateReceiver;
import com.squareup.otto.Subscribe;

public class ListenNetStateService extends Service {
    BroadcastReceiver mReceiver;

    public ListenNetStateService() {

    }

    @Override
    public void onCreate() {
        mReceiver = new NetStateReceiver();
        IntentFilter mFilter = new IntentFilter();
        mFilter.addAction(ConnectivityManager.CONNECTIVITY_ACTION);
        registerReceiver(mReceiver, mFilter);
        BusProvider.getInstance().register(this);
    }

    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        unregisterReceiver(mReceiver);
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {

        return super.onStartCommand(intent, flags, startId);
    }

}
