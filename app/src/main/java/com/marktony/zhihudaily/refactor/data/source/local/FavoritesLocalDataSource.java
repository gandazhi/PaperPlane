package com.marktony.zhihudaily.refactor.data.source.local;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import com.marktony.zhihudaily.refactor.data.source.datasource.FavoritesDataSource;

/**
 * Created by lizhaotailang on 2017/6/15.
 */

public class FavoritesLocalDataSource implements FavoritesDataSource {

    @Nullable
    private static FavoritesLocalDataSource INSTANCE = null;

    private FavoritesLocalDataSource() {

    }

    public static FavoritesLocalDataSource getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new FavoritesLocalDataSource();
        }
        return INSTANCE;
    }

    public static void destroyInstance() {
        INSTANCE = null;
    }

    @Override
    public void getFavoriteItems(@NonNull LoadFavoritesCallback callback) {

    }
}
