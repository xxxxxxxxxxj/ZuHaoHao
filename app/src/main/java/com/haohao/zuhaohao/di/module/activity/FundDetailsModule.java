package com.haohao.zuhaohao.di.module.activity;

import android.app.Activity;

import com.haohao.zuhaohao.di.scoped.ActivityScoped;
import com.haohao.zuhaohao.ui.module.user.FundDetailsActivity;

import dagger.Binds;
import dagger.Module;

/**
 * 资金明细
 * date：2017/5/5 15:18
 * author：Seraph
 **/
@Module(includes = ActivityModule.class)
public abstract class FundDetailsModule {

    @Binds
    @ActivityScoped
    abstract Activity bindActivity(FundDetailsActivity activity);


}
