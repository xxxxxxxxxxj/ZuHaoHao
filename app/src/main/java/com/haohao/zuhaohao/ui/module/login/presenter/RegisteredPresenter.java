package com.haohao.zuhaohao.ui.module.login.presenter;

import com.alibaba.android.arouter.launcher.ARouter;
import com.blankj.utilcode.util.ToastUtils;
import com.haohao.zuhaohao.AppConfig;
import com.haohao.zuhaohao.AppConstants;
import com.haohao.zuhaohao.data.network.rx.RxSchedulers;
import com.haohao.zuhaohao.data.network.service.ApiCommonService;
import com.haohao.zuhaohao.data.network.service.ApiPassportService;
import com.haohao.zuhaohao.ui.module.base.ABaseSubscriber;
import com.haohao.zuhaohao.ui.module.login.contract.RegisteredContract;
import com.haohao.zuhaohao.ui.module.login.model.UserBean;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.concurrent.TimeUnit;

import javax.inject.Inject;

import io.reactivex.Flowable;
import okhttp3.RequestBody;

/**
 * 注册逻辑
 * date：2017/10/25 10:43
 * author：Seraph
 **/
public class RegisteredPresenter extends RegisteredContract.Presenter {


    private ApiPassportService apiPassportService;

    private ApiCommonService apiCommonService;

    @Inject
    RegisteredPresenter(ApiPassportService apiPassportService, ApiCommonService apiCommonService) {
        this.apiPassportService = apiPassportService;
        this.apiCommonService = apiCommonService;
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

    public void doLookAgreement() {
        //《号号分享平台服务协议》
        ARouter.getInstance().build(AppConstants.PagePath.COMM_AGENTWEB)
                .withString("title", "服务与隐私条款")
                .withString("webUrl", AppConstants.AgreementAction.AGREEMENT)
                .navigation();
    }

    //获取url连接验证
    public void onVerifyImageCode() {
        apiCommonService.getUrl(AppConfig.CLIENT_TYPE, 2)
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
        /*Map<String, String> fileParams = new HashMap<>();
        fileParams.put("mobile", phone);
        fileParams.put("ticket", ticket);
        fileParams.put("businessNo", AppConfig.getChannelValue());
        fileParams.put("businessId", "2");
        RequestBody requestBody = FileUploadHelp.multipartRequestBody(fileParams, null);*/

        JSONObject jsonObject = new JSONObject();
        try {
            jsonObject.put("mobile", phone);
            //jsonObject.put("ticket", ticket);
            jsonObject.put("businessNo", AppConfig.getChannelValue());
            jsonObject.put("businessId", "2");
        } catch (JSONException e) {
            e.printStackTrace();
            ToastUtils.showShort("提交数据错误");
            return;
        }
        RequestBody requestBody = RequestBody.create(okhttp3.MediaType.parse("application/json; charset=utf-8"), jsonObject.toString());
        apiCommonService.sendCode(requestBody)
                .compose(RxSchedulers.io_main_business())
                .doOnSubscribe(subscription -> mView.showLoading().setOnDismissListener(dialog -> subscription.cancel()))
                .as(mView.bindLifecycle())
                .subscribe(new ABaseSubscriber<String>(mView) {

                    @Override
                    public void onSuccess(String str) {
                        ToastUtils.showShort("发送验证码成功");
                        //获取验证码成功，开始倒计时
                        startCountdown(60);
                        if (AppConfig.BASE_HTTP_IP == 3) {
                            mView.doShowCodeOK(str);
                        }
                    }

                    @Override
                    public void onError(String errStr) {
                        ToastUtils.showShort(errStr);
                    }
                });
    }

    public void onRegistered(String phone, String code, String password) {
        apiPassportService.registerUser(phone, password, code, AppConfig.getChannelValue())
                .compose(RxSchedulers.io_main_business())
                .doOnSubscribe(subscription -> mView.showLoading("注册中").setOnDismissListener(dialog -> subscription.cancel()))
                .as(mView.bindLifecycle())
                .subscribe(new ABaseSubscriber<UserBean>(mView) {
                    @Override
                    public void onSuccess(UserBean userBean) {
                        ToastUtils.showShort("注册成功");
                        mView.finish();
                    }

                    @Override
                    public void onError(String errStr) {
                        ToastUtils.showShort(errStr);
                    }
                });
    }

}

