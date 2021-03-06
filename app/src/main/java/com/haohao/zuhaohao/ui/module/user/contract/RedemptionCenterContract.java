package com.haohao.zuhaohao.ui.module.user.contract;

import com.haohao.zuhaohao.ui.module.base.IABaseContract;
import com.haohao.zuhaohao.ui.module.user.model.RedemptionCenterBean;
import com.haohao.zuhaohao.ui.module.user.model.RedemptionGameBean;

import java.util.List;

/**
 * 兑换中心
 * date：2018/11/14 17:15
 * author：xiongj
 **/
public interface RedemptionCenterContract extends IABaseContract {

    interface View extends IBaseView {

        void initLayout(List<RedemptionCenterBean> list);

        void setNoDataView(int type);

        void notifyDataSetChanged();

        void showExchangePop(List<RedemptionGameBean> list);

        void onAutoRefresh();

    }

    abstract class Presenter extends ABasePresenter<View> {

    }


}
