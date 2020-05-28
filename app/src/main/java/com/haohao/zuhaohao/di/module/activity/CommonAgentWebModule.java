package com.haohao.zuhaohao.di.module.activity;

import android.app.Activity;

import com.haohao.zuhaohao.di.QualifierType;
import com.haohao.zuhaohao.di.scoped.ActivityScoped;
import com.haohao.zuhaohao.ui.module.common.web.CommonAgentWebActivity;

import dagger.Binds;
import dagger.Module;
import dagger.Provides;

/**
 * 通用web界面
 * date：2017/5/5 15:18
 * author：Seraph
 *
 **/
@Module(includes = ActivityModule.class)
public abstract class CommonAgentWebModule {

    @Binds
    @ActivityScoped
    abstract Activity bindActivity(CommonAgentWebActivity activity);


    @QualifierType("webUrl")
    @Provides
    @ActivityScoped
    static String provideStrWebContent(Activity activity){
        return activity.getIntent().getStringExtra("webUrl");
    }

    @QualifierType("title")
    @Provides
    @ActivityScoped
    static String provideStrTitle(Activity activity){
        return activity.getIntent().getStringExtra("title");
    }
}
