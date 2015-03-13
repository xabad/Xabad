// +----------------------------------------------------------------------
// | FileName:   ${file_name}  
// +----------------------------------------------------------------------
// | CreateTime: ${date}  ${time}
// +----------------------------------------------------------------------
// | Author:     xab(admin@xueyong.net.cn)
// +----------------------------------------------------------------------
// | Description:
// +----------------------------------------------------------------------


package com.lac.xaboa.utils;

import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.os.Build;


import com.lac.xaboa.bean.AppInfo;

import java.lang.reflect.Field;
import java.util.Properties;

public class I {
    private AppInfo appInfo;

    {
        appInfo = new AppInfo();
    }

    Context context;
    PackageManager packageManager;
    Properties deviceInfo =new Properties();

    public I(Context context) {
        this.context = context;
    }
    public AppInfo init(){

        String packageName = context.getPackageName();
        try {
            packageManager = context.getPackageManager();
            getDeviceInfo();
            PackageInfo packageInfo = packageManager.getPackageInfo(packageName,PackageManager.GET_ACTIVITIES);
            appInfo.setPackageName(packageInfo.packageName);
            appInfo.setVersionCode(packageInfo.versionCode);
            appInfo.setVersionName(packageInfo.versionName);
            appInfo.setDeviceInfo(deviceInfo);
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
        return appInfo;
    }

    private void getDeviceInfo() throws IllegalAccessException {
        Field[] fields = Build.class.getDeclaredFields();
        for (Field field : fields) {
             field.setAccessible(true);
             deviceInfo.put(field.getName(), field.get(null));
        }
    }

}
