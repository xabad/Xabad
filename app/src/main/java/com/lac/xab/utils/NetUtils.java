// +----------------------------------------------------------------------
// | FileName:   @NetUtils.java  
// +----------------------------------------------------------------------
// | CreateTime: @2014-9-24  @上午10:05:43
// +----------------------------------------------------------------------
// | Copyright:  http://www.xueyong.net.cn
// +----------------------------------------------------------------------
// | Author:     xab(admin@xueyong.net.cn)
// +----------------------------------------------------------------------
package com.lac.xab.utils;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

public class NetUtils extends XabUtils {
	/**
	 * 
	 * 检查设备是否连接到网络
	 * 
	 * @date 2013-4-19 下午2:12:37
	 */
	public static boolean isConnec(Context context) {
		boolean flag = false;
		ConnectivityManager cconnManager = (ConnectivityManager) context
				.getSystemService(Context.CONNECTIVITY_SERVICE);
		if (cconnManager != null) {
			NetworkInfo netInfo = cconnManager.getActiveNetworkInfo();
			if (netInfo != null) {
				if (netInfo.isConnected()) {
					flag = true;
				}
			}
			clear(netInfo);
		}
		clear(cconnManager);
		return flag;
	}

	/**
	 * 
	 * 检测设备是否连接到wifi
	 * 
	 * @date 2013-4-19 下午2:20:14
	 */
	public static boolean isConnecWIFI(Context context) {
		boolean flag = false;
		ConnectivityManager cconnManager = (ConnectivityManager) context
				.getSystemService(Context.CONNECTIVITY_SERVICE);
		if (cconnManager != null) {
			NetworkInfo wifiInfo = cconnManager
					.getNetworkInfo(ConnectivityManager.TYPE_WIFI);
			if (wifiInfo != null && wifiInfo.isAvailable()) {
				if (wifiInfo.isConnected()) {
					flag = true;
				}
			}
			clear(wifiInfo);
		}
		clear(cconnManager);
		return flag;
	}
}
