package com.marktony.zhihudaily.refactor.data.source.repository;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import com.marktony.zhihudaily.refactor.data.DoubanMomentNews;
import com.marktony.zhihudaily.refactor.data.GuokrHandpickNews;
import com.marktony.zhihudaily.refactor.data.ZhihuDailyNews;
import com.marktony.zhihudaily.refactor.data.source.datasource.FavoritesDataSource;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by lizhaotailang on 2017/6/15.
 */

public class FavoritesRepository implements FavoritesDataSource {

    @Nullable
    private static FavoritesRepository INSTANCE = null;

    @NonNull
    private final FavoritesDataSource mLocalDataSource;

    private Map<Integer, ZhihuDailyNews.Question> mZhihuCachedItems;
    private Map<Integer, DoubanMomentNews.Posts> mDoubanCachedItems;
    private Map<Integer, GuokrHandpickNews.Result> mGuokrCachedItems;

    private FavoritesRepository(@NonNull FavoritesDataSource localDataSource) {
        this.mLocalDataSource = localDataSource;
    }

    public static FavoritesRepository newInstance(@NonNull FavoritesDataSource localDataSource) {
        if (INSTANCE == null) {
            INSTANCE = new FavoritesRepository(localDataSource);
        }
        return INSTANCE;
    }

    public static void destroyInstance() {
        INSTANCE = null;
    }

    @Override
    public void getFavoriteItems(@NonNull LoadFavoritesCallback callback) {
        if (mZhihuCachedItems != null && mDoubanCachedItems != null && mGuokrCachedItems != null) {
            callback.onItemsLoaded(
                    new ArrayList<>(mZhihuCachedItems.values()),
                    new ArrayList<>(mDoubanCachedItems.values()),
                    new ArrayList<>(mGuokrCachedItems.values()));
            return;
        }

        mLocalDataSource.getFavoriteItems(new LoadFavoritesCallback() {
            @Override
            public void onItemsLoaded(@NonNull List<ZhihuDailyNews.Question> zhihuList,
                                      @NonNull List<DoubanMomentNews.Posts> doubanList,
                                      @NonNull List<GuokrHandpickNews.Result> guokrList) {
                refreshCache(zhihuList, doubanList, guokrList);
                callback.onItemsLoaded(zhihuList, doubanList, guokrList);
            }

            @Override
            public void onDataNotAvailable() {
                callback.onDataNotAvailable();
            }
        });
    }

    private void refreshCache(List<ZhihuDailyNews.Question> zhihuList,
                              List<DoubanMomentNews.Posts> doubanList,
                              List<GuokrHandpickNews.Result> guokrList) {
        if (mZhihuCachedItems == null) {
            mZhihuCachedItems = new LinkedHashMap<>();
        }
        if (mDoubanCachedItems == null) {
            mDoubanCachedItems = new LinkedHashMap<>();
        }
        if (mGuokrCachedItems == null) {
            mGuokrCachedItems = new LinkedHashMap<>();
        }

        mZhihuCachedItems.clear();
        mDoubanCachedItems.clear();
        mGuokrCachedItems.clear();

        for (ZhihuDailyNews.Question item : zhihuList) {
            mZhihuCachedItems.put(item.getId(), item);
        }
        for (DoubanMomentNews.Posts item : doubanList) {
            mDoubanCachedItems.put(item.getId(), item);
        }
        for (GuokrHandpickNews.Result item : guokrList) {
            mGuokrCachedItems.put(item.getId(), item);
        }

    }
}
