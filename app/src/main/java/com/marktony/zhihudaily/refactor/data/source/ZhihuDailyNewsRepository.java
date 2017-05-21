package com.marktony.zhihudaily.refactor.data.source;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import com.marktony.zhihudaily.refactor.data.ZhihuDailyNews;

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

    private Map<String, ZhihuDailyNews.Question> mCachedItems;

    private boolean mCacheIsDirty = false;

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
    public void getZhihuDailyNews(long date, @NonNull LoadZhihuDailyNewsCallback callback) {

        if (mCachedItems != null && !mCacheIsDirty) {
            callback.onNewsLoaded(new ArrayList<>(mCachedItems.values()));
            return;
        }

        if (mCacheIsDirty) {
            getItemsFromRemoteDataSource(date, callback);
        } else {
            mLocalDataSource.getZhihuDailyNews(date, new LoadZhihuDailyNewsCallback() {
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
    public void getItem(@NonNull String itemId, @NonNull GetNewsItemCallback callback) {
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
                mCachedItems.put(String.valueOf(item.getId()), item);
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
                        mCachedItems.put(String.valueOf(item.getId()), item);
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
    public void favoriteItem(@NonNull String itemId, boolean favorited) {
        mRemoteDataSource.favoriteItem(itemId, favorited);
        mLocalDataSource.favoriteItem(itemId, favorited);

        ZhihuDailyNews.Question cachedItem = getItemWithId(itemId);
        if (cachedItem != null) {
            cachedItem.setFavorited(favorited);
        }
    }

    @Override
    public void outdateItem(@NonNull String itemId) {
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
    }

    @Override
    public void saveItem(@NonNull ZhihuDailyNews.Question item) {
        mLocalDataSource.saveItem(item);
        mRemoteDataSource.saveItem(item);

        mCachedItems.put(String.valueOf(item.getId()), item);
    }

    private void getItemsFromRemoteDataSource(long date, @NonNull LoadZhihuDailyNewsCallback callback) {
        mRemoteDataSource.getZhihuDailyNews(date, new LoadZhihuDailyNewsCallback() {
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
        mCachedItems.clear();
        for (ZhihuDailyNews.Question item : list) {
            mCachedItems.put(String.valueOf(item.getId()), item);
        }
        mCacheIsDirty = false;
    }

    private void refreshLocalDataSource(List<ZhihuDailyNews.Question> list) {
        for (ZhihuDailyNews.Question item : list) {
            mLocalDataSource.saveItem(item);
        }
    }

    @Nullable
    private ZhihuDailyNews.Question getItemWithId(@NonNull String id) {
        if (mCachedItems == null || mCachedItems.isEmpty()) {
            return null;
        }
        return mCachedItems.get(id);
    }

}
