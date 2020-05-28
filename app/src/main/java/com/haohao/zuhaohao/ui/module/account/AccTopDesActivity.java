package com.haohao.zuhaohao.ui.module.account;

import android.os.Bundle;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.haohao.zuhaohao.AppConstants;
import com.haohao.zuhaohao.R;
import com.haohao.zuhaohao.databinding.ActAccTopDesBinding;
import com.haohao.zuhaohao.ui.module.base.ABaseActivity;
import com.haohao.zuhaohao.ui.module.base.IABaseContract;

import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;

/**
 * 置顶说明
 * date：2018/12/5 15:59
 * author：xiongj
 **/
@Route(path = AppConstants.PagePath.ACC_TOPDES)
public class AccTopDesActivity extends ABaseActivity<IABaseContract.ABasePresenter> implements IABaseContract.IBaseView {

    private ActAccTopDesBinding binding;

    @Override
    protected void initContextView() {
        binding = DataBindingUtil.setContentView(this, R.layout.act_acc_top_des);
    }

    @Override
    protected IABaseContract.ABasePresenter getMVPPresenter() {
        return null;
    }

    @Override
    protected void initCreate(@Nullable Bundle savedInstanceState) {
        binding.appbar.toolbar.setTitle("置顶说明");
    }
}
