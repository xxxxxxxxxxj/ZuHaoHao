package com.haohao.zuhaohao.ui.module.main.contract;

import com.haohao.zuhaohao.ui.module.base.IABaseContract;

/**
 * 个人中心界面
 * date：2017/2/20 17:06
 * author：Seraph
 *
 **/
public interface MainMeContract extends IABaseContract {

    interface View extends IBaseView {
    }

    abstract class Presenter extends ABasePresenter<View> {

    }
}
