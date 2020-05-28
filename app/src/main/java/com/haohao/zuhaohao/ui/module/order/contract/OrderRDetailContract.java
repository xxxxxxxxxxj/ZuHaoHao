package com.haohao.zuhaohao.ui.module.order.contract;

import com.haohao.zuhaohao.ui.module.base.IABaseContract;
import com.haohao.zuhaohao.ui.module.order.model.OutOrderBean;

/**
 * 订单详情
 * date：2017/12/4 14:58
 * author：Seraph
 * mail：417753393@qq.com
 **/
public interface OrderRDetailContract extends IABaseContract {

    interface View extends IBaseView {

        void setOrderBean(OutOrderBean orderBean);

        void setNoDataView(int type);
    }

    abstract class Presenter extends ABasePresenter<View> {

    }

}
