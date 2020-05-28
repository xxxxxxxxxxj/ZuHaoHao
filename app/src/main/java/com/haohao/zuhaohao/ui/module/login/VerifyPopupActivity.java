package com.haohao.zuhaohao.ui.module.login;

import android.app.Activity;
import android.content.Intent;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.webkit.JavascriptInterface;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ProgressBar;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.haohao.zuhaohao.AppConstants;
import com.haohao.zuhaohao.R;
import com.token.verifysdk.VerifyCoder;

/**
 * 腾讯弹窗验证码界面
 * date：2018/10/19 10:25
 * author：xiongj
 **/
@Route(path = AppConstants.PagePath.LOGIN_VERIFYPOPUP)
public class VerifyPopupActivity extends AppCompatActivity {

    private WebView webview;
    private ProgressBar mProgressBar;
    private float mDensity;
    private float mScale = 0.8f; //默认弹框验证码宽度是屏幕宽度*0.8
    private final float F_DEFAULT_POPUP_IFRAME_WIDTH = 19f * 16;
    private final int F_MAX_IFRAME_WIDTH_SCALE = 2;
    private final int F_CAP_TYPE_NEW_SLIDE_PUZZLE = 9;//滑动拼图新UI

    public Resources getResources() {
        Resources res = super.getResources();
        Configuration config = new Configuration();
        config.setToDefaults();
        res.updateConfiguration(config, res.getDisplayMetrics());
        return res;
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        super.onCreate(savedInstanceState);
        WindowManager manager = getWindowManager();
        DisplayMetrics metrics = new DisplayMetrics();
        manager.getDefaultDisplay().getMetrics(metrics);
        mDensity = metrics.density;
        int windowWidth = metrics.widthPixels;

        /*
         * 以滑动拼图弹框验证码为例，取弹框验证码宽度为屏幕宽度0.8
         * 滑动拼图标准宽18.2*16dp，标准高16.1*16dp,最大缩放比例2 ----capType=7
         * 图中点字标准宽18.2*16dp，标准高19.6*16dp,最大缩放比例2 ----capType=4,6
         * 滑动拼图新UI标准宽19*16dp，标准高19*16dp,最大缩放比例2 ----capType=9
         * */
        int iframeWidthPX = (int) (windowWidth * mScale);
        int iframeWidthDP = (int) (iframeWidthPX / mDensity);
        if (iframeWidthDP >= (int) (F_DEFAULT_POPUP_IFRAME_WIDTH * F_MAX_IFRAME_WIDTH_SCALE)) {
            iframeWidthDP = (int) (F_DEFAULT_POPUP_IFRAME_WIDTH * F_MAX_IFRAME_WIDTH_SCALE);
            iframeWidthPX = (int) (iframeWidthDP * mDensity);
        }
        //根据验证码类型和弹框宽度，获取验证码弹框高度
        int iframeHeightDP = VerifyCoder.getPopupIframeHeightByWidthAndCaptype(iframeWidthDP, F_CAP_TYPE_NEW_SLIDE_PUZZLE);
        int iframeHeightPX = (int) (iframeHeightDP * mDensity);


        //业务可根据自己需要实现不同的loading展现
        setContentView(R.layout.act_verify_popup);
        webview = findViewById(R.id.webview);
        mProgressBar = findViewById(R.id.progressBar);
        android.view.WindowManager.LayoutParams attributes = getWindow().getAttributes();
        attributes.width = iframeWidthPX;
        attributes.height = iframeHeightPX;
        getWindow().setAttributes(attributes);

        // 设置屏幕自适应。
        webview.getSettings().setUseWideViewPort(true);
        webview.getSettings().setLoadWithOverviewMode(true);
        // 建议禁止缓存加载，以确保在攻击发生时可快速获取最新的滑动验证组件进行对抗。
        webview.getSettings().setCacheMode(WebSettings.LOAD_NO_CACHE);
        // 设置不使用默认浏览器，而直接使用WebView组件加载页面。
        webview.setWebViewClient(new WebViewClient() {
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                view.loadUrl(url);
                return true;
            }

            @Override
            public void onPageFinished(WebView view, String url) {
                super.onPageFinished(view, url);
                //收到验证码页面(包括图片)加载完成回调时，把Loading隐藏，WebView显示
                mProgressBar.setVisibility(View.INVISIBLE);
            }
        });
        // 设置WebView组件支持加载JavaScript。
        webview.getSettings().setJavaScriptEnabled(true);
        // 建立JavaScript调用Java接口的桥梁。
        webview.addJavascriptInterface(new testJsInterface(), "testInterface");
        // 加载业务页面。
        webview.loadUrl("file:///android_asset/verify.html");
    }

    public class testJsInterface {
        @JavascriptInterface
        public void getSlideData(String nc_token, String csessionid, String sig, String ticket) {
            Log.e("TAG", "nc_token = " + nc_token);
            Log.e("TAG", "csessionid = " + csessionid);
            Log.e("TAG", "sig = " + sig);
            Log.e("TAG", "ticket = " + ticket);
            Intent it = new Intent();
            it.putExtra("nc_token", nc_token);
            it.putExtra("csessionid", csessionid);
            it.putExtra("sig", sig);
            it.putExtra("ticket", ticket);
            setResult(Activity.RESULT_OK, it);
            finish();
        }
    }
}
