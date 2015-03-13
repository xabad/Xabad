// +----------------------------------------------------------------------
// | FileName:   ${file_name}  
// +----------------------------------------------------------------------
// | CreateTime: ${date}  ${time}
// +----------------------------------------------------------------------
// | Author:     xab(admin@xueyong.net.cn)
// +----------------------------------------------------------------------
// | Description:
// +----------------------------------------------------------------------


package com.lac.xaboa.base;

import com.lac.xaboa.db.model.Member;
import com.lac.xaboa.db.model.Notice;
import com.lac.xaboa.utils.H;

import java.util.List;

import retrofit.Callback;
import retrofit.http.Field;
import retrofit.http.FormUrlEncoded;
import retrofit.http.GET;
import retrofit.http.Multipart;
import retrofit.http.POST;
import retrofit.http.Part;
import retrofit.http.Path;
import retrofit.mime.TypedFile;
import retrofit.mime.TypedString;

public interface OaITF {


    @GET(H.URL_LOGIN)
    Member login(@Path("emp_no") String emp_no,@Path("password") String password);

    @FormUrlEncoded
    @POST(H.URL_NOTICE_LIST)
    void getNoticeList(@Field("emp_no") String emp_no,@Field("randid") String emp_randid,@Field("lastid") String lastid,Callback<List<Notice>> cb);

    @Multipart
    @POST("/index.php?m=Home&c=Phone")
    String upfile(@Part("file") TypedFile photo, @Part("desc") TypedString description);

    @FormUrlEncoded
    @POST("/index.php?m=Home&c=Phone&a=uploadError")
    String uploadError(@Field("appinfo") String gson);

}
