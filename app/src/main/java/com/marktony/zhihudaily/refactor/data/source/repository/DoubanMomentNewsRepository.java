package com.marktony.zhihudaily.refactor.data.source.repository;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import com.marktony.zhihudaily.refactor.data.DoubanMomentNews;
import com.marktony.zhihudaily.refactor.data.source.datasource.DoubanMomentNewsDataSource;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by lizhaotailang on 2017/5/21.
 */

public class DoubanMomentNewsRepository implements DoubanMomentNewsDataSource {

    @Nullable
    private static DoubanMomentNewsRepository INSTANCE = null;

    @NonNull
    private final DoubanMomentNewsDataSource mLocalDataSource;

    @NonNull
    private final DoubanMomentNewsDataSource mRemoteDataSource;

    private Map<Integer, DoubanMomentNews.Posts> mCachedItems;

    private boolean mCacheIsDirty = false;
    private boolean mLoadMore = false;

    private DoubanMomentNewsRepository(@NonNull DoubanMomentNewsDataSource remoteDataSource,
                                       @NonNull DoubanMomentNewsDataSource localDataSource) {
        this.mLocalDataSource = localDataSource;
        this.mRemoteDataSource = remoteDataSource;
    }

    public static DoubanMomentNewsRepository getInstance(@NonNull DoubanMomentNewsDataSource remoteDataSource,
                                                         @NonNull DoubanMomentNewsDataSource localDataSource) {
        if (INSTANCE == null) {
            INSTANCE = new DoubanMomentNewsRepository(remoteDataSource, localDataSource);
        }
        return INSTANCE;
    }

    public static void destroyInstance() {
        INSTANCE = null;
    }

    @Override
    public void getDoubanMomentNews(boolean loadMore, long date, @NonNull LoadDoubanMomentDailyCallback callback) {

        mLoadMore = loadMore;

        if (mCachedItems != null && !mCacheIsDirty && !loadMore) {
            callback.onNewsLoaded(new ArrayList<>(mCachedItems.values()));
            return;
        }

        if (mCacheIsDirty || loadMore) {
            getItemsFromRemoteDataSource(loadMore, date, callback);
        } else {
            mLocalDataSource.getDoubanMomentNews(false, date, new LoadDoubanMomentDailyCallback() {
                @Override
                public void onNewsLoaded(@NonNull List<DoubanMomentNews.Posts> list) {
                    refreshCache(list);
                    callback.onNewsLoaded(new ArrayList<>(mCachedItems.values()));
                }

                @Override
                public void onDataNotAvailable() {
                    getItemsFromRemoteDataSource(loadMore, date, callback);
                }
            });
        }
    }

    @Override
    public void getItem(int id, @NonNull GetNewsItemCallback callback) {
        DoubanMomentNews.Posts cachedItem = getItemWithId(id);

        if (cachedItem != null) {
            callback.onItemLoaded(cachedItem);
            return;
        }

        mLocalDataSource.getItem(id, new GetNewsItemCallback() {
            @Override
            public void onItemLoaded(@NonNull DoubanMomentNews.Posts item) {
                if (mCachedItems == null) {
                    mCachedItems = new LinkedHashMap<>();
                }
                mCachedItems.put(item.getId(), item);
                callback.onItemLoaded(item);
            }

            @Override
            public void onDataNotAvailable() {
                mRemoteDataSource.getItem(id, new GetNewsItemCallback() {
                    @Override
                    public void onItemLoaded(@NonNull DoubanMomentNews.Posts item) {
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
    public void favoriteItem(int itemId, boolean favorite) {
        mRemoteDataSource.favoriteItem(itemId, favorite);
        mLocalDataSource.favoriteItem(itemId, favorite);

        DoubanMomentNews.Posts cachedItem = getItemWithId(itemId);
        if (cachedItem != null) {
            cachedItem.setFavorite(favorite);
        }
    }

    @Override
    public void outdateItem(int itemId) {
        mRemoteDataSource.outdateItem(itemId);
        mLocalDataSource.outdateItem(itemId);

        DoubanMomentNews.Posts cachedItem = getItemWithId(itemId);
        if (cachedItem != null) {
            cachedItem.setOutdated(true);
        }
    }

    @Override
    public void refreshDoubanMomentNews() {
        mCacheIsDirty = true;
        mLoadMore = false;
    }

    @Override
    public void saveItem(@NonNull DoubanMomentNews.Posts item) {
        mLocalDataSource.saveItem(item);
        mRemoteDataSource.saveItem(item);

        // Do in memory cache update to keep the app UI up to date
        if (mCachedItems == null) {
            mCachedItems = new LinkedHashMap<>();
        }
        mCachedItems.put(item.getId(), item);
    }

    private void getItemsFromRemoteDataSource(boolean isLoadMore, long date, LoadDoubanMomentDailyCallback callback) {
        mRemoteDataSource.getDoubanMomentNews(isLoadMore, date, new LoadDoubanMomentDailyCallback() {
            @Override
            public void onNewsLoaded(@NonNull List<DoubanMomentNews.Posts> list) {
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

    private void refreshCache(List<DoubanMomentNews.Posts> list) {

        if (mCachedItems == null) {
            mCachedItems = new LinkedHashMap<>();
        }
        if (!mLoadMore) {
            mCachedItems.clear();
            mCacheIsDirty = false;
        }
        for (DoubanMomentNews.Posts item : list) {
            mCachedItems.put(item.getId(), item);
        }
    }

    @Nullable
    private DoubanMomentNews.Posts getItemWithId(int id) {
        if (mCachedItems == null || mCachedItems.isEmpty()) {
            return null;
        }
        return mCachedItems.get(id);
    }

    private void refreshLocalDataSource(List<DoubanMomentNews.Posts> list) {
        for (DoubanMomentNews.Posts item : list) {
            mLocalDataSource.saveItem(item);
        }
    }

}
