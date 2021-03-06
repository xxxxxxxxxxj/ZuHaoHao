package com.haohao.zuhaohao.ui.module.welcome;


import com.blankj.utilcode.util.SPUtils;

import com.haohao.zuhaohao.AppConstants;
import com.haohao.zuhaohao.R;

import javax.inject.Inject;

/**
 * 欢迎页逻辑
 * date：2017/5/3 10:12
 * author：Seraph
 *
 **/
class GuidePagesActivityPresenter extends GuidePagesActivityContract.Presenter {


    private Integer[] images = new Integer[]{R.mipmap.welcome_guide_pages_one, R.mipmap.welcome_guide_pages_two, R.mipmap.welcome_guide_pages_three};

    @Inject
    GuidePagesActivityPresenter() {

    }

    @Override
    public void start() {
        SPUtils.getInstance(AppConstants.SPAction.SP_NAME).put(AppConstants.SPAction.IS_FIRST, false);
        //设置引导页
        mView.setImageList(images);
    }

    public void onItemClick(int position) {
        if (position == images.length - 1) {//最后一页点击跳转
            mView.jumpNextActivity();
        }
    }
}
