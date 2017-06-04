package com.marktony.zhihudaily.refactor.data.source.datasource;

import android.support.annotation.NonNull;

import com.marktony.zhihudaily.refactor.data.ZhihuDailyNews;

import java.util.List;

/**
 * Created by lizhaotailang on 2017/5/21.
 */

public interface ZhihuDailyNewsDataSource {

    interface LoadZhihuDailyNewsCallback {

        void onNewsLoaded(@NonNull List<ZhihuDailyNews.Question> list);

        void onDataNotAvailable();

    }

    interface GetNewsItemCallback {

        void onItemLoaded(@NonNull ZhihuDailyNews.Question item);

        void onDataNotAvailable();

    }

    void getZhihuDailyNews(boolean forceUpdate, boolean clearCache, long date, @NonNull LoadZhihuDailyNewsCallback callback);

    void getItem(int itemId, @NonNull GetNewsItemCallback callback);

    void favoriteItem(int itemId, boolean favorite);

    void outdateItem(int itemId);

    void saveItem(@NonNull ZhihuDailyNews.Question item);

}
