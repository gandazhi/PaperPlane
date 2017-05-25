package com.marktony.zhihudaily.refactor.data.source.repository;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import com.marktony.zhihudaily.refactor.data.ZhihuDailyNews;
import com.marktony.zhihudaily.refactor.data.source.datasource.ZhihuDailyNewsDataSource;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by lizhaotailang on 2017/5/21.
 */

public class ZhihuDailyNewsRepository implements ZhihuDailyNewsDataSource {

    @Nullable
    private static ZhihuDailyNewsRepository INSTANCE = null;

    @NonNull
    private final ZhihuDailyNewsDataSource mLocalDataSource;

    @NonNull
    private final ZhihuDailyNewsDataSource mRemoteDataSource;

    private Map<Integer, ZhihuDailyNews.Question> mCachedItems;

    private boolean mCacheIsDirty = false;
    private boolean mLoadMore = false;

    private ZhihuDailyNewsRepository(@NonNull ZhihuDailyNewsDataSource remoteDataSource,
                                     @NonNull ZhihuDailyNewsDataSource localDataSource) {
        this.mLocalDataSource = localDataSource;
        this.mRemoteDataSource = remoteDataSource;
    }

    public static ZhihuDailyNewsRepository getInstance(@NonNull ZhihuDailyNewsDataSource remoteDataSource,
                                                       @NonNull ZhihuDailyNewsDataSource localDataSource) {
        if (INSTANCE == null) {
            INSTANCE = new ZhihuDailyNewsRepository(remoteDataSource, localDataSource);
        }
        return INSTANCE;
    }

    public static void destroyInstance() {
        INSTANCE = null;
    }

    @Override
    public void getZhihuDailyNews(boolean loadMore, long date, @NonNull LoadZhihuDailyNewsCallback callback) {

        // Update the flag of loading more or not.
        mLoadMore = loadMore;

        if (mCachedItems != null && !mCacheIsDirty && !loadMore) {
            callback.onNewsLoaded(new ArrayList<>(mCachedItems.values()));
            return;
        }

        if (mCacheIsDirty || loadMore) {
            getItemsFromRemoteDataSource(date, callback);
        } else {
            // The value of parameter isLoadMore will be ignored
            // when it comes to the code of remote data source.
            mLocalDataSource.getZhihuDailyNews(false, date, new LoadZhihuDailyNewsCallback() {
                @Override
                public void onNewsLoaded(@NonNull List<ZhihuDailyNews.Question> list) {
                    refreshCache(list);
                    callback.onNewsLoaded(new ArrayList<>(mCachedItems.values()));
                }

                @Override
                public void onDataNotAvailable() {
                    getItemsFromRemoteDataSource(date, callback);
                }
            });
        }

    }

    @Override
    public void getItem(int itemId, @NonNull GetNewsItemCallback callback) {
        ZhihuDailyNews.Question cachedItem = getItemWithId(itemId);

        if (cachedItem != null) {
            callback.onItemLoaded(cachedItem);
            return;
        }

        mLocalDataSource.getItem(itemId, new GetNewsItemCallback() {
            @Override
            public void onItemLoaded(@NonNull ZhihuDailyNews.Question item) {
                if (mCachedItems == null) {
                    mCachedItems = new LinkedHashMap<>();
                }
                mCachedItems.put(item.getId(), item);
                callback.onItemLoaded(item);
            }

            @Override
            public void onDataNotAvailable() {
                mRemoteDataSource.getItem(itemId, new GetNewsItemCallback() {
                    @Override
                    public void onItemLoaded(@NonNull ZhihuDailyNews.Question item) {
                        if (mCachedItems == null) {
                            mCachedItems = new LinkedHashMap<>();
                        }
                        mCachedItems.put(item.getId(), item);
                        callback.onItemLoaded(item);
                    }

                    @Override
                    public void onDataNotAvailable() {
                        callback.onDataNotAvailable();
                    }
                });
            }
        });
    }

    @Override
    public void favoriteItem(int itemId, boolean favorited) {
        mRemoteDataSource.favoriteItem(itemId, favorited);
        mLocalDataSource.favoriteItem(itemId, favorited);

        ZhihuDailyNews.Question cachedItem = getItemWithId(itemId);
        if (cachedItem != null) {
            cachedItem.setFavorite(favorited);
        }
    }

    @Override
    public void outdateItem(int itemId) {
        mRemoteDataSource.outdateItem(itemId);
        mLocalDataSource.outdateItem(itemId);

        ZhihuDailyNews.Question cachedItem = getItemWithId(itemId);
        if (cachedItem != null) {
            cachedItem.setOutdated(true);
        }
    }

    @Override
    public void refreshZhihuDailyNews() {
        mCacheIsDirty = true;
        mLoadMore = false;
    }

    @Override
    public void saveItem(@NonNull ZhihuDailyNews.Question item) {
        mLocalDataSource.saveItem(item);
        mRemoteDataSource.saveItem(item);

        mCachedItems.put(item.getId(), item);
    }

    private void getItemsFromRemoteDataSource(long date, @NonNull LoadZhihuDailyNewsCallback callback) {

        // The parameter isLoadMore will be ignored
        // when it comes to the remote data source.
        mRemoteDataSource.getZhihuDailyNews(false, date, new LoadZhihuDailyNewsCallback() {
            @Override
            public void onNewsLoaded(@NonNull List<ZhihuDailyNews.Question> list) {
                refreshCache(list);
                refreshLocalDataSource(list);
                callback.onNewsLoaded(new ArrayList<>(mCachedItems.values()));
            }

            @Override
            public void onDataNotAvailable() {
                callback.onDataNotAvailable();
            }
        });
    }

    private void refreshCache(List<ZhihuDailyNews.Question> list) {

        if (mCachedItems == null) {
            mCachedItems = new LinkedHashMap<>();
        }
        if (!mLoadMore) {
            mCachedItems.clear();
            mCacheIsDirty = false;
        }
        for (ZhihuDailyNews.Question item : list) {
            mCachedItems.put(item.getId(), item);
        }
    }

    private void refreshLocalDataSource(List<ZhihuDailyNews.Question> list) {
        for (ZhihuDailyNews.Question item : list) {
            mLocalDataSource.saveItem(item);
        }
    }

    @Nullable
    private ZhihuDailyNews.Question getItemWithId(int id) {
        if (mCachedItems == null || mCachedItems.isEmpty()) {
            return null;
        }
        return (mCachedItems == null || mCachedItems.isEmpty()) ? null : mCachedItems.get(id);
    }

}
