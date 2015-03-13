// +----------------------------------------------------------------------
// | FileName:   Xaboa  
// +----------------------------------------------------------------------
// | CreateTime: 2015/1/29  13:50
// +----------------------------------------------------------------------
// | Author:     xab(admin@xueyong.net.cn)
// +----------------------------------------------------------------------
// | Description:
// +----------------------------------------------------------------------


package com.lac.xaboa.utils;

import com.lac.xab.utils.L;
import com.lac.xab.utils.StringUtils;
import com.lac.xaboa.base.OaITF;

import java.util.List;

import retrofit.RestAdapter;
import retrofit.client.Header;
import retrofit.client.Response;
import retrofit.converter.Converter;

public class RTF {

    public static OaITF createOaITF(){
        RestAdapter restAdapter = new RestAdapter.Builder()
                .setEndpoint(C.URL_BASE)
                .setLogLevel(RestAdapter.LogLevel.FULL)
                .build();
        return restAdapter.create(OaITF.class);
    }

    public static OaITF createOaITF(Converter converter){
        RestAdapter restAdapter = new RestAdapter.Builder()
                .setEndpoint(C.URL_BASE)
                .setLogLevel(RestAdapter.LogLevel.FULL)
                .setConverter(converter)
                .build();
        return restAdapter.create(OaITF.class);
    }

    public static boolean checkRandid( Response response){
        boolean flag = false;
        List<Header> headers = response.getHeaders();
        if(headers ==null || headers.size() < 1){
            flag = false;
        }else{
            int size = headers.size();
            for(int i=0;i<size;i++){
                Header header = headers.get(i);
                if(StringUtils.equals(header.getName(),K.CHECK_RANDID)){
                    flag = Boolean.parseBoolean(header.getValue());
                }
            }
        }
        return flag;
    }
}
