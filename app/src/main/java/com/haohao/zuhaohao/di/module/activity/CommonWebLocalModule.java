package com.haohao.zuhaohao.di.module.activity;

import android.app.Activity;

import com.haohao.zuhaohao.di.QualifierType;
import com.haohao.zuhaohao.di.scoped.ActivityScoped;
import com.haohao.zuhaohao.ui.module.common.web.CommonWebLocalActivity;

import dagger.Binds;
import dagger.Module;
import dagger.Provides;

/**
 * 通用web界面(显示本地Str)
 * date：2017/5/5 15:18
 * author：Seraph
 **/
@Module(includes = ActivityModule.class)
public abstract class CommonWebLocalModule {

    @Binds
    @ActivityScoped
    abstract Activity bindActivity(CommonWebLocalActivity activity);


    @QualifierType("webContent")
    @Provides
    @ActivityScoped
    static String provideStrWebContent(Activity activity){
        return activity.getIntent().getStringExtra("webContent");
    }

    @QualifierType("title")
    @Provides
    @ActivityScoped
    static String provideStrTitle(Activity activity){
        return activity.getIntent().getStringExtra("title");
    }
}
