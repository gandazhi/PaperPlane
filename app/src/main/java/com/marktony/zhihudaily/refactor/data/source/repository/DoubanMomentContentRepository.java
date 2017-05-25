package com.marktony.zhihudaily.refactor.data.source.repository;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import com.marktony.zhihudaily.refactor.data.DoubanMomentContent;
import com.marktony.zhihudaily.refactor.data.source.datasource.DoubanMomentContentDataSource;

/**
 * Created by lizhaotailang on 2017/5/25.
 */

public class DoubanMomentContentRepository implements DoubanMomentContentDataSource {

    @Nullable
    private static DoubanMomentContentRepository INSTANCE = null;

    @NonNull
    private final DoubanMomentContentDataSource mLocalDataSource;

    @NonNull
    private final DoubanMomentContentDataSource mRemoteDataSource;

    @Nullable
    private DoubanMomentContent mContent;

    private DoubanMomentContentRepository(@NonNull DoubanMomentContentDataSource remoteDataSource,
                                          @NonNull DoubanMomentContentDataSource localDataSource) {
        mLocalDataSource = localDataSource;
        mRemoteDataSource = remoteDataSource;
    }

    public static DoubanMomentContentRepository getInstance(@NonNull DoubanMomentContentDataSource remoteDataSource,
                                                            @NonNull DoubanMomentContentDataSource localDataSource) {
        if (INSTANCE == null) {
            INSTANCE = new DoubanMomentContentRepository(remoteDataSource, localDataSource);
        }
        return INSTANCE;
    }

    @Override
    public void getDoubanMomentContent(int id, @NonNull LoadDoubanMomentContentCallback callback) {
        if (mContent != null) {
            callback.onContentLoaded(mContent);
            return;
        }

        // Get data from net first.
        mRemoteDataSource.getDoubanMomentContent(id, new LoadDoubanMomentContentCallback() {
            @Override
            public void onContentLoaded(@NonNull DoubanMomentContent content) {
                if (mContent == null) {
                    mContent = content;
                    saveContent(content);
                }
                callback.onContentLoaded(content);
            }

            @Override
            public void onDataNotAvailable() {
                mLocalDataSource.getDoubanMomentContent(id, new LoadDoubanMomentContentCallback() {
                    @Override
                    public void onContentLoaded(@NonNull DoubanMomentContent content) {
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
            mContent.setFavorite(favorite);
        }
    }

    @Override
    public void saveContent(@NonNull DoubanMomentContent content) {
        mLocalDataSource.saveContent(content);
        mRemoteDataSource.saveContent(content);
    }
}
