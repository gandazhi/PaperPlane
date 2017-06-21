package com.marktony.zhihudaily.data.source.datasource;

import android.support.annotation.NonNull;

import com.marktony.zhihudaily.data.GuokrHandpickContentResult;

/**
 * Created by lizhaotailang on 2017/5/26.
 */

public interface GuokrHandpickContentDataSource {

    interface LoadGuokrHandpickContentCallback {

        void onContentLoaded(@NonNull GuokrHandpickContentResult content);

        void onDataNotAvailable();

    }

    void getGuokrHandpickContent(int id, @NonNull LoadGuokrHandpickContentCallback callback);

    void saveContent(@NonNull GuokrHandpickContentResult content);

}
