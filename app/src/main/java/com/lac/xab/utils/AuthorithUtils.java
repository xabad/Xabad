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


import java.io.IOException;

public class AuthorithUtils {
    public static void chmod(String permission, String path) {
        try {
            String command = "chmod " + permission + " " + path;
            Runtime runtime = Runtime.getRuntime();
            java.lang.Process process= runtime.exec(command);
            process.waitFor();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
