package com.marktony.zhihudaily.refactor.data.source.datasource;

import android.support.annotation.NonNull;

import com.marktony.zhihudaily.refactor.data.DoubanMomentPosts;

import java.util.List;

/**
 * Created by lizhaotailang on 2017/5/21.
 */

public interface DoubanMomentNewsDataSource {

    interface LoadDoubanMomentDailyCallback {

        void onNewsLoaded(@NonNull List<DoubanMomentPosts> list);

        void onDataNotAvailable();

    }

    interface GetNewsItemCallback {

        void onItemLoaded(@NonNull DoubanMomentPosts item);

        void onDataNotAvailable();

    }

    void getDoubanMomentNews(boolean forceUpdate, boolean clearCache, long date, @NonNull LoadDoubanMomentDailyCallback callback);

    void getItem(int id, @NonNull GetNewsItemCallback callback);

    void favoriteItem(int itemId, boolean favorite);

    void outdateItem(int itemId);

    void saveItem(@NonNull DoubanMomentPosts item);

}
