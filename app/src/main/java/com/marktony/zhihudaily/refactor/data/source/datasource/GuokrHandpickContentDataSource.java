package com.marktony.zhihudaily.refactor.data.source.datasource;

import android.support.annotation.NonNull;

import com.marktony.zhihudaily.refactor.data.GuokrHandpickContentResult;

/**
 * Created by lizhaotailang on 2017/5/26.
 */

public interface GuokrHandpickContentDataSource {

    interface LoadGuokrHandpickContentCallback {

        void onContentLoaded(@NonNull GuokrHandpickContentResult content);

        void onDataNotAvailable();

    }

    void getGuokrHandpickContent(int id, @NonNull LoadGuokrHandpickContentCallback callback);

    void favorite(boolean favorite);

    void saveContent(@NonNull GuokrHandpickContentResult content);

}
