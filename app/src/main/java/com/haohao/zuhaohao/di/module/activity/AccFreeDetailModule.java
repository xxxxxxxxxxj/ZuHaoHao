package com.haohao.zuhaohao.di.module.activity;

import android.app.Activity;

import com.haohao.zuhaohao.di.scoped.ActivityScoped;
import com.haohao.zuhaohao.ui.module.account.AccFreeDetailActivity;

import dagger.Binds;
import dagger.Module;
import dagger.Provides;

/**
 * 商品详情
 * date：2017/5/5 15:18
 * author：Seraph
 **/
@Module(includes = ActivityModule.class)
public abstract class AccFreeDetailModule {

    @ActivityScoped
    @Binds
    abstract Activity bindActivity(AccFreeDetailActivity activity);

    @Provides
    @ActivityScoped
    static String provideStringId(Activity activity) {
        return activity.getIntent().getStringExtra("id");
    }


}
