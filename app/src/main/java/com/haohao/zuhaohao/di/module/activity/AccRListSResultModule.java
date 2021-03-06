package com.haohao.zuhaohao.di.module.activity;

import android.app.Activity;

import com.haohao.zuhaohao.di.scoped.ActivityScoped;
import com.haohao.zuhaohao.ui.module.account.AccRListSResultActivity;

import dagger.Binds;
import dagger.Module;
import dagger.Provides;

/**
 * 账号搜索结果
 * date：2017/5/5 15:18
 * author：Seraph
 **/
@Module(includes = ActivityModule.class)
public abstract class AccRListSResultModule {

    @ActivityScoped
    @Binds
    abstract Activity bindActivity(AccRListSResultActivity activity);

    @Provides
    @ActivityScoped
    static String provideSearchStr(Activity activity) {
        return activity.getIntent().getStringExtra("key");
    }

}
