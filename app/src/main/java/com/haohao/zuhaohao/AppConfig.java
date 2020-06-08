package com.haohao.zuhaohao;

import com.blankj.utilcode.util.MetaDataUtils;

/**
 * app的一些常量设置
 * date：2017/2/22 09:47
 * author：Seraph
 **/
public class AppConfig {
    /**
     * 阿里云oss对象存储服务配置相关参数
     */
    // 访问的endpoint地址
    public static final String OSS_ENDPOINT = "http://oss-cn-shanghai.aliyuncs.com";
    //callback 测试地址
    public static final String OSS_CALLBACK_URL = "http://oss-demo.aliyuncs.com:23450";
    // STS 鉴权服务器地址。
    // 或者根据工程sts_local_server目录中本地鉴权服务脚本代码启动本地STS鉴权服务器。
    public static final String STS_SERVER_URL = "http://****/sts/getsts";//STS 地址

    public static final String BUCKET_NAME = "test-haohao-20200603";
    public static final String OSS_ACCESS_KEY_ID = "<yourAccessKeyId>";
    public static final String OSS_ACCESS_KEY_SECRET = "<yourAccessKeySecret>";
    /**
     * 王者荣耀游戏ID
     */
    public static final String GAME_ID = "592";
    /**
     * 王者荣耀游戏名字
     */
    public static final String GAME_NAME = "QQ飞车";
    /**
     * //1 端游  2手游
     */
    public static final String GAME_TYPE = "1";

    /**
     * 本地数据库名称
     */
    public static final String DB_NAME = "zuhaohao-db";

    /**
     * 网络超时
     */
    public static final int DEFAULT_TIMEOUT = 30;
    /**
     * 最大缓存 1G
     */
    public static final long CACHE_MAX_SIZE = 1024 * 1024 * 1024;
    /**
     * 图片保存文件夹名称
     * 为相册文件夹{@link android.os.Environment#DIRECTORY_DCIM}中的子文件夹名
     */
    public static final String SAVE_IMAGE_FOLDERS_NAME = "zuhaohao";
    /**
     * 是否在debug模式
     */
    public static final boolean DEBUG = true;

    /**
     * 使用的服务器地址1正式，2准生产，3测试
     */
    public static final int BASE_HTTP_IP = 1;

    /**
     * buglyid
     */
    public static final String BUGLY_ID = "b40759d484";

    /**
     * 友盟appkey
     */
    public static final String UM_APP_KEY = "5ecf39e1895ccab59300016e";

    /**
     * 推送的标签
     */
    static final String JPUSH_TAG_LOGIN = "zuhaohao_user";
    /**
     * 别名前缀
     */
    static final String JPUSH_ALIAS = "zuhaohao_user_";


    //根据值活动对应的编号
    public static String getBusinessNo() {
        return AppConstants.ChannelAction.getChannelNo(getChannelValue());
    }

    //获取渠道号
    public static String getChannelValue() {
        return MetaDataUtils.getMetaDataInApp("CHANNEL");
    }

    //免费玩活动号
    public static final String MIAN_FEI_ACTIV_NO = "HD201811299812";

    //渠道(php使用的appkey)
    public static final String APP_KEY_PHP = "app_fulu";

    //阿里云dns
    public static final String ACCOUNT_ID = "139243";

    //pc上号器下载地址
    public static final String PC_DOWNLOAD = "http://www.xubei.com/download.html";

    //验证是否上号器的标识符
    public static final String SCAN_QC_PC_STR = "&from=pcQrLogin";

    //分页，每一页加载的数量
    public static final int PAGE_SIZE = 32;

    //照片列表请求码
    public static final int CODE_IMAGE_LIST_REQUEST = 1000;

    //第三方调用验证码的客户端类型  1：手机Web页面2：PCWeb页面 4：APP
    public static final String CLIENT_TYPE = "4";

    //临时存储
    private static String APP_ID = "";

    //阿里云客服链接
    public static String CSCHAT_URL = "https://cschat-ccs.aliyun.com/index.htm?tntInstId=_1KlnA7c&scene=SCE00004697#/";

    public static String getAppId() {
        return APP_ID;
    }

    public static void setAppId(String appId) {
        APP_ID = appId;
    }

}
