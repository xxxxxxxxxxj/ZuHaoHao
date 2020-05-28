package com.haohao.zuhaohao.di.module.activity;

import android.app.Activity;

import com.haohao.zuhaohao.di.scoped.ActivityScoped;
import com.haohao.zuhaohao.ui.module.login.RegisteredActivity;

import dagger.Binds;
import dagger.Module;

/**
 * 注册模型
 * date：2017/5/5 15:18
 * author：Seraph
 **/
@Module(includes = ActivityModule.class)
public abstract class RegisteredModule {

    @ActivityScoped
    @Binds
    abstract Activity bindActivity(RegisteredActivity activity);


}
