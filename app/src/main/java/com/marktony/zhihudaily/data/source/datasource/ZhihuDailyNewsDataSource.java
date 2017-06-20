package com.marktony.zhihudaily.data.source.datasource;

import android.support.annotation.NonNull;

import com.marktony.zhihudaily.data.ZhihuDailyNewsQuestion;

import java.util.List;

/**
 * Created by lizhaotailang on 2017/5/21.
 */

public interface ZhihuDailyNewsDataSource {

    interface LoadZhihuDailyNewsCallback {

        void onNewsLoaded(@NonNull List<ZhihuDailyNewsQuestion> list);

        void onDataNotAvailable();

    }

    interface GetNewsItemCallback {

        void onItemLoaded(@NonNull ZhihuDailyNewsQuestion item);

        void onDataNotAvailable();

    }

    void getZhihuDailyNews(boolean forceUpdate, boolean clearCache, long date, @NonNull LoadZhihuDailyNewsCallback callback);

    void getFavorites(@NonNull LoadZhihuDailyNewsCallback callback);

    void getItem(int itemId, @NonNull GetNewsItemCallback callback);

    void favoriteItem(int itemId, boolean favorite);

    void outdateItem(int itemId);

    void saveAll(@NonNull List<ZhihuDailyNewsQuestion> list);

}
