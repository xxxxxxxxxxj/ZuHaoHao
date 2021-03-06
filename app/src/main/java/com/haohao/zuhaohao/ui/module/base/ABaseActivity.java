package com.haohao.zuhaohao.ui.module.base;

import android.content.Context;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Lifecycle;

import com.haohao.zuhaohao.ui.views.CustomLoadingDialog;
import com.uber.autodispose.AutoDispose;
import com.uber.autodispose.AutoDisposeConverter;
import com.uber.autodispose.android.lifecycle.AndroidLifecycleScopeProvider;

import javax.inject.Inject;

import dagger.android.AndroidInjection;
import dagger.android.AndroidInjector;
import dagger.android.DispatchingAndroidInjector;
import dagger.android.support.HasSupportFragmentInjector;

/**
 * 所有的activity的父类继承，包含的一系列的常用操作
 * mvp结构设计
 *
 * @see #initContextView() 初始化对应的布局
 * @see #getMVPPresenter() 获取实现{@link IABaseContract.ABasePresenter}接口的实现类，也是mvp架构中的Presenter层
 * @see #initCreate(Bundle) 初始化之后的第一次调用相当于activity的{@link #onCreate(Bundle)}
 * 此类设计必须实现{@link IABaseContract.ABasePresenter}或者子类接口，以完成mvp架构中的View层
 * date：2017/2/15 09:09
 * author：Seraph
 *
 **/
public abstract class ABaseActivity<P extends IABaseContract.ABasePresenter> extends AppCompatActivity implements IABaseContract.IBaseView, HasSupportFragmentInjector {

    protected abstract void initContextView();

    protected abstract P getMVPPresenter();

    protected abstract void initCreate(@Nullable Bundle savedInstanceState);

    @Inject
    DispatchingAndroidInjector<Fragment> supportFragmentInjector;

    @Inject
    CustomLoadingDialog mLoadingDialog;

    protected P mPresenter;

    //自动解绑rxJava（在指定的生命周期）
    public <T> AutoDisposeConverter<T> bindLifecycle(Lifecycle.Event untilEvent) {
        return AutoDispose.autoDisposable(AndroidLifecycleScopeProvider.from(this, untilEvent));
    }
    //自动解绑rxJava（在结束的时候）
    public <T> AutoDisposeConverter<T> bindLifecycle() {
        return bindLifecycle(Lifecycle.Event.ON_DESTROY);
    }


    /**
     * ActivityLifecycleCallbacks回调在super中
     */
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        //一处声明，处处依赖注入
        AndroidInjection.inject(this);
        initContextView();
        super.onCreate(savedInstanceState);
        initMVP();
        initCreate(savedInstanceState);
    }


    @SuppressWarnings("unchecked")
    private void initMVP() {
        try {
            if (getMVPPresenter() == null) {
                return;
            }
            mPresenter = getMVPPresenter();
            mPresenter.setView(this);
        } catch (ClassCastException e) {
            throw new RuntimeException("子类必须实现IABaseContract.IBaseView接口");
        }
    }


    @Override
    public Context getContext() {
        return this;
    }

    @Override
    public CustomLoadingDialog showLoading() {
        return showLoading("");
    }

    @Override
    public CustomLoadingDialog showLoading(String str) {
        mLoadingDialog.setDialogMessage(str);
        return mLoadingDialog;
    }

    @Override
    public void hideLoading() {
        if (mLoadingDialog != null && mLoadingDialog.isShowing()) {
            mLoadingDialog.dismiss();
        }
    }

    @Override
    protected void onDestroy() {
        if (mLoadingDialog != null && mLoadingDialog.isShowing()) {
            mLoadingDialog.dismiss();
        }
        if (mPresenter != null) {
            mPresenter.onDestroy();
        }
        super.onDestroy();
    }

    @Override
    public AndroidInjector<Fragment> supportFragmentInjector() {
        return supportFragmentInjector;
    }



}
