package com.marktony.zhihudaily.refactor.data.source.remote;

import android.support.annotation.Nullable;

import com.marktony.zhihudaily.refactor.data.source.DoubanMomentNewsDataSource;

/**
 * Created by lizhaotailang on 2017/5/21.
 */

public class DoubanMomentNewsRemoteDataSource implements DoubanMomentNewsDataSource {

    @Nullable
    private static DoubanMomentNewsRemoteDataSource INSTANCE = null;

    private DoubanMomentNewsRemoteDataSource() {

    }

    public static DoubanMomentNewsRemoteDataSource getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new DoubanMomentNewsRemoteDataSource();
        }
        return INSTANCE;
    }

}
