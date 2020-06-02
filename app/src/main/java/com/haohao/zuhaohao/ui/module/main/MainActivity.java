package com.haohao.zuhaohao.ui.module.main;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.blankj.utilcode.util.FragmentUtils;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.bottomnavigation.LabelVisibilityMode;
import com.haohao.zuhaohao.AppConstants;
import com.haohao.zuhaohao.R;
import com.haohao.zuhaohao.databinding.ActMainBinding;
import com.haohao.zuhaohao.di.QualifierType;
import com.haohao.zuhaohao.ui.module.base.ABaseActivity;
import com.haohao.zuhaohao.ui.module.main.contract.MainContract;
import com.haohao.zuhaohao.ui.module.main.presenter.MainPresenter;
import com.haohao.zuhaohao.ui.views.SuccessfulReceiptDialog;
import com.hwangjr.rxbus.annotation.Subscribe;
import com.hwangjr.rxbus.annotation.Tag;

import java.util.List;

import javax.inject.Inject;

/**
 * 主界面
 * date：2017/2/15 11:24
 **/
@Route(path = AppConstants.PagePath.APP_MAIN)
public class MainActivity extends ABaseActivity<MainContract.Presenter> implements MainContract.View {

    private ActMainBinding binding;

    @Inject
    MainPresenter presenter;

    @Override
    protected MainContract.Presenter getMVPPresenter() {
        return presenter;
    }

    @Override
    protected void initContextView() {
        binding = DataBindingUtil.setContentView(this, R.layout.act_main);
    }

    //页面合集
    @QualifierType("main")
    @Inject
    List<Fragment> fragments;

    @Inject
    FragmentManager fragmentManager;

    @Override
    public void initCreate(@Nullable Bundle savedInstanceState) {
        //初始化layout
        initLayout();
    }

    @Override
    protected void onResume() {
        super.onResume();
        presenter.start();
    }

    private void initLayout() {
        //默认显示第一个
        FragmentUtils.removeAll(fragmentManager);
        FragmentUtils.add(fragmentManager, fragments, R.id.fl_home, 0);
        //设置底部导航效果
        binding.bnvMain.enableAnimation(false);
        binding.bnvMain.setLabelVisibilityMode(LabelVisibilityMode.LABEL_VISIBILITY_LABELED);
        binding.bnvMain.setItemHorizontalTranslationEnabled(false);
        //使用图片本身的颜色
        binding.bnvMain.setItemIconTintList(null);
        binding.bnvMain.setOnNavigationItemSelectedListener(bottomNavigationListener);
    }


    private BottomNavigationView.OnNavigationItemSelectedListener bottomNavigationListener = menuItem -> {
        int showIndex;
        switch (menuItem.getItemId()) {
            case R.id.item_1://首页
                showIndex = 0;
                break;
            case R.id.item_2://出租游戏列表
                presenter.doSelectLease();
                return false;/*
            case R.id.item_3://发布
                showIndex = 1;
                break;
            case R.id.item_4://订单
                presenter.doSelectOrder();
                return false;*/
            case R.id.item_5://我的
                showIndex = 2;
                break;
            default:
                showIndex = 0;
                break;
        }
        showIndexFragment(showIndex);
        return true;
    };


    /**
     * 显示对应位置的fragment
     *
     * @param showIndex 位置
     */
    private void showIndexFragment(int showIndex) {
        presenter.setSelectedFragment(showIndex);
        Fragment showFragment = fragments.get(showIndex);
        if (showFragment != null) {
            FragmentUtils.showHide(showFragment, fragments);
        }
    }


    @Override
    public void onBackPressed() {
        presenter.onBackPressed();
    }


    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        presenter.onSaveInstanceState(outState);
    }

    @Subscribe(tags = {@Tag(AppConstants.RxBusAction.TAG_MAIN_MENU)})
    public void clickMenuPosition(Integer position) {
        binding.bnvMain.setCurrentItem(position);
    }

    @Inject
    SuccessfulReceiptDialog receiptDialog;
}