package com.haohao.zuhaohao.ui.module.login;

import android.graphics.Color;
import android.os.Bundle;
import android.view.View;

import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.haohao.zuhaohao.AppConstants;
import com.haohao.zuhaohao.R;
import com.haohao.zuhaohao.databinding.ActLoginTypeSelectBinding;
import com.haohao.zuhaohao.ui.module.base.ABaseActivity;
import com.haohao.zuhaohao.ui.module.login.contract.LoginTypeSelectContract;
import com.haohao.zuhaohao.ui.module.login.presenter.LoginTypeSelectPresenter;
import com.hwangjr.rxbus.annotation.Subscribe;
import com.hwangjr.rxbus.annotation.Tag;

import javax.inject.Inject;

import cn.sharesdk.tencent.qq.QQ;
import cn.sharesdk.wechat.friends.Wechat;

/**
 * 登录类型选择
 * date：2017/10/25 10:28
 * author：Seraph
 **/
@Route(path = AppConstants.PagePath.LOGIN_TYPESELECT)
public class LoginTypeSelectActivity extends ABaseActivity<LoginTypeSelectContract.Presenter> implements LoginTypeSelectContract.View {

    private ActLoginTypeSelectBinding binding;

    @Override
    protected void initContextView() {
        binding = DataBindingUtil.setContentView(this, R.layout.act_login_type_select);
        binding.setActivity(this);
    }

    @Inject
    LoginTypeSelectPresenter presenter;

    @Override
    protected LoginTypeSelectContract.Presenter getMVPPresenter() {
        return presenter;
    }

    @Override
    public void initCreate(@Nullable Bundle savedInstanceState) {
        binding.appbar.appbar.setBackgroundColor(Color.TRANSPARENT);
        binding.appbar.toolbar.setBackgroundColor(Color.TRANSPARENT);
        presenter.start();
    }


    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.ll_wx:
                presenter.onOtherLogin(Wechat.NAME);
                break;
            case R.id.ll_qq:
                presenter.onOtherLogin(QQ.NAME);
                break;
            case R.id.ll_phone:
                presenter.startLogin();
                break;
        }
    }

    @Override
    public void finish() {
        super.finish();
        overridePendingTransition(R.anim.anim_null, R.anim.anim_slide_out_to_bottom);
    }

    @Subscribe(tags = {@Tag(AppConstants.RxBusAction.TAG_OTHER_LOGIN)})
    public void otherLogin(Boolean aBoolean) {
        if (aBoolean) {
            presenter.toLoginByOpenId();
        }
    }

}
