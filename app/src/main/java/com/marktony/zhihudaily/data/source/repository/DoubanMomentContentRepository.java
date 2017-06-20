package com.marktony.zhihudaily.data.source.repository;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import com.marktony.zhihudaily.data.DoubanMomentContent;
import com.marktony.zhihudaily.data.source.datasource.DoubanMomentContentDataSource;
import com.marktony.zhihudaily.util.DateFormatUtil;

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

    public static void destroyInstance() {
        INSTANCE = null;
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
    public void favorite(int id, boolean favorite) {
        mLocalDataSource.favorite(id, favorite);
        mRemoteDataSource.favorite(id, favorite);
        if (mContent != null) {
            mContent.setFavorite(favorite);
        }
    }

    @Override
    public void saveContent(@NonNull DoubanMomentContent content) {
        content.setTimestamp(DateFormatUtil.formatDoubanMomentDateStringToLong(content.getPublishedTime()));
        mLocalDataSource.saveContent(content);
        mRemoteDataSource.saveContent(content);
    }
}
