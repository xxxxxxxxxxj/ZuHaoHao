package com.haohao.zuhaohao.di.module.activity;

import android.app.Activity;

import com.haohao.zuhaohao.di.scoped.ActivityScoped;
import com.haohao.zuhaohao.ui.module.rights.RightsProcessSellerActivity;

import dagger.Binds;
import dagger.Module;
import dagger.Provides;

/**
 * 维权处理
 * date：2017/5/5 15:18
 * author：Seraph
 *
 **/
@Module(includes = ActivityModule.class)
public abstract class RightsProcessSellerModule {

    @Binds
    @ActivityScoped
    abstract Activity bindActivity(RightsProcessSellerActivity activity);


    @Provides
    @ActivityScoped
    static String provideStrOrderNo(Activity activity) {
        return activity.getIntent().getStringExtra("orderNo");
    }
}
