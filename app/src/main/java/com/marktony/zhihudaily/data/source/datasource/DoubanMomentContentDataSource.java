package com.marktony.zhihudaily.data.source.datasource;

import android.support.annotation.NonNull;

import com.marktony.zhihudaily.data.DoubanMomentContent;

/**
 * Created by lizhaotailang on 2017/5/25.
 */

public interface DoubanMomentContentDataSource {

    interface LoadDoubanMomentContentCallback {

        void onContentLoaded(@NonNull DoubanMomentContent content);

        void onDataNotAvailable();

    }

    void getDoubanMomentContent(int id, @NonNull LoadDoubanMomentContentCallback callback);

    void favorite(boolean favorite);

    void saveContent(@NonNull DoubanMomentContent content);

}
