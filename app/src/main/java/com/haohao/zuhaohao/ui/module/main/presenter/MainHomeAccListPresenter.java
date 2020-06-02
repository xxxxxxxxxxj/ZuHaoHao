package com.haohao.zuhaohao.ui.module.main.presenter;

import android.util.Log;

import com.alibaba.android.arouter.launcher.ARouter;
import com.blankj.utilcode.util.ToastUtils;
import com.haohao.zuhaohao.AppConfig;
import com.haohao.zuhaohao.AppConstants;
import com.haohao.zuhaohao.data.network.rx.RxSchedulers;
import com.haohao.zuhaohao.data.network.service.Api8Service;
import com.haohao.zuhaohao.ui.module.account.model.AccBean;
import com.haohao.zuhaohao.ui.module.base.ABaseSubscriber;
import com.haohao.zuhaohao.ui.module.base.BaseData;
import com.haohao.zuhaohao.ui.module.main.contract.MainHomeAccListContract;
import com.haohao.zuhaohao.ui.views.NoDataView;
import com.haohao.zuhaohao.utlis.PageUtils;

import java.util.HashMap;
import java.util.List;

import javax.inject.Inject;

/**
 * 首页推荐账号列表逻辑处理层
 * date：2017/2/15 11:27
 * author：Seraph
 **/
public class MainHomeAccListPresenter extends MainHomeAccListContract.Presenter {
    private NoDataView noDataView;
    private List<AccBean> list;
    private boolean isFree;
    private Api8Service api8Service;
    private int pageNo = 0;
    //选择平台
    private String selectPlatform;
    private String game_id;

    /*@Inject
    MainHomeAccListPresenter(Api8Service api8Service, String selectPlatform, String game_id, NoDataView noDataView, boolean isFree, List<AccBean> list) {
        this.api8Service = api8Service;
        this.selectPlatform = selectPlatform;
        this.game_id = game_id;
        this.noDataView = noDataView;
        this.isFree = isFree;
        this.list = list;
    }*/

    public void setData(String selectPlatform, String game_id, NoDataView noDataView, boolean isFree, List<AccBean> list) {
        this.selectPlatform = selectPlatform;
        this.game_id = game_id;
        this.noDataView = noDataView;
        this.isFree = isFree;
        this.list = list;
    }

    @Inject
    MainHomeAccListPresenter(Api8Service api8Service) {
        this.api8Service = api8Service;
    }

    @Override
    public void start() {
        mView.onAutoRefresh();
    }

    public void doRefresh() {
        //刷新第一页
        findGoodsList(1);
    }

    public void nextPage() {
        //下一页
        findGoodsList(pageNo + 1);
    }

    //查询游戏账号列表
    private void findGoodsList(final int tempPageNo) {
        HashMap<String, Object> map = new HashMap<>();
        //游戏id
        map.put("gameId", game_id);
        map.put("pageSize", AppConfig.PAGE_SIZE);
        map.put("pageIndex", tempPageNo);
        map.put("businessNo", AppConfig.getChannelValue());
        //系统筛选
        if (selectPlatform != null) {
            map.put("system", selectPlatform);
        }
        api8Service.findGoodsList(map)
                .compose(RxSchedulers.io_main_business())
                .doOnSubscribe(subscription -> noDataView.setType(NoDataView.LOADING))
                .as(mView.bindLifecycle())
                .subscribe(new ABaseSubscriber<BaseData<AccBean>>() {
                    @Override
                    public void onSuccess(BaseData<AccBean> accountList) {
                        if (tempPageNo == 1) {
                            if (accountList.list != null && accountList.list.size() > 0) {
                                Log.e("TAG","123");
                                mView.setVisiblity(0);
                            } else {
                                Log.e("TAG","456");
                                mView.setVisiblity(1);
                            }
                        }
                        pageNo = PageUtils.doSuccess(tempPageNo, list, accountList.list,
                                (start, size) -> mView.notifyItemRangeChanged(start, size), noDataView, mView.getSrl());
                    }

                    @Override
                    public void onError(String errStr) {
                        ToastUtils.showShort(errStr);
                        PageUtils.doError(noDataView, mView.getSrl());
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
