package com.haohao.zuhaohao.ui.module.setting;

import android.os.Bundle;
import android.view.Menu;
import android.view.View;

import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.GridLayoutManager;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.alibaba.android.arouter.launcher.ARouter;
import com.haohao.zuhaohao.AppConfig;
import com.haohao.zuhaohao.AppConstants;
import com.haohao.zuhaohao.R;
import com.haohao.zuhaohao.databinding.ActSettingHelpCenterBinding;
import com.haohao.zuhaohao.ui.module.base.ABaseActivity;
import com.haohao.zuhaohao.ui.module.setting.adapter.HelpCenterAdapter;
import com.haohao.zuhaohao.ui.module.setting.contract.HelpCenterContract;
import com.haohao.zuhaohao.ui.module.setting.model.ProblemBean;
import com.haohao.zuhaohao.ui.module.setting.presenter.HelpCenterPresenter;
import com.jakewharton.rxbinding2.support.v7.widget.RxToolbar;

import javax.inject.Inject;

/**
 * 帮助中心
 * date：2018/7/31 14:17
 * author：Seraph
 **/
@Route(path = AppConstants.PagePath.SETTING_HELPCENTER)
public class HelpCenterActivity extends ABaseActivity<HelpCenterContract.Presenter> implements HelpCenterContract.View {


    private ActSettingHelpCenterBinding binding;

    @Override
    protected void initContextView() {
        binding = DataBindingUtil.setContentView(this, R.layout.act_setting_help_center);
        binding.setActivity(this);
    }

    @Inject
    HelpCenterPresenter presenter;

    @Override
    protected HelpCenterContract.Presenter getMVPPresenter() {
        return presenter;
    }

    @Inject
    GridLayoutManager layoutManager;

    @Inject
    HelpCenterAdapter adapter;

    @Override
    public void initCreate(@Nullable Bundle savedInstanceState) {
        binding.appbar.toolbar.setTitle("帮助中心");
        binding.recyclerview.setLayoutManager(layoutManager);
        binding.recyclerview.setAdapter(adapter);
        adapter.setOnItemClickListener((adapter, view, position) -> {
            ProblemBean bean = (ProblemBean) adapter.getItem(position);
            ARouter.getInstance()
                    .build(AppConstants.PagePath.SETTING_HELPLIST)
                    .withSerializable("bean", bean)
                    .navigation();
        });
        RxToolbar.itemClicks(binding.appbar.toolbar).as(bindLifecycle()).subscribe(menuItem ->
                ARouter.getInstance().build(AppConstants.PagePath.COMM_AGENTWEB)
                        .withString("title", "联系客服")
                        .withString("webUrl", AppConfig.CSCHAT_URL)
                        .navigation());
    }

    public void onViewClicked(View view) {
        presenter.doCopyAddress();
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_help_center, menu);
        return super.onCreateOptionsMenu(menu);
    }

}
