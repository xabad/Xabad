// +----------------------------------------------------------------------
// | FileName:   @${file_name}  
// +----------------------------------------------------------------------
// | CreateTime: @${date}  @${time}
// +----------------------------------------------------------------------
// | Author:     xab(admin@xueyong.net.cn)
// +----------------------------------------------------------------------
// | Description:
// +----------------------------------------------------------------------


package com.lac.xaboa.utils;

import android.content.Context;
import android.location.Location;
import android.os.Bundle;
import android.os.Handler;

import com.amap.api.location.AMapLocation;
import com.amap.api.location.AMapLocationListener;
import com.amap.api.location.LocationManagerProxy;
import com.amap.api.location.LocationProviderProxy;
import com.lac.xaboa.base.BusProvider;
import com.lac.xaboa.bean.AMapBean;


public class LocationUtils implements
        AMapLocationListener, Runnable {

    Context context;
    private AMapBean mapBean = new AMapBean();

    private LocationManagerProxy aMapLocManager = null;
    private AMapLocation aMapLocation;
    private Handler handler = new Handler();

    public LocationUtils(Context context) {
        this.context = context;

    }

    public void start() {
        if (aMapLocManager == null) {
            aMapLocManager = LocationManagerProxy.getInstance(context);
        }
        aMapLocManager.requestLocationData(
                LocationProviderProxy.AMapNetwork, -1, 10, this);
        handler.postDelayed(this, C.LOCATION_DELAVED);
    }

    public void stop() {
        if (aMapLocManager != null) {
            aMapLocManager.removeUpdates(this);
            aMapLocManager.destroy();
        }
        aMapLocManager = null;
    }

    @Override
    public void onLocationChanged(AMapLocation location) {
        if (location != null) {
            this.aMapLocation = location;
            mapBean.setLocation(this.aMapLocation);
            BusProvider.getInstance().post(mapBean);
        }
    }


    @Override
    public void run() {
        if (this.aMapLocation == null) {
            mapBean.clear();
            BusProvider.getInstance().post(mapBean);
            stop();
        }
    }

    @Override
    public void onLocationChanged(Location location) {

    }

    @Override
    public void onStatusChanged(String provider, int status, Bundle extras) {

    }

    @Override
    public void onProviderEnabled(String provider) {

    }

    @Override
    public void onProviderDisabled(String provider) {

    }

}
