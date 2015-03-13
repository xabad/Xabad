// +----------------------------------------------------------------------
// | FileName:   @${file_name}  
// +----------------------------------------------------------------------
// | CreateTime: @${date}  @${time}
// +----------------------------------------------------------------------
// | Author:     xab(admin@xueyong.net.cn)
// +----------------------------------------------------------------------
// | Description:
// +----------------------------------------------------------------------


package com.lac.xaboa.base;

import android.app.Activity;
import android.app.Application;
import android.app.Notification;
import android.content.Context;
import android.content.Intent;
import android.os.Process;
import android.widget.Toast;

import com.lac.xab.utils.L;
import com.lac.xaboa.module.AppModule;
import com.lac.xaboa.service.LacPushService;
import com.lac.xaboa.service.ListenNetStateService;
import com.lac.xaboa.utils.C;
import com.tencent.bugly.crashreport.CrashReport;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import cn.jpush.android.api.BasicPushNotificationBuilder;
import cn.jpush.android.api.JPushInterface;
import dagger.ObjectGraph;

public class A extends Application {
    private static A instance = new A();
    private static Map<String, Activity> atyMap = new HashMap<String, Activity>();
    float i = 0f;

    public static A getInstance() {
        return instance;
    }

    private ObjectGraph objectGraph;
    CrashHandler ch = null;

    @Override
    public void onCreate() {
        super.onCreate();
        instance = this;
        ch = CrashHandler.getInstance();
        ch.init(getApplicationContext());
        CrashReport.UserStrategy strategy = new CrashReport.UserStrategy(getApplicationContext()); //App的策略Bean
        CrashReport.CrashHandleCallback chc = new CrashReport.CrashHandleCallback() {
            @Override
            public synchronized Map<String, String> onCrashHandleStart(int i, String s, String s2, String s3) {
                return super.onCrashHandleStart(i, s, s2, s3);
            }
        };
        strategy.setCrashHandleCallback(chc);
        CrashReport.initCrashReport(getApplicationContext(), C.BUGLY_APPID, C.DEBUG, strategy);
        objectGraph = ObjectGraph.create(new AppModule(instance));
        initService();
        JPushInterface.setDebugMode(true);    // 设置开启日志,发布时请关闭日志
             // 初始化 JPush
        initJpush();
        BasicPushNotificationBuilder builder = new BasicPushNotificationBuilder(this);
        builder.notificationFlags = Notification.FLAG_AUTO_CANCEL;  //设置为自动消失
        builder.notificationDefaults = 0;  // 设置为铃声与震动都要
        JPushInterface.setPushNotificationBuilder(1, builder);
    }

    private void initService() {
        Intent netsry = new Intent();
        netsry.setClass(this, ListenNetStateService.class);
        startService(netsry);
        netsry = new Intent();
        netsry.setClass(this, LacPushService.class);
        startService(netsry);
    }

    public static void initJpush(){
        JPushInterface.init(getInstance().getApplicationContext());
    }
    public static void onPause(Context context){
        JPushInterface.onPause(context);
    }
    public static void onResume(Context context){
        JPushInterface.onResume(context);
    }
    public static void stopJpush(){
        JPushInterface.stopPush(getInstance().getApplicationContext());
    }
    public static void resumePush(){
        JPushInterface.resumePush(getInstance().getApplicationContext());
    }
    public void addAty(Activity activity) {
        Class c = activity.getClass();
        String tag = c.getName();
        addAty(tag, activity);
    }

    public void addAty(String tag, Activity aty) {
        atyMap.put(tag, aty);
    }

    public void exit() {
        stopJpush();
        Iterator keys = atyMap.keySet().iterator();
        while (keys.hasNext()) {
            Object key = keys.next();
            finishAty(atyMap.get(key));
        }
        android.os.Process.killProcess(Process.myPid());
        System.exit(0);
    }

    private void finishAty(Activity aty) {
        if (aty != null && !aty.isFinishing()) {
            aty.finish();
        }
    }

    public static void inject(Activity activity) {
        getInstance().getObjectGraph().inject(activity);
    }

    public ObjectGraph getObjectGraph() {
        return objectGraph;
    }


}
