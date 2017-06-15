package com.marktony.zhihudaily.refactor.data.source.datasource;

import android.support.annotation.NonNull;

import com.marktony.zhihudaily.refactor.data.DoubanMomentNews;
import com.marktony.zhihudaily.refactor.data.GuokrHandpickNews;
import com.marktony.zhihudaily.refactor.data.ZhihuDailyNews;

import java.util.List;

/**
 * Created by lizhaotailang on 2017/6/15.
 */

public interface FavoritesDataSource {

    interface LoadFavoritesCallback {

        void onItemsLoaded(@NonNull List<ZhihuDailyNews.Question> zhihuList,
                           @NonNull List<DoubanMomentNews.Posts> doubanList,
                           @NonNull List<GuokrHandpickNews.Result> guokrList);

        void onDataNotAvailable();

    }

    interface LoadZhihuItemCallback {

        void onItemLoaded(@NonNull ZhihuDailyNews.Question item);

        void onDataNotAvailable();

    }

    interface LoadDoubanItemCallback {

        void onItemLoaded(@NonNull DoubanMomentNews.Posts item);

        void onDataNotAvailable();

    }

    interface LoadGuokrItemCallback {

        void onItemLoaded(@NonNull GuokrHandpickNews.Result item);

        void onDataNotAvailable();

    }

    void getFavoriteItems(@NonNull LoadFavoritesCallback callback);

}
