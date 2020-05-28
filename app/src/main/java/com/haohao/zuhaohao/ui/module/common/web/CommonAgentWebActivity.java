package com.haohao.zuhaohao.ui.module.common.web;

import android.os.Bundle;
import android.widget.LinearLayout;

import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.haohao.zuhaohao.AppConstants;
import com.haohao.zuhaohao.R;
import com.haohao.zuhaohao.databinding.CommonActWebAgentBinding;
import com.haohao.zuhaohao.di.QualifierType;
import com.haohao.zuhaohao.ui.module.base.ABaseActivity;
import com.haohao.zuhaohao.ui.module.base.IABaseContract;
import com.just.agentweb.AgentWeb;

import javax.inject.Inject;

/**
 * 通用AgentWeb界面
 * date：2018/3/5 19:20
 * author：Seraph
 *
 **/
@Route(path = AppConstants.PagePath.COMM_AGENTWEB)
public class CommonAgentWebActivity extends ABaseActivity<IABaseContract.ABasePresenter> {

    private CommonActWebAgentBinding binding;

    @Override
    protected void initContextView() {
        binding = DataBindingUtil.setContentView(this, R.layout.common_act_web_agent);
    }

    @Override
    protected IABaseContract.ABasePresenter getMVPPresenter() {
        return null;
    }

    @QualifierType("webUrl")
    @Inject
    String webUrl;

    @QualifierType("title")
    @Inject
    String title;

    @Override
    public void initCreate(@Nullable Bundle savedInstanceState) {
        binding.appbar.toolbar.setTitle(title);
        initLayout();
    }

    private AgentWeb mAgentWeb;

    private void initLayout() {
        mAgentWeb = AgentWeb.with(this)
                .setAgentWebParent(binding.llRoot, new LinearLayout.LayoutParams(-1, -1))
                .useDefaultIndicator()
                .createAgentWeb()
                .ready()
                .go(webUrl);
    }

    @Override
    protected void onPause() {
        mAgentWeb.getWebLifeCycle().onPause();
        super.onPause();

    }

    @Override
    protected void onResume() {
        mAgentWeb.getWebLifeCycle().onResume();
        super.onResume();
    }

    @Override
    public void onDestroy() {
        mAgentWeb.getWebLifeCycle().onDestroy();
        super.onDestroy();
    }

}
