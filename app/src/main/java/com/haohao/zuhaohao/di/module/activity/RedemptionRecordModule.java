package com.haohao.zuhaohao.di.module.activity;

import android.app.Activity;
import android.content.ClipboardManager;
import android.content.Context;

import com.haohao.zuhaohao.di.scoped.ActivityScoped;
import com.haohao.zuhaohao.ui.module.user.RedemptionRecordActivity;

import dagger.Binds;
import dagger.Module;
import dagger.Provides;

/**
 * 兑换记录
 * date：2017/5/5 15:18
 * author：Seraph
 **/
@Module(includes = ActivityModule.class)
public abstract class RedemptionRecordModule {

    @Binds
    @ActivityScoped
    abstract Activity bindActivity(RedemptionRecordActivity activity);


    @Provides
    @ActivityScoped
    static ClipboardManager provideClipboardManager(Activity activity) {
        return (ClipboardManager) activity.getSystemService(Context.CLIPBOARD_SERVICE);
    }
}
