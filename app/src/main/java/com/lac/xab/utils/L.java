// +----------------------------------------------------------------------
// | FileName:   L.java
// +----------------------------------------------------------------------
// | CreateTime: 2014-9-26  上午11:55:15
// +----------------------------------------------------------------------
// | Copyright:  http://www.xueyong.net.cn
// +----------------------------------------------------------------------
// | Author:     xab(admin@xueyong.net.cn)
// +----------------------------------------------------------------------
package com.lac.xab.utils;

import android.os.Environment;
import android.os.Process;

import com.lac.xaboa.utils.C;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;

public class L {
    public static boolean D;
    public static boolean E;
    public static boolean I;
    public static boolean V;
    public static boolean W;

    public static String className = "";
    public static String methodName = "";
    public static final String LOG_FILE = Environment
            .getExternalStorageDirectory() + C.LOG_FILE_NAME;
    ;

    private static RandomAccessFile accessFile;

    public static void d(String TAG, String str) {
        if (C.PRINTLOG) {
            android.util.Log.d(TAG, "tid:" + Process.myTid() + "; -->" + str);
            saveLog(TAG + ":" + str);
        }
    }

    public static void e(String TAG, String str) {
        if (C.PRINTLOG) {
            android.util.Log.e(TAG, "tid:" + Process.myTid() + "; -->" + str);
            saveLog(TAG + ":" + str);
        }
    }

    public static void e(String TAG, String str, Exception e) {
        if (C.PRINTLOG) {
            android.util.Log.e(TAG, "tid:" + Process.myTid() + "; -->" + str);
            saveLog(TAG + ":" + str + ",==>" + e);
        }
    }

    public static void i() {
        getMethodNames(new Throwable().getStackTrace());
        i(C.TAG, "",false);
    }

    public static void i(Object str) {
        getMethodNames(new Throwable().getStackTrace());
        i(C.TAG, str,false);
    }


    public static void i(String TAG, Object str) {
        getMethodNames(new Throwable().getStackTrace());
        i(TAG, str,false);
    }


    public static void i(String TAG, Object str,boolean customClass) {
        if(customClass){
            getMethodNames(new Throwable().getStackTrace());
        }
        if (C.PRINTLOG) {
            android.util.Log.i(TAG, createLog(String.valueOf(str)));
            saveLog(TAG + ":" + str);
        }
    }

    public static void v(String TAG, String str) {
        if (C.PRINTLOG) {
            android.util.Log.v(TAG, "tid:" + Process.myTid() + "; -->" + str);
            saveLog(TAG + ":" + str);
        }
    }

    public static void w(String TAG, String str) {
        if (C.PRINTLOG) {
            android.util.Log.w(TAG, "tid:" + Process.myTid() + "; -->" + str);
            saveLog(TAG + ":" + str);
        }
    }

    private static void saveLog(String paramString) {
        if (C.DEBUG) {
            try {
                if (accessFile == null) {
                    accessFile = new RandomAccessFile(LOG_FILE, "rw");
                }

                long l = accessFile.length();
                accessFile.seek(l);
                accessFile.writeBytes(DateTimeUtils.StrYMDHMS() + ":");
                accessFile.writeBytes(paramString);
                accessFile.writeBytes("\r\n");
                return;
            } catch (FileNotFoundException localFileNotFoundException) {
                localFileNotFoundException.printStackTrace();

                return;
            } catch (IOException localIOException) {
                localIOException.printStackTrace();
            }
        }
    }
    private static String createLog(String log) {
        StringBuffer buffer = new StringBuffer();
        buffer.append("[ ");
        buffer.append("CLASS => ");
        buffer.append(className);
        buffer.append(",METHOD => ");
        buffer.append(methodName);
        buffer.append(" ] ");
        buffer.append(log);
        return buffer.toString();
    }

    private static void getMethodNames(StackTraceElement[] sElements) {
        className = sElements[1].getFileName().split("\\.")[0];
        methodName = sElements[1].getMethodName();
       // lineNumber = sElements[1].getLineNumber();
    }

}
