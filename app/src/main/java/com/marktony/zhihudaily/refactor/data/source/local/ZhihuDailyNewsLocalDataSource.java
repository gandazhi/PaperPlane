package com.marktony.zhihudaily.refactor.data.source.local;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import com.marktony.zhihudaily.refactor.data.ZhihuDailyNews;
import com.marktony.zhihudaily.refactor.data.source.ZhihuDailyNewsDataSource;

/**
 * Created by lizhaotailang on 2017/5/21.
 */

public class ZhihuDailyNewsLocalDataSource implements ZhihuDailyNewsDataSource {

    @Nullable
    private static ZhihuDailyNewsLocalDataSource INSTANCE = null;

    private ZhihuDailyNewsLocalDataSource() {

    }

    public static ZhihuDailyNewsLocalDataSource getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new ZhihuDailyNewsLocalDataSource();
        }
        return INSTANCE;
    }

    public static void destroyInstance() {
        INSTANCE = null;
    }

    @Override
    public void getZhihuDailyNews(boolean loadMore, long date, @NonNull LoadZhihuDailyNewsCallback callback) {

    }

    @Override
    public void getItem(@NonNull String itemId, @NonNull GetNewsItemCallback callback) {

    }

    @Override
    public void favoriteItem(@NonNull String itemId, boolean favorited) {

    }

    @Override
    public void outdateItem(@NonNull String itemId) {

    }

    @Override
    public void refreshZhihuDailyNews() {

    }

    @Override
    public void saveItem(@NonNull ZhihuDailyNews.Question item) {

    }
}
