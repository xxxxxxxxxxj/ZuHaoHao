package com.haohao.zuhaohao.ui.module.main.contract;

import com.haohao.zuhaohao.ui.module.account.model.AccBean;
import com.haohao.zuhaohao.ui.module.base.IABaseContract;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;

import java.util.List;

/**
 * 首页
 * date：2017/2/20 17:06
 * author：Seraph
 **/
public interface MainHomeAccListContract extends IABaseContract {

    interface View extends IBaseView {

        void setGameList(List<AccBean> hotGameList);

        void notifyItemRangeChanged(int positionStart, int itemCount);

        SmartRefreshLayout getSrl();
    }

    abstract class Presenter extends ABasePresenter<View> {

    }
}
