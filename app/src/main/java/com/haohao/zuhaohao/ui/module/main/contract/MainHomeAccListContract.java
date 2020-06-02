package com.haohao.zuhaohao.ui.module.main.contract;

import com.haohao.zuhaohao.ui.module.base.IABaseContract;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;

/**
 * 首页
 * date：2017/2/20 17:06
 * author：Seraph
 **/
public interface MainHomeAccListContract extends IABaseContract {

    interface View extends IBaseView {

        void setVisiblity(int type);

        void notifyItemRangeChanged(int positionStart, int itemCount);

        SmartRefreshLayout getSrl();

        void onAutoRefresh();
    }

    abstract class Presenter extends ABasePresenter<View> {

    }
}
