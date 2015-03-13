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

import java.io.Serializable;
import java.util.Properties;

public class AppInfo implements Serializable {
    private static final long serialVersionUID = 1L;

    private String packageName;
    private Integer versionCode;
    private String versionName;
    private Properties deviceInfo;

    public String getPackageName() {
        return packageName;
    }

    public void setPackageName(String packageName) {
        this.packageName = packageName;
    }

    public Integer getVersionCode() {
        return versionCode;
    }

    public void setVersionCode(Integer versionCode) {
        this.versionCode = versionCode;
    }

    public String getVersionName() {
        return versionName;
    }

    public void setVersionName(String versionName) {
        this.versionName = versionName;
    }

    public Properties getDeviceInfo() {
        return deviceInfo;
    }

    public void setDeviceInfo(Properties deviceInfo) {
        this.deviceInfo = deviceInfo;
    }
}
