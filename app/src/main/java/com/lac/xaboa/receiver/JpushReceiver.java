package com.lac.xaboa.receiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

import com.google.gson.Gson;
import com.lac.xab.utils.L;
import com.lac.xaboa.base.BusProvider;
import com.lac.xaboa.jpush.PushModel;


import cn.jpush.android.api.JPushInterface;

/**
 * 自定义接收器
 * <p/>
 * 如果不定义这个 Receiver，则：
 * 1) 默认用户会打开主界面
 * 2) 接收不到自定义消息
 */
public class JpushReceiver extends BroadcastReceiver {
    private static final String TAG = "JPush";

    @Override
    public void onReceive(Context context, Intent intent) {
        Bundle bundle = intent.getExtras();
        if (JPushInterface.ACTION_REGISTRATION_ID.equals(intent.getAction())) {
            String regId = bundle.getString(JPushInterface.EXTRA_REGISTRATION_ID);
            L.i("[JpushReceiver] 接收Registration Id : " + regId);
            //send the Registration Id to your server...

        } else if (JPushInterface.ACTION_MESSAGE_RECEIVED.equals(intent.getAction())) {
            L.i("[JpushReceiver] 接收到推送下来的自定义消息: " + bundle.getString(JPushInterface.EXTRA_MESSAGE));
            processCustomMessage(context, bundle);

        } else if (JPushInterface.ACTION_NOTIFICATION_RECEIVED.equals(intent.getAction())) {
            String PUSH_ID = bundle.getString("cn.jpush.android.PUSH_ID");
            String ALERT = bundle.getString("cn.jpush.android.ALERT");
            String EXTRA = bundle.getString("cn.jpush.android.EXTRA");
            String NOTIFICATION_ID = ""+bundle.getInt(JPushInterface.EXTRA_NOTIFICATION_ID);
            String NOTIFICATION_CONTENT_TITLE = bundle.getString("cn.jpush.android.NOTIFICATION_CONTENT_TITLE");
            String MSG_ID = bundle.getString("cn.jpush.android.MSG_ID");
            PushModel pushModel =new PushModel();
            pushModel.setALERT(ALERT);
            pushModel.setEXTRA(EXTRA);
            pushModel.setMSG_ID(MSG_ID);
            pushModel.setNOTIFICATION_CONTENT_TITLE(NOTIFICATION_CONTENT_TITLE);
            pushModel.setNOTIFICATION_ID(NOTIFICATION_ID);
            pushModel.setPUSH_ID(PUSH_ID);
            BusProvider.getInstance().post(pushModel);
        } else if (JPushInterface.ACTION_NOTIFICATION_OPENED.equals(intent.getAction())) {
            L.i("[JpushReceiver] 用户点击打开了通知");
            String notifactionId = bundle.getString(JPushInterface.EXTRA_NOTIFICATION_ID);
            JPushInterface.reportNotificationOpened(context, notifactionId);

        } else if (JPushInterface.ACTION_RICHPUSH_CALLBACK.equals(intent.getAction())) {
            L.i("[JpushReceiver] 用户收到到RICH PUSH CALLBACK: " + bundle.getString(JPushInterface.EXTRA_EXTRA));
            //在这里根据 JPushInterface.EXTRA_EXTRA 的内容处理代码，比如打开新的Activity， 打开一个网页等..
        } else if (JPushInterface.ACTION_CONNECTION_CHANGE.equals(intent.getAction())) {
            boolean connected = intent.getBooleanExtra(JPushInterface.EXTRA_CONNECTION_CHANGE, false);
            Log.w(TAG, "[JpushReceiver]" + intent.getAction() + " connected state change to " + connected);
        } else {
            L.i("[JpushReceiver] Unhandled intent - " + intent.getAction());
        }
    }

    // 打印所有的 intent extra 数据
    private static String printBundle(Bundle bundle) {
        StringBuilder sb = new StringBuilder();
        for (String key : bundle.keySet()) {
            if (key.equals(JPushInterface.EXTRA_NOTIFICATION_ID)) {
                sb.append("\nkey:" + key + ", value:" + bundle.getInt(key));
            } else if (key.equals(JPushInterface.EXTRA_CONNECTION_CHANGE)) {
                sb.append("\nkey:" + key + ", value:" + bundle.getBoolean(key));
            } else {
                sb.append("\nkey:" + key + ", value:" + bundle.getString(key));
            }
        }
        return sb.toString();
    }

    //send msg to MainActivity
    private void processCustomMessage(Context context, Bundle bundle) {
        String message = bundle.getString(JPushInterface.EXTRA_MESSAGE);
        String extras = bundle.getString(JPushInterface.EXTRA_EXTRA);
    }
}
