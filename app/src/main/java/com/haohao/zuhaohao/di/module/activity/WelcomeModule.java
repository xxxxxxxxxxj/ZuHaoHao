package com.haohao.zuhaohao.di.module.activity;

import android.app.Activity;

import com.haohao.zuhaohao.di.scoped.ActivityScoped;
import com.haohao.zuhaohao.ui.module.welcome.WelcomeActivity;

import dagger.Binds;
import dagger.Module;

/**
 * 欢迎页
 * date：2017/5/5 15:18
 * author：Seraph
 *
 **/
@Module(includes = ActivityModule.class)
public abstract class WelcomeModule {

    @ActivityScoped
    @Binds
    abstract Activity bindContext(WelcomeActivity activity);

}
