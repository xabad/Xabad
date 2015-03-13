// +----------------------------------------------------------------------
// | FileName:   Xaboa  
// +----------------------------------------------------------------------
// | CreateTime: 2015/2/12  11:33
// +----------------------------------------------------------------------
// | Author:     xab(admin@xueyong.net.cn)
// +----------------------------------------------------------------------
// | Description:
// +----------------------------------------------------------------------


package com.lac.xaboa.jpush;

import java.io.Serializable;

public class PushModel implements Serializable{
    private static final long serialVersionUID = 1L;

    private String PUSH_ID ;
    private String ALERT ;
    private String EXTRA ;
    private String NOTIFICATION_ID ;
    private String NOTIFICATION_CONTENT_TITLE ;
    private String MSG_ID ;

    public String getPUSH_ID() {
        return PUSH_ID;
    }

    public void setPUSH_ID(String PUSH_ID) {
        this.PUSH_ID = PUSH_ID;
    }

    public String getALERT() {
        return ALERT;
    }

    public void setALERT(String ALERT) {
        this.ALERT = ALERT;
    }

    public String getEXTRA() {
        return EXTRA;
    }

    public void setEXTRA(String EXTRA) {
        this.EXTRA = EXTRA;
    }

    public String getNOTIFICATION_ID() {
        return NOTIFICATION_ID;
    }

    public void setNOTIFICATION_ID(String NOTIFICATION_ID) {
        this.NOTIFICATION_ID = NOTIFICATION_ID;
    }

    public String getNOTIFICATION_CONTENT_TITLE() {
        return NOTIFICATION_CONTENT_TITLE;
    }

    public void setNOTIFICATION_CONTENT_TITLE(String NOTIFICATION_CONTENT_TITLE) {
        this.NOTIFICATION_CONTENT_TITLE = NOTIFICATION_CONTENT_TITLE;
    }

    public String getMSG_ID() {
        return MSG_ID;
    }

    public void setMSG_ID(String MSG_ID) {
        this.MSG_ID = MSG_ID;
    }
}
