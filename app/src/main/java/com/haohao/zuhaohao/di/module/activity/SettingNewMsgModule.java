package com.haohao.zuhaohao.di.module.activity;

import android.app.Activity;

import com.haohao.zuhaohao.di.scoped.ActivityScoped;
import com.haohao.zuhaohao.ui.module.setting.SettingNewMsgActivity;

import dagger.Binds;
import dagger.Module;

/**
 * 设置新消息
 * date：2017/5/5 15:18
 * author：Seraph
 **/
@Module(includes = ActivityModule.class)
public abstract class SettingNewMsgModule {

    @Binds
    @ActivityScoped
    abstract Activity bindActivity(SettingNewMsgActivity activity);


}
