// +----------------------------------------------------------------------
// | FileName:   ${file_name}  
// +----------------------------------------------------------------------
// | CreateTime: ${date}  ${time}
// +----------------------------------------------------------------------
// | Author:     xab(admin@xueyong.net.cn)
// +----------------------------------------------------------------------
// | Description:
// +----------------------------------------------------------------------


package com.lac.xaboa.bean;


import com.amap.api.location.AMapLocation;
import com.lac.xab.utils.DateTimeUtils;

public class AMapBean {

    public boolean status =false;
    private Double geoLat;
    private Double geoLng;
    private String cityCode = "";
    private String adCode = "";
    private String desc = "";
    private Float accuracy;
    private String provider = "";
    private String time = "";
    private String province = "";
    private String city = "";
    private String district = "";
    private String street = "";
    private String floor = "";

    public void setLocation(AMapLocation location) {
        this.status = true;
        setAccuracy(location.getAccuracy());
        setAdCode(location.getAdCode());
        setCity(location.getCity());
        setCityCode(location.getCityCode());
        setDesc(location.getAddress());
        setGeoLat(location.getLatitude());
        setGeoLng(location.getLongitude());
        setAdCode(location.getAdCode());
        setProvider(location.getProvider());
        setProvince(location.getProvince());
        setTime(DateTimeUtils.getValueByTime(location.getTime() / 1000));
        setDistrict(location.getDistrict());
        setStreet(location.getStreet());
        setFloor(location.getFloor());
    }

    public void clear() {
        this.status = false;
        setAccuracy(0F);
        setAdCode("");
        setCity("");
        setCityCode("");
        setDesc("");
        setGeoLat(0D);
        setGeoLng(0D);
        setAdCode("");
        setProvider("");
        setProvince("");
        setTime("");
        setDistrict("");
        setStreet("");
        setFloor("");
    }
    public String getStreet() {
        return street;
    }

    private void setStreet(String street) {
        this.street = street;
    }

    public String getFloor() {
        return floor;
    }

    private void setFloor(String floor) {
        this.floor = floor;
    }

    public Double getGeoLat() {
        return geoLat;
    }

    private void setGeoLat(Double geoLat) {
        this.geoLat = geoLat;
    }

    public Double getGeoLng() {
        return geoLng;
    }

    private void setGeoLng(Double geoLng) {
        this.geoLng = geoLng;
    }

    public String getCityCode() {
        return cityCode;
    }

    private void setCityCode(String cityCode) {
        this.cityCode = cityCode;
    }

    public String getAdCode() {
        return adCode;
    }

    private void setAdCode(String adCode) {
        this.adCode = adCode;
    }

    public String getDesc() {
        return desc;
    }

    private void setDesc(String desc) {
        this.desc = desc;
    }

    public Float getAccuracy() {
        return accuracy;
    }

    private void setAccuracy(Float accuracy) {
        this.accuracy = accuracy;
    }

    public String getProvider() {
        return provider;
    }

    private void setProvider(String provider) {
        this.provider = provider;
    }

    public String getTime() {
        return time;
    }

    private void setTime(String time) {
        this.time = time;
    }

    public String getProvince() {
        return province;
    }

    private void setProvince(String province) {
        this.province = province;
    }

    public String getCity() {
        return city;
    }

    private void setCity(String city) {
        this.city = city;
    }

    public String getDistrict() {
        return district;
    }

    private void setDistrict(String district) {
        this.district = district;
    }

}
