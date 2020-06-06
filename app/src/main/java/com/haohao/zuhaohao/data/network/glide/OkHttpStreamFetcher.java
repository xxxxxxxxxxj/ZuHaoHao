package com.haohao.zuhaohao.data.network.glide;

import androidx.annotation.NonNull;

import com.bumptech.glide.load.Key;
import com.bumptech.glide.load.data.DataFetcher;
import com.bumptech.glide.load.model.ModelLoader;

import java.io.InputStream;
import java.util.List;

/**
 * <p>Title:${type_name}</p>
 * <p>Description:</p>
 * <p>Company:北京昊唐科技有限公司</p>
 *
 * @author 徐俊
 * @date zhoujunxia on 2020-06-06 12:29
 */
public class OkHttpStreamFetcher extends ModelLoader.LoadData<InputStream> {
    public OkHttpStreamFetcher(@NonNull Key sourceKey, @NonNull List<Key> alternateKeys, @NonNull DataFetcher<InputStream> fetcher) {
        super(sourceKey, alternateKeys, fetcher);
    }
}