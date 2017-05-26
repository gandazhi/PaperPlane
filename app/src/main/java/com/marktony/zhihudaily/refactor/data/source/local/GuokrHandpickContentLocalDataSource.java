package com.marktony.zhihudaily.refactor.data.source.local;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import com.marktony.zhihudaily.refactor.data.GuokrHandpickContent;
import com.marktony.zhihudaily.refactor.data.source.datasource.GuokrHandpickContentDataSource;

/**
 * Created by lizhaotailang on 2017/5/26.
 */

public class GuokrHandpickContentLocalDataSource implements GuokrHandpickContentDataSource {

    @Nullable
    private static GuokrHandpickContentLocalDataSource INSTANCE = null;

    private GuokrHandpickContentLocalDataSource() {

    }

    public static GuokrHandpickContentLocalDataSource getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new GuokrHandpickContentLocalDataSource();
        }
        return INSTANCE;
    }

    public static void destroyInstance() {
        INSTANCE = null;
    }

    @Override
    public void getGuokrHandpickContent(int id, @NonNull LoadGuokrHandpickContentCallback callback) {

    }

    @Override
    public void favorite(boolean favorite) {

    }

    @Override
    public void saveContent(@NonNull GuokrHandpickContent content) {

    }
}
