package com.haohao.zuhaohao.ui.module.main.adapter;

import android.widget.ImageView;

import com.blankj.utilcode.util.ObjectUtils;
import com.bumptech.glide.load.resource.bitmap.RoundedCorners;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.haohao.zuhaohao.R;
import com.haohao.zuhaohao.data.network.glide.GlideApp;
import com.haohao.zuhaohao.ui.module.account.model.GameBean;
import com.haohao.zuhaohao.ui.module.base.BaseDataCms;

import javax.inject.Inject;

/**
 * <p>Title:${type_name}</p>
 * <p>Description:</p>
 * <p>Company:北京昊唐科技有限公司</p>
 *
 * @author 徐俊
 * @date zhoujunxia on 2020-05-29 16:07
 */
public class HomeHotRentAdapter extends BaseQuickAdapter<BaseDataCms<GameBean>, BaseViewHolder> {

    @Inject
    public HomeHotRentAdapter() {
        super(R.layout.item_main_home_rent);
    }

    @Override
    protected void convert(BaseViewHolder helper, BaseDataCms<GameBean> item) {
        helper.setText(R.id.tv_itemmainhomerent, item.properties.getGameName());
        ImageView iv_itemmainhomerent = helper.getView(R.id.iv_itemmainhomerent);
        if (ObjectUtils.isEmpty(item.properties.img_url) || !item.properties.img_url.contains("http")) {
            item.properties.img_url = "http:" + item.properties.img_url;
        }
        GlideApp.with(mContext).load(item.properties.img_url).transform(new RoundedCorners(25)).into(iv_itemmainhomerent);
    }
}
