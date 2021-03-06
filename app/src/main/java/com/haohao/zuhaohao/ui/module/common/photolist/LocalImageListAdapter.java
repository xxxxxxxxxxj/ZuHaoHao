package com.haohao.zuhaohao.ui.module.common.photolist;

import android.app.Activity;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.haohao.zuhaohao.R;
import com.haohao.zuhaohao.data.network.glide.GlideApp;
import com.haohao.zuhaohao.ui.views.CustomSquareImageView;

import java.io.File;
import java.util.ArrayList;

import javax.inject.Inject;


/**
 * 显示并选择本地图片的适配器
 */
class LocalImageListAdapter extends BaseQuickAdapter<LocalImageBean, BaseViewHolder> {

    private ArrayList<String> mSelectedPathList = new ArrayList<>();

    private Activity activity;

    @Inject
    LocalImageListAdapter(Activity activity) {
        super(R.layout.common_activity_loacl_image_item);
        this.activity = activity;
    }


    @Override
    protected void convert(final BaseViewHolder holder, final LocalImageBean localImageBean) {
        CustomSquareImageView imageView = holder.getView(R.id.iv_image_item);
        final ImageView tagView = holder.getView(R.id.iv_image_item_tag);
        GlideApp.with(activity).load(new File(localImageBean.path)).into(imageView);
        if (mSelectedPathList.contains(localImageBean.path)) {
            tagView.setVisibility(View.VISIBLE);
        } else {
            tagView.setVisibility(View.GONE);
        }
        imageView.setOnClickListener(v -> {
            if (localImageBean.isSelected) { //取消选中
                mData.get(holder.getAdapterPosition()).isSelected = false;
                mSelectedPathList.remove(localImageBean.path);
                tagView.setVisibility(View.GONE);
            } else {
                if (mSelectedPathList.size() >= 5) {
                    Toast.makeText(mContext, "最多选择5张图片！", Toast.LENGTH_SHORT).show();
                    return;
                }
                if (!mSelectedPathList.contains(localImageBean.path)) {
                    mSelectedPathList.add(localImageBean.path);
                }
                mData.get(holder.getAdapterPosition()).isSelected = true;
                tagView.setVisibility(View.VISIBLE);
            }
        });
    }

    public ArrayList<String> getSelectedPathList() {
        return mSelectedPathList;
    }


    public void setSelectedPathList(ArrayList<String> selectedPathList) {
        if (selectedPathList != null && selectedPathList.size() > 0) {
            this.mSelectedPathList = selectedPathList;
            notifyDataSetChanged();
        }
    }


}
