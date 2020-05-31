package com.haohao.zuhaohao.ui.module.main.presenter;

import com.alibaba.android.arouter.launcher.ARouter;
import com.blankj.utilcode.util.ToastUtils;
import com.haohao.zuhaohao.AppConstants;
import com.haohao.zuhaohao.data.network.rx.RxSchedulers;
import com.haohao.zuhaohao.data.network.service.Api8Service;
import com.haohao.zuhaohao.ui.module.account.model.AccBean;
import com.haohao.zuhaohao.ui.module.base.ABaseSubscriber;
import com.haohao.zuhaohao.ui.module.base.BaseData;
import com.haohao.zuhaohao.ui.module.base.BaseDataCms;
import com.haohao.zuhaohao.ui.module.main.contract.MainHomeAccListContract;
import com.haohao.zuhaohao.ui.views.NoDataView;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

/**
 * 首页推荐账号列表逻辑处理层
 * date：2017/2/15 11:27
 * author：Seraph
 **/
public class MainHomeAccListPresenter extends MainHomeAccListContract.Presenter {
    private List<AccBean> list = new ArrayList<>();
    private boolean isFree;
    private Api8Service api8Service;

    @Inject
    MainHomeAccListPresenter(Api8Service api8Service) {
        this.api8Service = api8Service;
    }

    @Override
    public void start() {
        api8Service.getHotList()
                .compose(RxSchedulers.io_main_business())
                .doOnSubscribe(subscription -> mView.setNoData(NoDataView.LOADING))
                .as(mView.bindLifecycle())
                .subscribe(new ABaseSubscriber<BaseData<BaseDataCms<AccBean>>>() {
                    @Override
                    public void onSuccess(BaseData<BaseDataCms<AccBean>> accountList) {
                        if (accountList != null && accountList.datas.size() > 0) {
                            list.clear();
                            for (int i = 0; i < accountList.datas.size(); i++) {
                                BaseDataCms<AccBean> accBeanBaseDataCms = accountList.datas.get(i);
                                list.add(accBeanBaseDataCms.properties);
                            }
                            mView.setGameList(list);
                            mView.setNoData(NoDataView.LOADING_OK);
                        } else {
                            ToastUtils.showShort("数据为空");
                            mView.setNoData(NoDataView.NET_ERR);
                        }
                    }

                    @Override
                    public void onError(String errStr) {
                        ToastUtils.showShort(errStr);
                        mView.setNoData(NoDataView.NET_ERR);
                    }
                });
    }

    //跳转详情
    public void onItemClick(int position) {
        if (position >= list.size()) {
            ToastUtils.showShort("查看详情失败，请刷新列表后重试");
            return;
        }
        if (isFree) {
            ARouter.getInstance().build(AppConstants.PagePath.ACC_DETAIL_FREE).withString("id", list.get(position).id).navigation();
        } else {
            ARouter.getInstance().build(AppConstants.PagePath.ACC_DETAIL).withString("id", list.get(position).id).navigation();
        }
    }
}
