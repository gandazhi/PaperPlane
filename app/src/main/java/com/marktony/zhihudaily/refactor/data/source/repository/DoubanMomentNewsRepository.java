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
    public void getDoubanMomentNews(boolean addToCache, long date, @NonNull LoadDoubanMomentDailyCallback callback) {

        if (mCachedItems != null && !addToCache) {
            callback.onNewsLoaded(new ArrayList<>(mCachedItems.values()));
            return;
        }

        mRemoteDataSource.getDoubanMomentNews(addToCache, date, new LoadDoubanMomentDailyCallback() {
            @Override
            public void onNewsLoaded(@NonNull List<DoubanMomentNews.Posts> list) {
                refreshCache(addToCache, list);
                refreshLocalDataSource(list);
                callback.onNewsLoaded(new ArrayList<>(mCachedItems.values()));
            }

            @Override
            public void onDataNotAvailable() {
                mLocalDataSource.getDoubanMomentNews(addToCache, date, new LoadDoubanMomentDailyCallback() {
                    @Override
                    public void onNewsLoaded(@NonNull List<DoubanMomentNews.Posts> list) {
                        refreshCache(addToCache, list);
                        callback.onNewsLoaded(new ArrayList<>(mCachedItems.values()));
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
    public void saveItem(@NonNull DoubanMomentNews.Posts item) {

        mLocalDataSource.saveItem(item);
        mRemoteDataSource.saveItem(item);

        // Do in memory cache update to keep the app UI up to date
        if (mCachedItems == null) {
            mCachedItems = new LinkedHashMap<>();
        }
        mCachedItems.put(item.getId(), item);
    }

    private void refreshCache(boolean addToCache, List<DoubanMomentNews.Posts> list) {

        if (mCachedItems == null) {
            mCachedItems = new LinkedHashMap<>();
        }
        if (!addToCache) {
            mCachedItems.clear();
        }
        for (DoubanMomentNews.Posts item : list) {
            mCachedItems.put(item.getId(), item);
        }
    }

    @Nullable
    private DoubanMomentNews.Posts getItemWithId(int id) {
        return (mCachedItems == null || mCachedItems.isEmpty()) ? null : mCachedItems.get(id);
    }

    private void refreshLocalDataSource(List<DoubanMomentNews.Posts> list) {
        for (DoubanMomentNews.Posts item : list) {
            mLocalDataSource.saveItem(item);
        }
    }

}
