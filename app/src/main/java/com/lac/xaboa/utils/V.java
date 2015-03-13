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

public class V {
    //图片选择类型
    public final static int OPEN_CAMERA = 1;
    public final static int OPEN_ALBUM = 2;

    public final static int TAKE_PICTURE = 1;

    //图片类型
    public final static int PIC_CAMERA = 1;
    public final static int PIC_ALBUM = 2;

    public final static int PIC_TOOL = 3;
    //图片操作
    public final static int PIC_ADD = 1;
    public final static int PIC_DEL = 2;

    public final static int DATE = 1;
    public final static int TIME = 2;
    public final static int DATETIME = 3;

    public final static int UPDATE = 0;
    public final static int CLEAR = 1;

    //TASK
    public final static int ERROR_RANDID = -1;
    //登录
    public final static int LOGINING = 50;
    public final static int LOGINDFT = 0;

    public final static int LOGIN_EMPTY_EMPNO = 0;
    public final static int LOGIN_EMPTY_EMPPW = 1;
    public final static int LOGIN_ERROR_UPE = 2;
    public final static int LOGIN_ERROR_UNDEFINED = 3;
    public final static int LOGIN_SUCCESS = 4;

    // Notice
    public final static int NOTICE_LIST_HAVADATA = 2;
    public final static int NOTICE_LIST_NODATA = 1;

    //SYNCDATA
    public final static int SYNC_NOTICE = 10001;

    //push syndata
    public final static String PUSH_OPERATE_INSERT = "PUSH_OPERATE_INSERT";
    public final static String PUSH_OPERATE_DELETE = "PUSH_OPERATE_DELETE";
    public final static String PUSH_OPERATE_UPDATE = "PUSH_OPERATE_UPDATE";

    public final static String PUSH_DATABASE = "PUSH_DATABASE";
    public final static String PUSH_SYSTEM = "PUSH_SYSTEM";

    public final static String PUSH_LOG_STATUS_FINISH= "FINISH";
    public final static String PUSH_LOG_STATUS_PREPARE= "PREPARE";

}

