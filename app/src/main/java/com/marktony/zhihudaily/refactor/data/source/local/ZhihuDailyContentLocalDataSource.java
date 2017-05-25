package com.marktony.zhihudaily.refactor.data.source.local;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import com.marktony.zhihudaily.refactor.data.ZhihuDailyContent;
import com.marktony.zhihudaily.refactor.data.source.datasource.ZhihuDailyContentDataSource;

/**
 * Created by lizhaotailang on 2017/5/26.
 */

public class ZhihuDailyContentLocalDataSource implements ZhihuDailyContentDataSource {

    @Nullable
    private static ZhihuDailyContentLocalDataSource INSTANCE = null;

    private ZhihuDailyContentLocalDataSource() {

    }

    public static ZhihuDailyContentLocalDataSource getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new ZhihuDailyContentLocalDataSource();
        }
        return INSTANCE;
    }

    @Override
    public void getZhihuDailyContent(int id, @NonNull LoadZhihuDailyContentCallback callback) {

    }

    @Override
    public void favorite(boolean favorite) {

    }

    @Override
    public void saveContent(@NonNull ZhihuDailyContent content) {

    }
}
