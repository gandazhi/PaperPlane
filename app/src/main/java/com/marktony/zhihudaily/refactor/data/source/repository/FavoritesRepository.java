package com.marktony.zhihudaily.refactor.data.source.repository;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import com.marktony.zhihudaily.refactor.data.DoubanMomentPosts;
import com.marktony.zhihudaily.refactor.data.GuokrHandpickNewsResult;
import com.marktony.zhihudaily.refactor.data.ZhihuDailyNewsQuestion;
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

    private Map<Integer, ZhihuDailyNewsQuestion> mZhihuCachedItems;
    private Map<Integer, DoubanMomentPosts> mDoubanCachedItems;
    private Map<Integer, GuokrHandpickNewsResult> mGuokrCachedItems;

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
            public void onItemsLoaded(@NonNull List<ZhihuDailyNewsQuestion> zhihuList,
                                      @NonNull List<DoubanMomentPosts> doubanList,
                                      @NonNull List<GuokrHandpickNewsResult> guokrList) {
                refreshCache(zhihuList, doubanList, guokrList);
                callback.onItemsLoaded(zhihuList, doubanList, guokrList);
            }

            @Override
            public void onDataNotAvailable() {
                callback.onDataNotAvailable();
            }
        });
    }

    private void refreshCache(List<ZhihuDailyNewsQuestion> zhihuList,
                              List<DoubanMomentPosts> doubanList,
                              List<GuokrHandpickNewsResult> guokrList) {
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

        for (ZhihuDailyNewsQuestion item : zhihuList) {
            mZhihuCachedItems.put(item.getId(), item);
        }
        for (DoubanMomentPosts item : doubanList) {
            mDoubanCachedItems.put(item.getId(), item);
        }
        for (GuokrHandpickNewsResult item : guokrList) {
            mGuokrCachedItems.put(item.getId(), item);
        }

    }
}
