package com.marktony.zhihudaily.refactor.data.source.local;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import com.marktony.zhihudaily.refactor.data.DoubanMomentNews;
import com.marktony.zhihudaily.refactor.data.source.datasource.DoubanMomentNewsDataSource;

/**
 * Created by lizhaotailang on 2017/5/21.
 */

public class DoubanMomentNewsLocalDataSource implements DoubanMomentNewsDataSource {

    @Nullable
    private static DoubanMomentNewsLocalDataSource INSTANCE = null;

    private DoubanMomentNewsLocalDataSource() {

    }

    public static DoubanMomentNewsLocalDataSource getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new DoubanMomentNewsLocalDataSource();
        }
        return INSTANCE;
    }

    @Override
    public void getDoubanMomentNews(boolean forceUpdate, boolean clearCache, long date, @NonNull LoadDoubanMomentDailyCallback callback) {

    }

    @Override
    public void getItem(int id, @NonNull GetNewsItemCallback callback) {

    }

    @Override
    public void favoriteItem(int itemId, boolean favorited) {

    }

    @Override
    public void outdateItem(int itemId) {

    }

    @Override
    public void saveItem(@NonNull DoubanMomentNews.Posts item) {

    }
}
