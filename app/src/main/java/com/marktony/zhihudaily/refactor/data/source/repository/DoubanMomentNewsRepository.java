package com.marktony.zhihudaily.refactor.data.source.repository;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.Log;

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

    public static String TAG = "douban";
    
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

        Log.d(TAG, "DoubanMomentNewsRepository: ");
        
    }

    public static DoubanMomentNewsRepository getInstance(@NonNull DoubanMomentNewsDataSource remoteDataSource,
                                                         @NonNull DoubanMomentNewsDataSource localDataSource) {
        Log.d(TAG, "getInstance: ");
        if (INSTANCE == null) {
            INSTANCE = new DoubanMomentNewsRepository(remoteDataSource, localDataSource);
        }
        return INSTANCE;
    }

    public static void destroyInstance() {
        INSTANCE = null;
        Log.d(TAG, "destroyInstance: ");
    }

    @Override
    public void getDoubanMomentNews(boolean loadMore, long date, @NonNull LoadDoubanMomentDailyCallback callback) {

        Log.d(TAG, "getDoubanMomentNews: ");
        
        mLoadMore = loadMore;

        if (mCachedItems != null && !mCacheIsDirty && !loadMore) {
            callback.onNewsLoaded(new ArrayList<>(mCachedItems.values()));
            return;
        }

        if (mCacheIsDirty || loadMore) {
            Log.d(TAG, "getDoubanMomentNews: getItemsFromRemoteDataSource");
            getItemsFromRemoteDataSource(date, callback);
        } else {
            Log.d(TAG, "getDoubanMomentNews: mLocalDataSource get");
            mLocalDataSource.getDoubanMomentNews(false, date, new LoadDoubanMomentDailyCallback() {
                @Override
                public void onNewsLoaded(@NonNull List<DoubanMomentNews.Posts> list) {
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
    public void getItem(int id, @NonNull GetNewsItemCallback callback) {
        DoubanMomentNews.Posts cachedItem = getItemWithId(id);

        Log.d(TAG, "getItem: ");
        
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

        Log.d(TAG, "favoriteItem: ");
        
        mRemoteDataSource.favoriteItem(itemId, favorite);
        mLocalDataSource.favoriteItem(itemId, favorite);

        DoubanMomentNews.Posts cachedItem = getItemWithId(itemId);
        if (cachedItem != null) {
            cachedItem.setFavorite(favorite);
        }
    }

    @Override
    public void outdateItem(int itemId) {

        Log.d(TAG, "outdateItem: ");
        
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

        Log.d(TAG, "refreshDoubanMomentNews: ");
    }

    @Override
    public void saveItem(@NonNull DoubanMomentNews.Posts item) {

        Log.d(TAG, "saveItem: ");
        
        mLocalDataSource.saveItem(item);
        mRemoteDataSource.saveItem(item);

        // Do in memory cache update to keep the app UI up to date
        if (mCachedItems == null) {
            mCachedItems = new LinkedHashMap<>();
        }
        mCachedItems.put(item.getId(), item);
    }

    private void getItemsFromRemoteDataSource(long date, LoadDoubanMomentDailyCallback callback) {

        Log.d(TAG, "getItemsFromRemoteDataSource: ");
        
        mRemoteDataSource.getDoubanMomentNews(false, date, new LoadDoubanMomentDailyCallback() {
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


        Log.d(TAG, "refreshCache: ");
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
        Log.d(TAG, "getItemWithId: ");
        return (mCachedItems == null || mCachedItems.isEmpty()) ? null : mCachedItems.get(id);
    }

    private void refreshLocalDataSource(List<DoubanMomentNews.Posts> list) {
        Log.d(TAG, "refreshLocalDataSource: ");
        for (DoubanMomentNews.Posts item : list) {
            mLocalDataSource.saveItem(item);
        }
    }

}
