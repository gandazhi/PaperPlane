package com.marktony.zhihudaily.data.source.datasource;

import android.support.annotation.NonNull;

import com.marktony.zhihudaily.data.DoubanMomentNewsPosts;
import com.marktony.zhihudaily.data.GuokrHandpickNewsResult;
import com.marktony.zhihudaily.data.ZhihuDailyNewsQuestion;

import java.util.List;

/**
 * Created by lizhaotailang on 2017/6/15.
 */

public interface FavoritesDataSource {

    interface LoadFavoritesCallback {

        void onItemsLoaded(@NonNull List<ZhihuDailyNewsQuestion> zhihuList,
                           @NonNull List<DoubanMomentNewsPosts> doubanList,
                           @NonNull List<GuokrHandpickNewsResult> guokrList);

        void onDataNotAvailable();

    }

    interface LoadZhihuItemCallback {

        void onItemLoaded(@NonNull ZhihuDailyNewsQuestion item);

        void onDataNotAvailable();

    }

    interface LoadDoubanItemCallback {

        void onItemLoaded(@NonNull DoubanMomentNewsPosts item);

        void onDataNotAvailable();

    }

    interface LoadGuokrItemCallback {

        void onItemLoaded(@NonNull GuokrHandpickNewsResult item);

        void onDataNotAvailable();

    }

    void getFavoriteItems(@NonNull LoadFavoritesCallback callback);

}
