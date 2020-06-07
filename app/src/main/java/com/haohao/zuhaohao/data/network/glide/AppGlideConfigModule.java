package com.haohao.zuhaohao.data.network.glide;

import android.content.Context;
import android.util.Log;

import androidx.annotation.NonNull;

import com.bumptech.glide.Glide;
import com.bumptech.glide.GlideBuilder;
import com.bumptech.glide.Registry;
import com.bumptech.glide.annotation.GlideModule;
import com.bumptech.glide.load.DecodeFormat;
import com.bumptech.glide.load.engine.cache.InternalCacheDiskCacheFactory;
import com.bumptech.glide.module.AppGlideModule;
import com.bumptech.glide.request.RequestOptions;
import com.haohao.zuhaohao.AppConfig;
import com.haohao.zuhaohao.R;

import java.security.cert.CertificateException;
import java.util.concurrent.TimeUnit;

import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSession;
import javax.net.ssl.SSLSocketFactory;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;

import okhttp3.OkHttpClient;


/**
 * Glide加载配置
 * date：2017/12/26 15:11
 * author：Seraph
 **/
@GlideModule
public class AppGlideConfigModule extends AppGlideModule {

    //配置
    @Override
    public void applyOptions(@NonNull Context context, @NonNull GlideBuilder builder) {
        super.applyOptions(context, builder);
        //磁盘缓存
        builder.setDiskCache(new InternalCacheDiskCacheFactory(context, AppConfig.CACHE_MAX_SIZE));
        //默认请求
        builder.setDefaultRequestOptions(
                new RequestOptions()
                        .format(DecodeFormat.PREFER_RGB_565)
                        .disallowHardwareConfig()
                        .placeholder(R.mipmap.icon_placeholder)
                        .error(R.mipmap.icon_error)
        );
        //日志级别
        //builder.setLogLevel(Log.DEBUG);
    }

    @Override
    public void registerComponents(@NonNull Context context, @NonNull Glide glide, @NonNull Registry registry) {
        Log.e("TAG", "registerComponents = ");
        //设置请求方式为okhttp 并设置okhttpClient的证书及超时时间
        OkHttpUrlLoader.Factory factory = new OkHttpUrlLoader.Factory(UnsafeOkHttpClient.getUnsafeOkHttpClient());
        //registry.replace(GlideUrl.class, InputStream.class, factory);
    }

    //自定义工具类修改OkHttpClient证书和超时时间
    static class UnsafeOkHttpClient {
        public static OkHttpClient getUnsafeOkHttpClient() {
            try {
                // Create a trust manager that does not validate certificate chains
                final TrustManager[] trustAllCerts = new TrustManager[]{
                        new X509TrustManager() {
                            @Override
                            public void checkClientTrusted(java.security.cert.X509Certificate[] chain, String authType) throws CertificateException {
                            }

                            @Override
                            public void checkServerTrusted(java.security.cert.X509Certificate[] chain, String authType) throws CertificateException {
                            }

                            @Override
                            public java.security.cert.X509Certificate[] getAcceptedIssuers() {
                                return new java.security.cert.X509Certificate[]{};
                            }
                        }
                };

                // Install the all-trusting trust manager
                final SSLContext sslContext = SSLContext.getInstance("SSL");
                sslContext.init(null, trustAllCerts, new java.security.SecureRandom());

                // Create an ssl socket factory with our all-trusting manager
                final SSLSocketFactory sslSocketFactory = sslContext.getSocketFactory();

                OkHttpClient.Builder builder = new OkHttpClient.Builder();
                builder.sslSocketFactory(sslSocketFactory, (X509TrustManager) trustAllCerts[0]);
                builder.hostnameVerifier(new HostnameVerifier() {
                    @Override
                    public boolean verify(String hostname, SSLSession session) {
                        Log.e("TAG", "hostname = " + hostname);
                        return true;
                    }
                });

                builder.connectTimeout(20, TimeUnit.SECONDS);
                builder.readTimeout(20, TimeUnit.SECONDS);
                builder.writeTimeout(20, TimeUnit.SECONDS);

                OkHttpClient okHttpClient = builder.build();
                return okHttpClient;
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }
    }

    /**
     * 禁止解析Manifest文件
     * 主要针对V3升级到v4的用户，可以提升初始化速度，避免一些潜在错误
     * @return
     */
    @Override
    public boolean isManifestParsingEnabled() {
        return false;
    }
}
