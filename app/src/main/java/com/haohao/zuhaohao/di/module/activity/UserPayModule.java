package com.haohao.zuhaohao.di.module.activity;

import android.app.Activity;

import com.haohao.zuhaohao.di.scoped.ActivityScoped;
import com.haohao.zuhaohao.ui.module.user.UserPayActivity;

import dagger.Binds;
import dagger.Module;

/**
 * 用户充值
 * date：2017/5/5 15:18
 * author：Seraph
 *
 **/
@Module(includes = ActivityModule.class)
public abstract class UserPayModule {

    @ActivityScoped
    @Binds
    abstract Activity bindActivity(UserPayActivity activity);
}
