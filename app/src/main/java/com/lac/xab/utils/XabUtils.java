// +----------------------------------------------------------------------
// | FileName:   @XabUtils.java  
// +----------------------------------------------------------------------
// | CreateTime: @2014-9-24  @上午10:06:08
// +----------------------------------------------------------------------
// | Copyright:  http://www.xueyong.net.cn
// +----------------------------------------------------------------------
// | Author:     xab(admin@xueyong.net.cn)
// +----------------------------------------------------------------------
package com.lac.xab.utils;


import android.os.Environment;

public class XabUtils {
    private static XabUtils instance = null;

    public synchronized static XabUtils getInstance() {
        if (instance == null) {
            instance = new XabUtils();
        }
        return instance;
    }

    /**
     * 释放内存
     *
     * @date 2013-4-19 下午2:32:56
     */
    protected void c2n(Object... o) {
        int i = 0;
        if (o != null && o.length > 0) {
            int j = o.length;
            for (; i < j; i++) {
                o[i] = null;
            }
        }
    }

    public static void clear(Object... o) {
        getInstance();
        instance.c2n(o);
    }

    public static boolean existSDCard() {
        if (!Environment.getExternalStorageDirectory().equals(Environment.MEDIA_MOUNTED)) {
            return true;
        } else {
            return false;
        }
    }
}
