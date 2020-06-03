package com.haohao.zuhaohao.ui.module.user.presenter;

import com.blankj.utilcode.util.ToastUtils;
import com.haohao.zuhaohao.AppConfig;
import com.haohao.zuhaohao.data.db.help.UserBeanHelp;
import com.haohao.zuhaohao.data.db.table.UserTable;
import com.haohao.zuhaohao.data.network.rx.RxSchedulers;
import com.haohao.zuhaohao.data.network.service.ApiCommonService;
import com.haohao.zuhaohao.data.network.service.ApiPassportService;
import com.haohao.zuhaohao.data.network.service.ApiUserNewService;
import com.haohao.zuhaohao.ui.module.base.ABaseSubscriber;
import com.haohao.zuhaohao.ui.module.user.contract.UpdateBindPhoneContract;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.concurrent.TimeUnit;

import javax.inject.Inject;

import io.reactivex.Flowable;
import okhttp3.RequestBody;

/**
 * 更改绑定手机
 * date：2017/10/25 10:43
 * author：Seraph
 **/
public class UpdateBindPhonePresenter extends UpdateBindPhoneContract.Presenter {

    private ApiUserNewService apiUserNewService;

    private ApiPassportService apiPassportService;

    private ApiCommonService apiCommonService;

    private UserBeanHelp userBeanHelp;

    @Inject
    UpdateBindPhonePresenter(ApiCommonService apiCommonService, ApiPassportService apiPassportService, ApiUserNewService apiUserNewService, UserBeanHelp userBeanHelp) {
        this.apiUserNewService = apiUserNewService;
        this.apiPassportService = apiPassportService;
        this.apiCommonService = apiCommonService;
        this.userBeanHelp = userBeanHelp;
    }

    @Override
    public void start() {
    }


    //开始倒计时
    private void startCountdown(final int count) {
        Flowable.intervalRange(1, count, 0, 1, TimeUnit.SECONDS)
                .compose(RxSchedulers.io_main())
                .as(mView.bindLifecycle())
                .subscribe(aLong -> mView.setCountdownText(count - aLong));
    }


    //获取url连接验证
    public void onVerifyImageCode() {
        apiCommonService.getUrl(AppConfig.CLIENT_TYPE, null)
                .compose(RxSchedulers.io_main_business())
                .doOnSubscribe(subscription -> mView.showLoading().setOnDismissListener(dialog -> subscription.cancel()))
                .as(mView.bindLifecycle())
                .subscribe(new ABaseSubscriber<String>() {
                    @Override
                    public void onSuccess(String jsurl) {
                        mView.hideLoading();
                        mView.gotoVerifyFullScreenActivity(jsurl);
                    }

                    @Override
                    public void onError(String errStr) {
                        if ("601".equals(errStr)) {
                            mView.doGetCode();
                        } else {
                            mView.hideLoading();
                            ToastUtils.showShort(errStr);
                        }
                    }
                });
    }


    //获取验证码
    public void onGetCode(String phone, String ticket) {
        JSONObject jsonObject = new JSONObject();
        try {
            jsonObject.put("mobile", phone);
            jsonObject.put("ticket", ticket);
            jsonObject.put("businessNo", AppConfig.getChannelValue());
            jsonObject.put("businessId", null);
        } catch (JSONException e) {
            e.printStackTrace();
            ToastUtils.showShort("提交数据错误");
            return;
        }
        RequestBody jsonBody = RequestBody.create(okhttp3.MediaType.parse("application/json; charset=utf-8"), jsonObject.toString());
        apiCommonService.sendCode(jsonBody)
                .compose(RxSchedulers.io_main_business())
                .doOnSubscribe(subscription -> mView.showLoading("获取验证码").setOnDismissListener(dialog -> subscription.cancel()))
                .as(mView.bindLifecycle())
                .subscribe(new ABaseSubscriber<String>(mView) {

                    @Override
                    public void onSuccess(String str) {
                        ToastUtils.showShort("发送验证码成功");
                        //获取验证码成功，开始倒计时
                        startCountdown(60);
                    }

                    @Override
                    public void onError(String errStr) {
                        ToastUtils.showShort(errStr);
                    }
                });
    }

    //修改绑定手机成功
    public void onUpdateBindPhone(String phone, String code) {
        apiUserNewService.updatePhone(userBeanHelp.getAuthorization(), phone, code)
                .compose(RxSchedulers.io_main_business())
                .doOnSubscribe(subscription -> mView.showLoading().setOnDismissListener(d -> subscription.cancel()))
                .as(mView.bindLifecycle())
                .subscribe(new ABaseSubscriber<String>(mView) {
                    @Override
                    public void onSuccess(String s) {
                        ToastUtils.showShort("绑定手机号修改成功");
                        UserTable userTable = userBeanHelp.getUserBean();
                        userTable.setMobile(phone);
                        userBeanHelp.save(userTable);
                        mView.finish();
                    }

                    @Override
                    public void onError(String errStr) {
                        ToastUtils.showShort(errStr);
                    }
                });
    }

}

