package com.haohao.zuhaohao.di.module.activity;

import android.app.Activity;

import com.haohao.zuhaohao.di.scoped.ActivityScoped;
import com.haohao.zuhaohao.ui.module.user.FreezeDetailsActivity;

import dagger.Binds;
import dagger.Module;

/**
 * 冻结明细
 * date：2017/5/5 15:18
 * author：Seraph
 **/
@Module(includes = ActivityModule.class)
public abstract class FreezeDetailsModule {

    @Binds
    @ActivityScoped
    abstract Activity bindActivity(FreezeDetailsActivity activity);


}
