package com.marktony.zhihudaily.refactor.data.source.local;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import com.marktony.zhihudaily.refactor.data.DoubanMomentContent;
import com.marktony.zhihudaily.refactor.data.source.datasource.DoubanMomentContentDataSource;

/**
 * Created by lizhaotailang on 2017/5/25.
 */

public class DoubanMomentContentLocalDataSource implements DoubanMomentContentDataSource {

    @Nullable
    private static DoubanMomentContentLocalDataSource INSTANCE = null;

    private DoubanMomentContentLocalDataSource() {

    }

    public static DoubanMomentContentLocalDataSource getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new DoubanMomentContentLocalDataSource();
        }
        return INSTANCE;
    }

    @Override
    public void getDoubanMomentContent(int id, @NonNull LoadDoubanMomentContentCallback callback) {

    }

    @Override
    public void favorite(boolean favorite) {

    }

    @Override
    public void saveContent(@NonNull DoubanMomentContent content) {

    }
}
