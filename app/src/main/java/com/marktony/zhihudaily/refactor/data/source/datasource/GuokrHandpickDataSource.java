package com.marktony.zhihudaily.refactor.data.source.datasource;

import android.support.annotation.NonNull;

import com.marktony.zhihudaily.refactor.data.GuokrHandpickNews;

import java.util.List;

/**
 * Created by lizhaotailang on 2017/5/24.
 */

public interface GuokrHandpickDataSource {

    interface LoadGuokrHandpickNewsCallback {

        void onNewsLoad(@NonNull List<GuokrHandpickNews.Result> list);

        void onDataNotAvailable();

    }

    interface GetNewsItemCallback {

        void onItemLoaded(@NonNull GuokrHandpickNews.Result item);

        void onDataNotAvailable();

    }

    void getGuokrHandpickNews(boolean addToCache, int offset, int limit, @NonNull LoadGuokrHandpickNewsCallback callback);

    void getItem(int itemId, @NonNull GetNewsItemCallback callback);

    void favoriteItem(int itemId, boolean favorite);

    void outdateItem(int itemId);

    void saveItem(@NonNull GuokrHandpickNews.Result item);

}
