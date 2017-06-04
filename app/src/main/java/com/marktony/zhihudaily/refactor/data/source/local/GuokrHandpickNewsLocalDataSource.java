package com.marktony.zhihudaily.refactor.data.source.local;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import com.marktony.zhihudaily.refactor.data.GuokrHandpickNews;
import com.marktony.zhihudaily.refactor.data.source.datasource.GuokrHandpickDataSource;

/**
 * Created by lizhaotailang on 2017/5/24.
 */

public class GuokrHandpickNewsLocalDataSource implements GuokrHandpickDataSource {

    @Nullable
    private static GuokrHandpickNewsLocalDataSource INSTANCE = null;

    private GuokrHandpickNewsLocalDataSource() {

    }

    public static GuokrHandpickNewsLocalDataSource getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new GuokrHandpickNewsLocalDataSource();
        }
        return INSTANCE;
    }

    @Override
    public void getGuokrHandpickNews(boolean addToCache, int offset, int limit, @NonNull LoadGuokrHandpickNewsCallback callback) {

    }

    @Override
    public void getItem(int itemId, @NonNull GetNewsItemCallback callback) {

    }

    @Override
    public void favoriteItem(int itemId, boolean favorite) {

    }

    @Override
    public void outdateItem(int itemId) {

    }

    @Override
    public void saveItem(@NonNull GuokrHandpickNews.Result item) {

    }
}
