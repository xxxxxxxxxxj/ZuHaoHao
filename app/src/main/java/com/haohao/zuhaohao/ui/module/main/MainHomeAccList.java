package com.haohao.zuhaohao.ui.module.main;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;

import com.haohao.zuhaohao.R;
import com.haohao.zuhaohao.databinding.ActMainHomeBinding;
import com.haohao.zuhaohao.ui.module.account.model.AccBean;
import com.haohao.zuhaohao.ui.module.base.ABaseFragment;
import com.haohao.zuhaohao.ui.module.base.BaseDataCms;
import com.haohao.zuhaohao.ui.module.main.contract.MainHomeAccListContract;
import com.haohao.zuhaohao.ui.module.main.presenter.MainHomeAccListPresenter;

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
    private int type;

    public MainHomeAccList(int type) {
        super();
    }

    private final String mPageName = "MainHomeAccList";

    private ActMainHomeBinding binding;

    @Inject
    MainHomeAccListPresenter presenter;

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

    }

    @Override
    public void setGameList(List<BaseDataCms<AccBean>> hotGameList) {

    }

    @Override
    public void setNoData(int type) {

    }
}
