package com.marktony.zhihudaily.refactor.data.source.repository;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import com.marktony.zhihudaily.refactor.data.GuokrHandpickNews;
import com.marktony.zhihudaily.refactor.data.source.datasource.GuokrHandpickDataSource;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by lizhaotailang on 2017/5/24.
 */

public class GuokrHandpickNewsRepository implements GuokrHandpickDataSource {

    @Nullable
    private static GuokrHandpickNewsRepository INSTANCE = null;

    @NonNull
    private final GuokrHandpickDataSource mLocalDataSource;

    @NonNull
    private final GuokrHandpickDataSource mRemoteDataSource;

    private Map<Integer, GuokrHandpickNews.Result> mCachedItems;

    private GuokrHandpickNewsRepository(@NonNull GuokrHandpickDataSource remoteDataSource,
                                        @NonNull GuokrHandpickDataSource localDataSource) {
        mLocalDataSource = localDataSource;
        mRemoteDataSource = remoteDataSource;
    }

    public static GuokrHandpickNewsRepository getInstance(@NonNull GuokrHandpickDataSource remoteDataSource,
                                                          @NonNull GuokrHandpickDataSource localDataSource) {
        if (INSTANCE == null) {
            INSTANCE = new GuokrHandpickNewsRepository(remoteDataSource, localDataSource);
        }
        return INSTANCE;
    }

    public static void destroyInstance() {
        INSTANCE = null;
    }

    @Override
    public void getGuokrHandpickNews(boolean addToCache, int offset, int limit, @NonNull LoadGuokrHandpickNewsCallback callback) {

        if (mCachedItems != null && !addToCache) {
            callback.onNewsLoad(new ArrayList<>(mCachedItems.values()));
            return;
        }

        mRemoteDataSource.getGuokrHandpickNews(addToCache, offset, limit, new LoadGuokrHandpickNewsCallback() {
            @Override
            public void onNewsLoad(@NonNull List<GuokrHandpickNews.Result> list) {
                refreshCache(addToCache, list);
                refreshLocalDataSource(list);
                callback.onNewsLoad(new ArrayList<>(mCachedItems.values()));
            }

            @Override
            public void onDataNotAvailable() {
                mLocalDataSource.getGuokrHandpickNews(addToCache, offset, limit, new LoadGuokrHandpickNewsCallback() {
                    @Override
                    public void onNewsLoad(@NonNull List<GuokrHandpickNews.Result> list) {
                        refreshCache(addToCache, list);
                        callback.onNewsLoad(new ArrayList<>(mCachedItems.values()));
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
    public void getItem(int itemId, @NonNull GetNewsItemCallback callback) {
        GuokrHandpickNews.Result item = getItemWithId(itemId);

        if (item != null) {
            callback.onItemLoaded(item);
            return;
        }

        mLocalDataSource.getItem(itemId, new GetNewsItemCallback() {
            @Override
            public void onItemLoaded(@NonNull GuokrHandpickNews.Result item) {
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
                    public void onItemLoaded(@NonNull GuokrHandpickNews.Result item) {
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

        GuokrHandpickNews.Result cachedItem = getItemWithId(itemId);
        if (cachedItem != null) {
            cachedItem.setFavorite(favorite);
        }
    }

    @Override
    public void outdateItem(int itemId) {
        mRemoteDataSource.outdateItem(itemId);
        mLocalDataSource.outdateItem(itemId);

        GuokrHandpickNews.Result cachedItem = getItemWithId(itemId);
        if (cachedItem != null) {
            cachedItem.setOutdated(true);
        }
    }

    @Override
    public void saveItem(@NonNull GuokrHandpickNews.Result item) {
        mRemoteDataSource.saveItem(item);
        mLocalDataSource.saveItem(item);

        mCachedItems.put(item.getId(), item);
    }

    private void refreshLocalDataSource(List<GuokrHandpickNews.Result> list) {
        for (GuokrHandpickNews.Result item : list) {
            mLocalDataSource.saveItem(item);
        }
    }

    private void refreshCache(boolean addToCache, List<GuokrHandpickNews.Result> list) {
        if (mCachedItems == null) {
            mCachedItems = new LinkedHashMap<>();
        }
        if (!addToCache) {
            mCachedItems.clear();
        }
        for (GuokrHandpickNews.Result item : list) {
            mCachedItems.put(item.getId(), item);
        }
    }

    @Nullable
    private GuokrHandpickNews.Result getItemWithId(int itemId) {
        return (mCachedItems == null || mCachedItems.isEmpty()) ? null : mCachedItems.get(itemId);
    }

}
