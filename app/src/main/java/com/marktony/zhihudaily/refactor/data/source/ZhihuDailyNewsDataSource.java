package com.marktony.zhihudaily.refactor.data.source;

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

    void getZhihuDailyNews(boolean loadMore, long date, @NonNull LoadZhihuDailyNewsCallback callback);

    void getItem(@NonNull String itemId, @NonNull GetNewsItemCallback callback);

    void favoriteItem(@NonNull String itemId, boolean favorited);

    void outdateItem(@NonNull String itemId);

    void refreshZhihuDailyNews();

    void saveItem(@NonNull ZhihuDailyNews.Question item);

}
