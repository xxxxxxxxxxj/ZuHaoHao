package com.haohao.zuhaohao.ui.module.setting.presenter;

import android.app.Application;

import androidx.appcompat.app.AlertDialog;

import com.blankj.utilcode.util.AppUtils;
import com.haohao.zuhaohao.AppApplication;
import com.haohao.zuhaohao.AppConfig;
import com.haohao.zuhaohao.data.db.help.UserBeanHelp;
import com.haohao.zuhaohao.data.network.ApiBuild;
import com.haohao.zuhaohao.ui.module.setting.contract.SettingContract;
import com.tencent.bugly.beta.Beta;

import javax.inject.Inject;

/**
 * 设置
 * date：2017/12/4 14:38
 * author：Seraph
 **/
public class SettingPresenter extends SettingContract.Presenter {

    private UserBeanHelp userBeanHelp;

    private Application application;
    /*@Inject
    private ApiBuild apiBuild;*/

    @Inject
    SettingPresenter(Application application, UserBeanHelp userBeanHelp) {
        this.userBeanHelp = userBeanHelp;
        this.application = application;
    }

    @Override
    public void start() {
        //获取程序版本号
        String appVersionName = AppUtils.getAppVersionName();
        mView.setAppCode(appVersionName, AppConfig.getBusinessNo());
    }


    public void doLogOut() {
        //注销
        new AlertDialog.Builder(mView.getContext())
                .setTitle("账号退出")
                .setMessage("您是否确定注销登录？").setNegativeButton("取消", null)
                .setPositiveButton("确定", (dialog, which) -> {
                    userBeanHelp.cleanUserBean();
                    ((AppApplication) application).addAndDeleteJPushAlias();
                    ApiBuild.clearCookie();
                    mView.finish();
                }).show();
    }

    //检查更新
    public void checkUpgrade() {
        Beta.checkUpgrade();
    }

}
