package com.haohao.zuhaohao.ui.module.account.contract;

import com.haohao.zuhaohao.ui.module.account.model.AccRSResultBean;
import com.haohao.zuhaohao.ui.module.base.IABaseContract;

import java.util.List;

/**
 * 搜索
 * date：2017/11/28 11:15
 * author：Seraph
 * mail：417753393@qq.com
 **/
public interface AccRSearchContract extends IABaseContract {

    interface View extends IBaseView {

        void setSearchList(List<AccRSResultBean> gameBeans);

    }

    abstract class Presenter extends ABasePresenter<View> {

    }

}
