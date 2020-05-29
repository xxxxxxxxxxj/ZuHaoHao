package com.haohao.zuhaohao.ui.module.main.presenter;

import com.haohao.zuhaohao.data.db.help.UserBeanHelp;
import com.haohao.zuhaohao.ui.module.main.contract.MainHomeAccListContract;

import javax.inject.Inject;

/**
 * 首页推荐账号列表逻辑处理层
 * date：2017/2/15 11:27
 * author：Seraph
 **/
public class MainHomeAccListPresenter extends MainHomeAccListContract.Presenter {

    @Inject
    MainHomeAccListPresenter(UserBeanHelp userBeanHelp) {
    }

    @Override
    public void start() {

    }
}
