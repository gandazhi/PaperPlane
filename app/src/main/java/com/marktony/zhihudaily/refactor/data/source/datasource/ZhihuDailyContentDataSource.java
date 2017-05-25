package com.marktony.zhihudaily.refactor.data.source.datasource;

import android.support.annotation.NonNull;

import com.marktony.zhihudaily.refactor.data.ZhihuDailyContent;

/**
 * Created by lizhaotailang on 2017/5/25.
 */

public interface ZhihuDailyContentDataSource {

    interface LoadZhihuDailyContentCallback {

        void onContentLoaded(@NonNull ZhihuDailyContent content);

        void onDataNotAvailable();

    }

    void getZhihuDailyContent(int id, @NonNull LoadZhihuDailyContentCallback callback);

    void favorite(boolean favorite);

    void saveContent(@NonNull ZhihuDailyContent content);

}
