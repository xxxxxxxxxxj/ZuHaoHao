package com.haohao.zuhaohao.ui.module.main;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;

import com.haohao.zuhaohao.R;
import com.haohao.zuhaohao.databinding.ActMainHomeHotgameBinding;
import com.haohao.zuhaohao.ui.module.account.adapter.AccAdapter;
import com.haohao.zuhaohao.ui.module.account.model.AccBean;
import com.haohao.zuhaohao.ui.module.base.ABaseFragment;
import com.haohao.zuhaohao.ui.module.main.contract.MainHomeAccListContract;
import com.haohao.zuhaohao.ui.module.main.presenter.MainHomeAccListPresenter;
import com.haohao.zuhaohao.ui.views.NoDataView;
import com.haohao.zuhaohao.ui.views.NoScollFullLinearLayoutManager;
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
        binding.recyclerView.setHasFixedSize(true);
        binding.recyclerView.setNestedScrollingEnabled(false);
        NoScollFullLinearLayoutManager noScollFullLinearLayoutManager = new
                NoScollFullLinearLayoutManager(getActivity());
        noScollFullLinearLayoutManager.setScrollEnabled(false);
        binding.recyclerView.setLayoutManager(noScollFullLinearLayoutManager);
        adapter = new AccAdapter(localHotGameList);
        binding.recyclerView.setAdapter(adapter);
        adapter.setOnItemClickListener((adapter, view, position) -> presenter.onItemClick(position));
        presenter.start();
    }

    @Override
    public void setGameList(List<AccBean> hotGameList) {
        Log.e("TAG", "setGameList = " + hotGameList.size());
        localHotGameList.clear();
        for (int i = 0; i < hotGameList.size(); i++) {
            if (type == 0) {//安卓
                if (hotGameList.get(i).system == 0) {
                    localHotGameList.add(hotGameList.get(i));
                }
            } else {//苹果
                if (hotGameList.get(i).system == 1) {
                    localHotGameList.add(hotGameList.get(i));
                }
            }
        }
        adapter.notifyDataSetChanged();
        if (localHotGameList.size() <= 0) {
            binding.recyclerView.setVisibility(View.GONE);
            binding.llHomehotgameNodata.setVisibility(View.VISIBLE);
        }else{
            binding.llHomehotgameNodata.setVisibility(View.GONE);
            binding.recyclerView.setVisibility(View.VISIBLE);
        }
    }

    @Override
    public void setNoData(int type) {
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
