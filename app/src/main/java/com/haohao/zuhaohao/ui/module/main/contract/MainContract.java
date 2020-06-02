package com.haohao.zuhaohao.ui.module.main.contract;

import com.haohao.zuhaohao.ui.module.base.IABaseContract;

/**
 * main契约类
 * date：2017/4/6 15:11
 * author：Seraph
 **/
public interface MainContract extends IABaseContract {

    interface View extends IBaseView {
    }

    abstract class Presenter extends ABasePresenter<View> {

    }

}
