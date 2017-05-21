package com.marktony.zhihudaily.refactor.data.source.local;

import android.support.annotation.Nullable;

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

}
