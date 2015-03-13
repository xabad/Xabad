// +----------------------------------------------------------------------
// | FileName:   @${file_name}  
// +----------------------------------------------------------------------
// | CreateTime: @${date}  @${time}
// +----------------------------------------------------------------------
// | Author:     xab(admin@xueyong.net.cn)
// +----------------------------------------------------------------------
// | Description:
// +----------------------------------------------------------------------


package com.lac.xab.utils;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;

import java.io.File;

public class AppUtils {
    public static void install(Activity activity,File file) {
        Intent intent = new Intent();
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        intent.setAction(android.content.Intent.ACTION_VIEW);
        intent.setDataAndType(Uri.fromFile(file),
                "application/vnd.android.package-archive");
        activity.startActivity(intent);
    }
    public static void uninstall(Activity activity,String packageName) {
        Uri packageURI = Uri.parse("package:"+packageName);
        Intent uninstallIntent = new Intent(Intent.ACTION_DELETE, packageURI);
        activity.startActivity(uninstallIntent);
    }
}
