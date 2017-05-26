package com.marktony.zhihudaily.refactor.data.source.repository;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import com.marktony.zhihudaily.refactor.data.ZhihuDailyContent;
import com.marktony.zhihudaily.refactor.data.source.datasource.ZhihuDailyContentDataSource;

/**
 * Created by lizhaotailang on 2017/5/26.
 */

public class ZhihuDailyContentRepository implements ZhihuDailyContentDataSource {

    @Nullable
    public static ZhihuDailyContentRepository INSTANCE = null;

    @NonNull
    private final ZhihuDailyContentDataSource mLocalDataSource;

    @NonNull
    private final ZhihuDailyContentDataSource mRemoteDataSource;

    @Nullable
    private ZhihuDailyContent mContent;

    private ZhihuDailyContentRepository(@NonNull ZhihuDailyContentDataSource remoteDataSource,
                                        @NonNull ZhihuDailyContentDataSource localDataSource) {
        mLocalDataSource = localDataSource;
        mRemoteDataSource = remoteDataSource;
    }

    public static ZhihuDailyContentRepository getInstance(@NonNull ZhihuDailyContentDataSource remoteDataSource,
                                                          @NonNull ZhihuDailyContentDataSource localDataSource) {
        if (INSTANCE == null) {
            INSTANCE = new ZhihuDailyContentRepository(remoteDataSource, localDataSource);
        }
        return INSTANCE;
    }

    public static void destroyInstance() {
        INSTANCE = null;
    }

    @Override
    public void getZhihuDailyContent(int id, @NonNull LoadZhihuDailyContentCallback callback) {
        if (mContent != null) {
            callback.onContentLoaded(mContent);
            return;
        }

        mRemoteDataSource.getZhihuDailyContent(id, new LoadZhihuDailyContentCallback() {
            @Override
            public void onContentLoaded(@NonNull ZhihuDailyContent content) {
                if (mContent == null) {
                    mContent = content;
                    saveContent(content);
                }
                callback.onContentLoaded(content);
            }

            @Override
            public void onDataNotAvailable() {
                mLocalDataSource.getZhihuDailyContent(id, new LoadZhihuDailyContentCallback() {
                    @Override
                    public void onContentLoaded(@NonNull ZhihuDailyContent content) {
                        if (mContent == null) {
                            mContent = content;
                        }
                        callback.onContentLoaded(content);
                    }

                    @Override
                    public void onDataNotAvailable() {
                        callback.onDataNotAvailable();
                    }
                });
            }
        });
    }

    @Override
    public void favorite(boolean favorite) {
        mLocalDataSource.favorite(favorite);
        mRemoteDataSource.favorite(favorite);

        if (mContent != null) {
            mContent.setFavorited(favorite);
        }
    }

    @Override
    public void saveContent(@NonNull ZhihuDailyContent content) {

    }
}
