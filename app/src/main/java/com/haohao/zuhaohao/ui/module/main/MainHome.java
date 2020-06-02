package com.haohao.zuhaohao.ui.module.main;

import android.os.Bundle;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import com.alibaba.android.arouter.launcher.ARouter;
import com.blankj.utilcode.util.ConvertUtils;
import com.haohao.zuhaohao.AppConfig;
import com.haohao.zuhaohao.AppConstants;
import com.haohao.zuhaohao.R;
import com.haohao.zuhaohao.databinding.ActMainHomeBinding;
import com.haohao.zuhaohao.ui.module.account.model.AccBean;
import com.haohao.zuhaohao.ui.module.account.model.GameBean;
import com.haohao.zuhaohao.ui.module.base.ABaseFragment;
import com.haohao.zuhaohao.ui.module.base.BaseDataCms;
import com.haohao.zuhaohao.ui.module.main.adapter.HomeBannerAdapter;
import com.haohao.zuhaohao.ui.module.main.contract.MainHomeContract;
import com.haohao.zuhaohao.ui.module.main.model.BannerBean;
import com.haohao.zuhaohao.ui.module.main.model.HomeMultipleItem;
import com.haohao.zuhaohao.ui.module.main.model.WelfareBean;
import com.haohao.zuhaohao.ui.module.main.presenter.MainHomePresenter;
import com.tmall.ultraviewpager.UltraViewPager;
import com.umeng.analytics.MobclickAgent;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

/**
 * 首页（包含热门，推荐，banner，通知相关）
 * date：2017/2/20 16:38
 **/
public class MainHome extends ABaseFragment<MainHomeContract.Presenter> implements MainHomeContract.View {

    private final String mPageName = "MainHomeFragment";

    private ActMainHomeBinding binding;

    @Inject
    MainHomePresenter presenter;

    @Override
    protected MainHomeContract.Presenter getMVPPresenter() {
        return presenter;
    }

    @Override
    protected View initDataBinding(LayoutInflater inflater, ViewGroup container) {
        binding = DataBindingUtil.inflate(inflater, R.layout.act_main_home, container, false);
        binding.appbar.setHome(this);
        return binding.getRoot();
    }

    @Inject
    HomeBannerAdapter bannerAdapter;
    private List<MainHomeAccList> fragmentList = new ArrayList<MainHomeAccList>();
    private String[] strings = new String[]{"安卓", "苹果"};

    @Inject
    public MainHome() {
    }

    @Override
    public void initCreate(@Nullable Bundle savedInstanceState) {
        //tab分类
        for (int i = 0; i < strings.length; i++) {
            MainHomeAccList mainHomeAccList = new MainHomeAccList();
            Bundle bundle = new Bundle();
            bundle.putInt("type", i);
            mainHomeAccList.setArguments(bundle);
            fragmentList.add(mainHomeAccList);
        }
        MyAdapter fragmentAdater = new MyAdapter(getChildFragmentManager());
        binding.viewpager.setOffscreenPageLimit(2);
        binding.viewpager.setAdapter(fragmentAdater);
        binding.tabLayout.setupWithViewPager(binding.viewpager);

        //banner
        initUltraViewPager(binding.uvpBanner, position -> presenter.doBannerClick(position));
    }

    @Override
    public void onResume() {
        super.onResume();
        MobclickAgent.onPageStart(mPageName);
        presenter.start();
    }

    @Override
    public void onPause() {
        super.onPause();
        MobclickAgent.onPageEnd(mPageName);
    }


    @Override
    public void setGameList(List<BaseDataCms<BannerBean>> bannerList,
                            List<HomeMultipleItem> list,
                            List<BaseDataCms<AccBean>> hotList,
                            List<BaseDataCms<WelfareBean>> welfareList, List<BaseDataCms<GameBean>> hotRentList) {
        //第一个是banner
        bannerAdapter.repData(bannerList);
        binding.uvpBanner.refresh();
    }

    @Override
    public void setNoData(int type) {
    }


    /**
     * 初始化banner
     */
    private void initUltraViewPager(UltraViewPager ultraViewpager, HomeBannerAdapter.OnItemClickListener onItemClickListener) {
        ultraViewpager.setScrollMode(UltraViewPager.ScrollMode.HORIZONTAL);
        ultraViewpager.setHGap(100);
        ultraViewpager.setMultiScreen(0.95f);
        //UltraPagerAdapter 绑定子view到UltraViewPager
        ultraViewpager.setOffscreenPageLimit(5);
        bannerAdapter.setOnItemClickListener(onItemClickListener);
        ultraViewpager.setAdapter(bannerAdapter);
        //内置indicator初始化
        ultraViewpager.initIndicator();
        //设置indicator样式
        ultraViewpager.getIndicator()
                .setOrientation(UltraViewPager.Orientation.HORIZONTAL)
                .setFocusColor(0xFF0084FF)
                .setNormalColor(0xFF98A9BA)
                .setRadius((int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 3, getResources().getDisplayMetrics()));
        //设置indicator对齐方式
        ultraViewpager.getIndicator().setMargin(10, 10, 10, ConvertUtils.dp2px(10));
        ultraViewpager.getIndicator().setGravity(Gravity.CENTER_HORIZONTAL | Gravity.BOTTOM);
        //构造indicator,绑定到UltraViewPager
        ultraViewpager.getIndicator().build();
        //set an infinite loop
        ultraViewpager.setInfiniteLoop(true);
        //enable auto-scroll mode
        ultraViewpager.setAutoScroll(3000);
    }


    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.iv_msg://公告
                ARouter.getInstance().build(AppConstants.PagePath.USER_NOTICE).navigation();
                break;
            case R.id.tv_search://商品搜索
                ARouter.getInstance().build(AppConstants.PagePath.ACC_SEARCH).navigation();
                break;
            case R.id.iv_customer_service://客服
                ARouter.getInstance().build(AppConstants.PagePath.COMM_AGENTWEB)
                        .withString("title", "联系客服")
                        .withString("webUrl", AppConfig.CSCHAT_URL)
                        .navigation();
                break;
        }
    }

    public class MyAdapter extends FragmentPagerAdapter {
        public MyAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public int getCount() {
            return strings.length;
        }

        @Override
        public MainHomeAccList getItem(int position) {
            return fragmentList.get(position);
        }

        @Nullable
        @Override
        public CharSequence getPageTitle(int position) {
            return strings[position];
        }
    }
}
