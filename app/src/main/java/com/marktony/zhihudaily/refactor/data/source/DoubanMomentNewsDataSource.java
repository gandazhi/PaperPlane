package com.marktony.zhihudaily.refactor.data.source;

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

    void getDoubanMomentNews(boolean loadMore, long date, @NonNull LoadDoubanMomentDailyCallback callback);

    void getItem(@NonNull String id, @NonNull GetNewsItemCallback callback);

    void favoriteItem(@NonNull String itemId, boolean favorited);

    void outdateItem(@NonNull String itemId);

    void refreshDoubanMomentNews();

    void saveItem(@NonNull DoubanMomentNews.Posts item);

}
