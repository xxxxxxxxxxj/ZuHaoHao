package com.haohao.zuhaohao.ui.module.main;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;

import com.haohao.zuhaohao.R;
import com.haohao.zuhaohao.databinding.ActMainHomeHotgameBinding;
import com.haohao.zuhaohao.ui.module.account.adapter.AccAdapter;
import com.haohao.zuhaohao.ui.module.account.model.AccBean;
import com.haohao.zuhaohao.ui.module.base.ABaseFragment;
import com.haohao.zuhaohao.ui.module.main.contract.MainHomeAccListContract;
import com.haohao.zuhaohao.ui.module.main.presenter.MainHomeAccListPresenter;
import com.haohao.zuhaohao.utlis.LinearLayoutManager2;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnRefreshLoadMoreListener;
import com.umeng.analytics.MobclickAgent;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

/**
 * <p>Title:${type_name}</p>
 * <p>Description:首页推荐账号列表</p>
 * <p>Company:北京昊唐科技有限公司</p>
 *
 * @author 徐俊
 * @date zhoujunxia on 2020-05-29 18:17
 */
public class MainHomeAccList extends ABaseFragment<MainHomeAccListContract.Presenter> implements MainHomeAccListContract.View {

    private final String mPageName = "MainHomeAccList";

    private ActMainHomeHotgameBinding binding;
    @Inject
    MainHomeAccListPresenter presenter;

    private AccAdapter adapter;
    private List<AccBean> localHotGameList = new ArrayList<AccBean>();
    private int type;

    @Override
    protected View initDataBinding(LayoutInflater inflater, ViewGroup container) {
        binding = DataBindingUtil.inflate(inflater, R.layout.act_main_home_hotgame, container, false);
        return binding.getRoot();
    }

    @Override
    protected MainHomeAccListContract.Presenter getMVPPresenter() {
        return presenter;
    }

    @Override
    protected void initCreate(@Nullable Bundle savedInstanceState) {
        Bundle bundle = getArguments();
        //这里就拿到了之前传递的参数
        type = bundle.getInt("type");
        binding.rv.setLayoutManager(new LinearLayoutManager2(getActivity()));
        adapter = new AccAdapter(localHotGameList);
        binding.rv.setAdapter(adapter);
        adapter.setOnItemClickListener((adapter, view, position) -> presenter.onItemClick(position));
        binding.srl.setEnableRefresh(false);
        binding.srl.setOnRefreshLoadMoreListener(new OnRefreshLoadMoreListener() {
            @Override
            public void onLoadMore(@NonNull RefreshLayout refreshLayout) {
                presenter.nextPage();
            }

            @Override
            public void onRefresh(@NonNull RefreshLayout refreshLayout) {
            }
        });
        presenter.start();
    }

    @Override
    public void setGameList(List<AccBean> hotGameList) {
        Log.e("TAG", "setGameList = " + hotGameList.size());
        localHotGameList.clear();
        localHotGameList.addAll(hotGameList);
        adapter.notifyDataSetChanged();
        if (localHotGameList.size() <= 0) {
            binding.srl.setVisibility(View.GONE);
            binding.llHomehotgameNodata.setVisibility(View.VISIBLE);
        } else {
            binding.llHomehotgameNodata.setVisibility(View.GONE);
            binding.srl.setVisibility(View.VISIBLE);
        }
    }

    @Override
    public void notifyItemRangeChanged(int positionStart, int itemCount) {
        if (positionStart == 0 && itemCount == 0) {
            adapter.notifyDataSetChanged();
        } else {
            adapter.notifyItemRangeChanged(positionStart, itemCount);
        }
    }

    @Override
    public SmartRefreshLayout getSrl() {
        return binding.srl;
    }

    @Override
    public void onResume() {
        super.onResume();
        MobclickAgent.onPageStart(mPageName);
    }

    @Override
    public void onPause() {
        super.onPause();
        MobclickAgent.onPageEnd(mPageName);
    }
}
