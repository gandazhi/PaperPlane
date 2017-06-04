package com.marktony.zhihudaily.refactor.data.source.datasource;

import android.support.annotation.NonNull;

import com.marktony.zhihudaily.refactor.data.DoubanMomentNews;

import java.util.List;

/**
 * Created by lizhaotailang on 2017/5/21.
 */

public interface DoubanMomentNewsDataSource {

    interface LoadDoubanMomentDailyCallback {

        void onNewsLoaded(@NonNull List<DoubanMomentNews.Posts> list);

        void onDataNotAvailable();

    }

    interface GetNewsItemCallback {

        void onItemLoaded(@NonNull DoubanMomentNews.Posts item);

        void onDataNotAvailable();

    }

    void getDoubanMomentNews(boolean addToCache, long date, @NonNull LoadDoubanMomentDailyCallback callback);

    void getItem(int id, @NonNull GetNewsItemCallback callback);

    void favoriteItem(int itemId, boolean favorite);

    void outdateItem(int itemId);

    void saveItem(@NonNull DoubanMomentNews.Posts item);

}
