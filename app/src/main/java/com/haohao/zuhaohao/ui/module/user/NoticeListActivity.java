package com.haohao.zuhaohao.ui.module.user;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.haohao.zuhaohao.AppConstants;
import com.haohao.zuhaohao.R;
import com.haohao.zuhaohao.databinding.ActNoticeListBinding;
import com.haohao.zuhaohao.ui.module.base.ABaseActivity;
import com.haohao.zuhaohao.ui.module.main.model.NoticeBean;
import com.haohao.zuhaohao.ui.module.user.adapter.NoticeListAdapter;
import com.haohao.zuhaohao.ui.module.user.contract.NoticeListContract;
import com.haohao.zuhaohao.ui.module.user.presenter.NoticeListPresenter;
import com.haohao.zuhaohao.utlis.LinearLayoutManager2;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnRefreshLoadMoreListener;

import java.util.List;

import javax.inject.Inject;

/**
 * 公告
 * date：2018/5/29 17:43
 * author：Seraph
 **/
@Route(path = AppConstants.PagePath.USER_NOTICE)
public class NoticeListActivity extends ABaseActivity<NoticeListContract.Presenter> implements NoticeListContract.View {

    private ActNoticeListBinding binding;

    @Inject
    NoticeListPresenter presenter;

    @Override
    protected void initContextView() {
        binding = DataBindingUtil.setContentView(this, R.layout.act_notice_list);
    }

    private NoticeListAdapter adapter;

    @Override
    protected NoticeListContract.Presenter getMVPPresenter() {
        return presenter;
    }

    @Inject
    LinearLayoutManager2 linearLayoutManager;


    @Override
    public void initCreate(@Nullable Bundle savedInstanceState) {
        binding.appbar.toolbar.setTitle("公告");
        presenter.start();
    }

    public void initLayout(List<NoticeBean> list) {
        adapter = new NoticeListAdapter(list);
        binding.ccl.recyclerview.setLayoutManager(linearLayoutManager);
        binding.ccl.recyclerview.setAdapter(adapter);
        adapter.setOnItemClickListener((adapter, view, position) -> presenter.onItemClick(position));
        binding.ccl.refreshLayout.setOnRefreshLoadMoreListener(new OnRefreshLoadMoreListener() {
            @Override
            public void onLoadMore(@NonNull RefreshLayout refreshLayout) {
                presenter.onNextPage();
            }

            @Override
            public void onRefresh(@NonNull RefreshLayout refreshLayout) {
                presenter.onRefresh();
            }
        });
        binding.ccl.ndv.setOnClickListener(v -> presenter.onRefresh());
    }


    @Override
    public void setNoDataView(int type) {
        binding.ccl.refreshLayout.finishRefresh();
        binding.ccl.refreshLayout.finishLoadMore();
        binding.ccl.ndv.setType(type);
    }

    @Override
    public void notifyItemRangeChanged(int positionStart, int itemCount) {
        if (positionStart >= 0) {
            adapter.notifyItemRangeChanged(positionStart, itemCount);
        }
    }

}
