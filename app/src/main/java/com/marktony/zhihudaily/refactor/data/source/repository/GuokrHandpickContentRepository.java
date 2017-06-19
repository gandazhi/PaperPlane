package com.marktony.zhihudaily.refactor.data.source.repository;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import com.marktony.zhihudaily.refactor.data.GuokrHandpickContent;
import com.marktony.zhihudaily.refactor.data.GuokrHandpickContentResult;
import com.marktony.zhihudaily.refactor.data.source.datasource.GuokrHandpickContentDataSource;

/**
 * Created by lizhaotailang on 2017/5/26.
 */

public class GuokrHandpickContentRepository implements GuokrHandpickContentDataSource {

    @Nullable
    private static GuokrHandpickContentRepository INSTANCE = null;

    @NonNull
    private final GuokrHandpickContentDataSource mLocalDataSource;

    @NonNull
    private final GuokrHandpickContentDataSource mRemoteDataSource;

    @Nullable
    private GuokrHandpickContentResult mContent;

    private GuokrHandpickContentRepository(@NonNull GuokrHandpickContentDataSource remoteDataSource,
                                           @NonNull GuokrHandpickContentDataSource localDataSource) {
        this.mRemoteDataSource = remoteDataSource;
        this.mLocalDataSource = localDataSource;
    }

    public static GuokrHandpickContentRepository getInstance(@NonNull GuokrHandpickContentDataSource remoteDataSource,
                                                             @NonNull GuokrHandpickContentDataSource localDataSource) {
        if (INSTANCE == null) {
            INSTANCE = new GuokrHandpickContentRepository(remoteDataSource, localDataSource);
        }
        return INSTANCE;
    }

    public static void destroyInstance() {
        INSTANCE = null;
    }

    @Override
    public void getGuokrHandpickContent(int id, @NonNull LoadGuokrHandpickContentCallback callback) {
        if (mContent != null) {
            callback.onContentLoaded(mContent);
            return;
        }

        mRemoteDataSource.getGuokrHandpickContent(id, new LoadGuokrHandpickContentCallback() {
            @Override
            public void onContentLoaded(@NonNull GuokrHandpickContentResult content) {
                if (mContent == null) {
                    mContent = content;
                    saveContent(content);
                }
                callback.onContentLoaded(content);
            }

            @Override
            public void onDataNotAvailable() {
                mLocalDataSource.getGuokrHandpickContent(id, new LoadGuokrHandpickContentCallback() {
                    @Override
                    public void onContentLoaded(@NonNull GuokrHandpickContentResult content) {
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
        mRemoteDataSource.favorite(favorite);
        mLocalDataSource.favorite(favorite);
        if (mContent != null) {
            mContent.setFavorite(favorite);
        }
    }

    @Override
    public void saveContent(@NonNull GuokrHandpickContentResult content) {
        mLocalDataSource.saveContent(content);
        mRemoteDataSource.saveContent(content);
    }
}
