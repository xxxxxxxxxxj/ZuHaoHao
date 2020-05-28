package com.haohao.zuhaohao.di.module.activity;

import android.app.Activity;

import com.haohao.zuhaohao.di.scoped.ActivityScoped;
import com.haohao.zuhaohao.ui.module.user.AlipayAddActivity;

import dagger.Binds;
import dagger.Module;

/**
 * 绑定支付宝
 * date：2017/5/5 15:18
 * author：Seraph
 **/
@Module(includes = ActivityModule.class)
public abstract class AlipayAddModule {

    @ActivityScoped
    @Binds
    abstract Activity bindActivity(AlipayAddActivity activity);
}
