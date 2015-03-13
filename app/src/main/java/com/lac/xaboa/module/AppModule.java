// +----------------------------------------------------------------------
// | FileName:   ${file_name}
// +----------------------------------------------------------------------
// | CreateTime: ${date}  ${time}
// +----------------------------------------------------------------------
// | Author:     xab(admin@xueyong.net.cn)
// +----------------------------------------------------------------------
// | Description:
// +----------------------------------------------------------------------


package com.lac.xaboa.module;

import android.content.Context;

import com.lac.xab.utils.PreferenceUtils;
import com.lac.xaboa.base.A;
import com.lac.xaboa.bean.AppInfo;
import com.lac.xaboa.db.service.MemberService;
import com.lac.xaboa.db.service.NoticeService;
import com.lac.xaboa.main.aty.MainAty;
import com.lac.xaboa.member.LoginAty;
import com.lac.xaboa.member.task.LoginTask;
import com.lac.xaboa.utils.I;
import com.lac.xaboa.utils.LocationUtils;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

@Module(injects = {A.class,LoginAty.class, MainAty.class, LoginAty.class}, library = true, complete = false)
public class AppModule {
    private final Context context;

    public AppModule(Context context) {
        this.context = context.getApplicationContext();
    }

    @Provides
    @Singleton
    Context appliactionContext() {
        return context;
    }

    @Provides
    @Singleton
    PreferenceUtils providePreferenceUtils(){
        return  PreferenceUtils.getInstance(appliactionContext());
    }


    @Provides
    @Singleton
    AppInfo provideAppInfo() {
        return new I(appliactionContext()).init();
    }

    @Provides
    @Singleton
    LocationUtils provideLocationUtils() {
        return  new LocationUtils(appliactionContext());
    }

    @Provides
    @Singleton
    MemberService  provideMemberService() {
        return MemberService.getInstance(appliactionContext());
    }

    @Provides
    @Singleton
    NoticeService provideNoticeService() {
        return NoticeService.getInstance(appliactionContext());
    }
}
