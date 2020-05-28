package com.haohao.zuhaohao.di.module.activity;

import android.app.Activity;

import com.haohao.zuhaohao.di.scoped.ActivityScoped;
import com.haohao.zuhaohao.ui.module.user.AlipayModifyActivity;

import dagger.Binds;
import dagger.Module;

/**
 * 修改支付宝界面
 * date：2017/5/5 15:18
 * author：Seraph
 *
 **/
@Module(includes = ActivityModule.class)
public abstract class AlipayModifyModule {

    @ActivityScoped
    @Binds
    abstract Activity bindActivity(AlipayModifyActivity activity);
}
