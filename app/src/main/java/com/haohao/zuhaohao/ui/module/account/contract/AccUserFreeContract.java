package com.haohao.zuhaohao.ui.module.account.contract;

import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.haohao.zuhaohao.ui.module.account.model.UserAccFreeBean;
import com.haohao.zuhaohao.ui.module.base.IABaseContract;
import com.haohao.zuhaohao.ui.views.NoDataView;

import java.util.List;

/**
 * date：2017/11/28 11:15
 * author：Seraph
 * mail：417753393@qq.com
 **/
public interface AccUserFreeContract extends IABaseContract {

    interface View extends IBaseView {

        void initView(List<UserAccFreeBean> list, NoDataView noDataView);

        SmartRefreshLayout getSrl();

        void notifyItemRangeChanged(int start, int size);
    }

    abstract class Presenter extends ABasePresenter<View> {

    }

}
