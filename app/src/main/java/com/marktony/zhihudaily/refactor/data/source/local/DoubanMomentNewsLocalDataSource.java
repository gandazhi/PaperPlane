package com.marktony.zhihudaily.refactor.data.source.local;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import com.marktony.zhihudaily.refactor.data.DoubanMomentNews;
import com.marktony.zhihudaily.refactor.data.source.DoubanMomentNewsDataSource;

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
    public void getDoubanMomentNews(boolean loadMore, long date, @NonNull LoadDoubanMomentDailyCallback callback) {

    }

    @Override
    public void getItem(@NonNull String id, @NonNull GetNewsItemCallback callback) {

    }

    @Override
    public void favoriteItem(@NonNull String itemId, boolean favorited) {

    }

    @Override
    public void outdateItem(@NonNull String itemId) {

    }

    @Override
    public void refreshDoubanMomentNews() {

    }

    @Override
    public void saveItem(@NonNull DoubanMomentNews.Posts item) {

    }
}
