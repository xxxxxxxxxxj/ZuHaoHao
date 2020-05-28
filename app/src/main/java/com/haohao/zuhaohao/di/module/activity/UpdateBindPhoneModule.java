package com.haohao.zuhaohao.di.module.activity;

import android.app.Activity;

import com.haohao.zuhaohao.di.scoped.ActivityScoped;
import com.haohao.zuhaohao.ui.module.user.UpdateBindPhoneActivity;

import dagger.Binds;
import dagger.Module;

/**
 * 更改绑定手机
 * date：2017/5/5 15:18
 * author：Seraph
 **/
@Module(includes = ActivityModule.class)
public abstract class UpdateBindPhoneModule {

    @ActivityScoped
    @Binds
    abstract Activity bindActivity(UpdateBindPhoneActivity activity);


}
