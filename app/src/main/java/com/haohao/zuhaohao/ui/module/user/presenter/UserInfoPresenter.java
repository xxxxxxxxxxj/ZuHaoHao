package com.haohao.zuhaohao.ui.module.user.presenter;

import android.net.Uri;
import android.util.Log;

import com.alibaba.sdk.android.oss.ClientConfiguration;
import com.alibaba.sdk.android.oss.OSS;
import com.alibaba.sdk.android.oss.OSSClient;
import com.alibaba.sdk.android.oss.common.OSSLog;
import com.alibaba.sdk.android.oss.common.auth.OSSAuthCredentialsProvider;
import com.alibaba.sdk.android.oss.common.auth.OSSCredentialProvider;
import com.blankj.utilcode.util.ToastUtils;
import com.haohao.zuhaohao.AppConfig;
import com.haohao.zuhaohao.data.db.help.UserBeanHelp;
import com.haohao.zuhaohao.data.db.table.UserTable;
import com.haohao.zuhaohao.data.network.FileUploadHelp;
import com.haohao.zuhaohao.data.network.rx.RxSchedulers;
import com.haohao.zuhaohao.data.network.service.ApiGoodsService;
import com.haohao.zuhaohao.data.network.service.ApiUserNewService;
import com.haohao.zuhaohao.oss.OssService;
import com.haohao.zuhaohao.ui.module.base.ABaseSubscriber;
import com.haohao.zuhaohao.ui.module.base.BaseData;
import com.haohao.zuhaohao.ui.module.user.contract.UserInfoContract;
import com.haohao.zuhaohao.ui.module.user.model.AcctManageBean;
import com.haohao.zuhaohao.utlis.PathUtils;
import com.haohao.zuhaohao.utlis.TakePhoto;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

import javax.inject.Inject;

/**
 * 个人中心
 * date：2017/12/4 15:04
 * author：Seraph
 **/
public class UserInfoPresenter extends UserInfoContract.Presenter {


    private UserBeanHelp userBeanHelp;

    private ApiUserNewService apiUserNewService;

    private ApiGoodsService apiGoodsService;

    private TakePhoto mTakePhoto;
    private OssService ossService;

    @Inject
    UserInfoPresenter(UserBeanHelp userBeanHelp, ApiUserNewService apiUserNewService, ApiGoodsService apiGoodsService, TakePhoto takePhoto) {
        this.userBeanHelp = userBeanHelp;
        this.apiGoodsService = apiGoodsService;
        this.mTakePhoto = takePhoto;
        this.apiUserNewService = apiUserNewService;
    }

    @Override
    public void start() {
        ossService = initOSS();
        updateUserBean();
    }

    //初始化oss
    public OssService initOSS() {
//        移动端是不安全环境，不建议直接使用阿里云主账号ak，sk的方式。建议使用STS方式。具体参
//        https://help.aliyun.com/document_detail/31920.html
//        注意：SDK 提供的 PlainTextAKSKCredentialProvider 只建议在测试环境或者用户可以保证阿里云主账号AK，SK安全的前提下使用。具体使用如下
//        主账户使用方式
//        String AK = "******";
//        String SK = "******";
//        credentialProvider = new PlainTextAKSKCredentialProvider(AK,SK)
//        以下是使用STS Sever方式。
//        如果用STS鉴权模式，推荐使用OSSAuthCredentialProvider方式直接访问鉴权应用服务器，token过期后可以自动更新。
//        详见：https://help.aliyun.com/document_detail/31920.html
//        OSSClient的生命周期和应用程序的生命周期保持一致即可。在应用程序启动时创建一个ossClient，在应用程序结束时销毁即可。
        //使用自己的获取STSToken的类
        OSSCredentialProvider credentialProvider = new OSSAuthCredentialsProvider(AppConfig.STS_SERVER_URL);
        ClientConfiguration conf = new ClientConfiguration();
        conf.setConnectionTimeout(15 * 1000); // 连接超时，默认15秒
        conf.setSocketTimeout(15 * 1000); // socket超时，默认15秒
        conf.setMaxConcurrentRequest(5); // 最大并发请求书，默认5个
        conf.setMaxErrorRetry(2); // 失败后最大重试次数，默认2次
        OSS oss = new OSSClient(mView.getContext(), AppConfig.OSS_ENDPOINT, credentialProvider, conf);
        OSSLog.enableLog();
        return new OssService(oss, AppConfig.BUCKET_NAME);

    }

    //拍照
    public void doTakePhoto() {
        mTakePhoto.doTakePhoto();
    }

    //选择相册
    public void doPickPhotoFromGallery() {
        mTakePhoto.doPickPhotoFromGallery();
    }

    //拍照返回
    public void onCameraComplete() {
        Uri uri = Uri.fromFile(mTakePhoto.getCurrentPhotoFile());
        mView.onUCropImage(uri, uri);
    }

    //照片返回
    public void onPhotoComplete(Uri data) {
        mView.onUCropImage(data, Uri.fromFile(mTakePhoto.getNewPhotoFile()));
    }

    //图片剪切返回
    public void onUCropResult(Uri output) {
        String path = PathUtils.getPath(mView.getContext(), output);
        String fileName = PathUtils.getFileName(path);
        Log.e("TAG", "path = " + path);
        Log.e("TAG", "fileName = " + fileName);
        ossService.asyncPutImage(fileName, path);
        /*//上传图片
        try {
            upTempImage(new File(new URI(output.toString())));
        } catch (URISyntaxException e) {
            e.printStackTrace();
        }
*/
    }

    //上传图片到服务器
    private void upTempImage(File file) {
        Map<String, File> fileParams = new HashMap<>();
        fileParams.put("file", file);
        apiGoodsService.upTemp(FileUploadHelp.multipartRequestBody(null, fileParams))
                .compose(RxSchedulers.io_main_business())
                .doOnSubscribe(subscription -> mView.showLoading("上传图片").setOnDismissListener(dialog -> subscription.cancel()))
                .as(mView.bindLifecycle())
                .subscribe(new ABaseSubscriber<BaseData<String>>() {
                    @Override
                    public void onSuccess(BaseData<String> stringBaseData) {
                        //得到上传后的网络地址，再更新到头像
                        updataAvatar(stringBaseData.image_path);
                    }

                    @Override
                    public void onError(String errStr) {
                        mView.hideLoading();
                        ToastUtils.showShort(errStr);
                    }
                });
    }


    //更新用户资料
    private void updateUserBean() {
        mView.setUserInfo(userBeanHelp.getUserBean());
    }

    //检查是否实名认证
    public void doCheckAuth() {
        apiUserNewService.acctManageIndex(userBeanHelp.getAuthorization())
                .compose(RxSchedulers.io_main_business())
                .doOnSubscribe(subscription -> mView.showLoading().setOnDismissListener(dialog -> subscription.cancel()))
                .as(mView.bindLifecycle())
                .subscribe(new ABaseSubscriber<AcctManageBean>(mView) {
                    @Override
                    public void onSuccess(AcctManageBean acctManageBean) {
                        if (acctManageBean.isCertificate) {
                            //已经验证
                            ToastUtils.showShort("您已验证");
                        } else {
                            //跳转简单认证界面
                            mView.setIdCartAuth();
                        }
                    }

                    @Override
                    public void onError(String errStr) {
                        ToastUtils.showShort(errStr);
                    }
                });

    }


    //更新头像
    private void updataAvatar(final String image_path) {
        apiUserNewService.updateHeadPortrait(userBeanHelp.getAuthorization(), image_path)
                .compose(RxSchedulers.io_main_business())
                .subscribe(new ABaseSubscriber<String>(mView) {
                    @Override
                    public void onSuccess(String s) {
                        //更新头像成功
                        ToastUtils.showShort("保存头像成功");
                        UserTable userTable = userBeanHelp.getUserBean();
                        userTable.setAvatar(image_path);
                        userBeanHelp.save(userTable);
                        //加载头像
                        updateUserBean();
                    }

                    @Override
                    public void onError(String errStr) {
                        ToastUtils.showShort(errStr);
                    }
                });

    }


}
