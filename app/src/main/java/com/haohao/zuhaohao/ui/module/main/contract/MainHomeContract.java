package com.haohao.zuhaohao.ui.module.main.contract;

import com.haohao.zuhaohao.ui.module.base.BaseDataCms;
import com.haohao.zuhaohao.ui.module.base.IABaseContract;
import com.haohao.zuhaohao.ui.module.main.model.BannerBean;

import java.util.List;

/**
 * 首页
 * date：2017/2/20 17:06
 * author：Seraph
 **/
public interface MainHomeContract extends IABaseContract {

    interface View extends IBaseView {

        void setGameList(List<BaseDataCms<BannerBean>> bannerList);

        void setNoData(int type);
    }

    abstract class Presenter extends ABasePresenter<View> {

    }
}
