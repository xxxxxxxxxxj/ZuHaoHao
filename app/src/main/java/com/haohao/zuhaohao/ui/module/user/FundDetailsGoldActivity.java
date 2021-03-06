package com.haohao.zuhaohao.ui.module.user;

import android.os.Bundle;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.haohao.zuhaohao.AppConstants;
import com.haohao.zuhaohao.R;
import com.haohao.zuhaohao.databinding.ActUserGoldSmBinding;
import com.haohao.zuhaohao.ui.module.base.ABaseActivity;
import com.haohao.zuhaohao.ui.module.user.contract.FundDetailsGoldContract;
import com.haohao.zuhaohao.ui.module.user.presenter.FundDetailsGoldPresenter;

import javax.inject.Inject;

import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;

/**
 * 金币说明
 * date：2018/10/9 09:53
 * author：xiongj
 **/
@Route(path = AppConstants.PagePath.USER_FUNDDETAILS_JB, extras = AppConstants.ARAction.LOGIN)
public class FundDetailsGoldActivity extends ABaseActivity<FundDetailsGoldContract.Presenter> implements FundDetailsGoldContract.View {


    private ActUserGoldSmBinding binding;

    @Override
    protected void initContextView() {
        binding = DataBindingUtil.setContentView(this, R.layout.act_user_gold_sm);
    }


    @Inject
    FundDetailsGoldPresenter presenter;

    @Override
    protected FundDetailsGoldContract.Presenter getMVPPresenter() {
        return presenter;
    }


    @Override
    public void initCreate(@Nullable Bundle savedInstanceState) {
        binding.appbar.toolbar.setTitle("金币说明");
    }

}
