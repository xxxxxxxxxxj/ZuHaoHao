package com.haohao.zuhaohao.ui.module.setting;

import android.os.Bundle;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.alibaba.android.arouter.launcher.ARouter;
import com.haohao.zuhaohao.AppConstants;
import com.haohao.zuhaohao.R;
import com.haohao.zuhaohao.databinding.ActSettingHelpListBinding;
import com.haohao.zuhaohao.ui.module.base.ABaseActivity;
import com.haohao.zuhaohao.ui.module.setting.adapter.HelpListAdapter;
import com.haohao.zuhaohao.ui.module.setting.contract.HelpListContract;
import com.haohao.zuhaohao.ui.module.setting.model.QuestionBean;
import com.haohao.zuhaohao.ui.module.setting.presenter.HelpListPresenter;
import com.haohao.zuhaohao.utlis.LinearLayoutManager2;

import java.util.List;

import javax.inject.Inject;

import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.DividerItemDecoration;

/**
 * 帮助类型列表
 * date：2018/5/31 10:11
 * author：Seraph
 **/
@Route(path = AppConstants.PagePath.SETTING_HELPLIST)
public class HelpListActivity extends ABaseActivity<HelpListContract.Presenter> implements HelpListContract.View {

    private ActSettingHelpListBinding binding;

    @Override
    protected void initContextView() {
        binding = DataBindingUtil.setContentView(this, R.layout.act_setting_help_list);
    }

    @Inject
    HelpListPresenter presenter;

    @Override
    protected HelpListContract.Presenter getMVPPresenter() {
        return presenter;
    }


    @Inject
    LinearLayoutManager2 layoutManager;

    @Inject
    DividerItemDecoration itemDecoration;

    @Inject
    HelpListAdapter adapter;

    @Override
    public void initCreate(@Nullable Bundle savedInstanceState) {
        presenter.start();
        binding.ndv.setOnClickListener(v -> presenter.getArticleList());
    }


    @Override
    public void setHelpTypeTitle(String title) {
        binding.appbar.toolbar.setTitle(title);
        binding.recyclerview.setLayoutManager(layoutManager);
        binding.recyclerview.addItemDecoration(itemDecoration);
        binding.recyclerview.setAdapter(adapter);
        adapter.setOnItemClickListener((adapter, view, position) -> {
            QuestionBean questionBean = (QuestionBean) adapter.getItem(position);
            ARouter.getInstance().build(AppConstants.PagePath.COMM_WEBLOCAL)
                    .withString("title", questionBean.articleTitle)
                    .withString("webContent", questionBean.articleContent)
                    .navigation();
        });
    }

    @Override
    public void setQuestionList(List<QuestionBean> list) {
        adapter.replaceData(list);
    }

    @Override
    public void setNoDataView(int type) {
        binding.ndv.setType(type);
    }


}
