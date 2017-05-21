package com.marktony.zhihudaily.refactor.data.source;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

/**
 * Created by lizhaotailang on 2017/5/21.
 */

public class DoubanMomentNewsRepository implements DoubanMomentNewsDataSource {

    @Nullable
    private static DoubanMomentNewsRepository INSTANCE = null;

    @NonNull
    private final DoubanMomentNewsDataSource mLocalDataSource;

    @NonNull
    private final DoubanMomentNewsDataSource mRemoteDataSource;

    private DoubanMomentNewsRepository(@NonNull DoubanMomentNewsDataSource remoteDataSource,
                                       @NonNull DoubanMomentNewsDataSource localDataSource) {
        this.mLocalDataSource = localDataSource;
        this.mRemoteDataSource = remoteDataSource;
    }

    public static DoubanMomentNewsRepository getInstance(@NonNull DoubanMomentNewsDataSource remoteDataSource,
                                                         @NonNull DoubanMomentNewsDataSource localDataSource ) {
        if (INSTANCE == null) {
            INSTANCE = new DoubanMomentNewsRepository(remoteDataSource, localDataSource);
        }
        return INSTANCE;
    }

}
