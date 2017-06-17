package com.marktony.zhihudaily.refactor.data.source.datasource;

import android.support.annotation.NonNull;

import com.marktony.zhihudaily.refactor.data.DoubanMomentPosts;
import com.marktony.zhihudaily.refactor.data.GuokrHandpickNewsResult;
import com.marktony.zhihudaily.refactor.data.ZhihuDailyNewsQuestion;

import java.util.List;

/**
 * Created by lizhaotailang on 2017/6/15.
 */

public interface FavoritesDataSource {

    interface LoadFavoritesCallback {

        void onItemsLoaded(@NonNull List<ZhihuDailyNewsQuestion> zhihuList,
                           @NonNull List<DoubanMomentPosts> doubanList,
                           @NonNull List<GuokrHandpickNewsResult> guokrList);

        void onDataNotAvailable();

    }

    interface LoadZhihuItemCallback {

        void onItemLoaded(@NonNull ZhihuDailyNewsQuestion item);

        void onDataNotAvailable();

    }

    interface LoadDoubanItemCallback {

        void onItemLoaded(@NonNull DoubanMomentPosts item);

        void onDataNotAvailable();

    }

    interface LoadGuokrItemCallback {

        void onItemLoaded(@NonNull GuokrHandpickNewsResult item);

        void onDataNotAvailable();

    }

    void getFavoriteItems(@NonNull LoadFavoritesCallback callback);

}
