package com.lac.xaboa.widget;

import android.app.PendingIntent;
import android.appwidget.AppWidgetManager;
import android.appwidget.AppWidgetProvider;
import android.content.Context;
import android.content.Intent;
import android.widget.RemoteViews;

import com.lac.xaboa.R;
import com.lac.xaboa.main.aty.MainAty;

/**
 * App桌面插件
 * @author tianhongwei
 * @since 2014-11
 *
 */
public class MyWidgetProvider extends AppWidgetProvider {
//	private static final String action = "com.cnhbv.app.fisheryapp.mywidgetprovider";
	
	 //每接收一次广播消息就调用一次，使用频繁  
    public void onReceive(Context context, Intent intent) {  
        // TODO Auto-generated method stub  
        System.out.println("recrive");
        super.onReceive(context, intent);  
    }  
    //每次更新都调用一次该方法，使用频繁  
    public void onUpdate(Context context, AppWidgetManager appWidgetManager, int[] appWidgetIds) { 
        super.onUpdate(context, appWidgetManager, appWidgetIds);  
        System.out.println("update--->");
        RemoteViews views = new RemoteViews(context.getPackageName(), R.layout.inc_splash);
        Intent intent = new Intent(context, MainAty.class);
        PendingIntent pendingIntent = PendingIntent.getActivity(context, 0, intent, 0);
        views.setOnClickPendingIntent(R.id.layoutImg, pendingIntent);
        appWidgetManager.updateAppWidget(appWidgetIds, views);
    }  
    //没删除一个就调用一次  
    public void onDeleted(Context context, int[] appWidgetIds) {  
        // TODO Auto-generated method stub  
        System.out.println("Deleted");  
        super.onDeleted(context, appWidgetIds);  
    }  
    //当该Widget第一次添加到桌面是调用该方法，可添加多次但只第一次调用  
    public void onEnabled(Context context) {  
        // TODO Auto-generated method stub  
        System.out.println("OnEnable");  
        super.onEnabled(context);  
    }  
    //当最后一个该Widget删除是调用该方法，注意是最后一个  
    public void onDisabled(Context context) {  
        // TODO Auto-generated method stub  
        System.out.println("onDisable");  
        super.onDisabled(context);  
    }  
}
