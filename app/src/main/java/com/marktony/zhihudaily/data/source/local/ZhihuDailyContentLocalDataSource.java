package com.marktony.zhihudaily.data.source.local;

import android.content.Context;
import android.os.AsyncTask;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import com.marktony.zhihudaily.data.ZhihuDailyContent;
import com.marktony.zhihudaily.data.source.datasource.ZhihuDailyContentDataSource;
import com.marktony.zhihudaily.database.AppDatabase;
import com.marktony.zhihudaily.database.DatabaseCreator;

/**
 * Created by lizhaotailang on 2017/5/26.
 */

public class ZhihuDailyContentLocalDataSource implements ZhihuDailyContentDataSource {

    @Nullable
    private static ZhihuDailyContentLocalDataSource INSTANCE = null;

    @Nullable
    private AppDatabase mDb = null;

    private ZhihuDailyContentLocalDataSource(@NonNull Context context) {
        DatabaseCreator creator = DatabaseCreator.getInstance();
        if (!creator.isDatabaseCreated()) {
            creator.createDb(context);
        }
        mDb = creator.getDatabase();
    }

    public static ZhihuDailyContentLocalDataSource getInstance(@NonNull Context context) {
        if (INSTANCE == null) {
            INSTANCE = new ZhihuDailyContentLocalDataSource(context);
        }
        return INSTANCE;
    }

    @Override
    public void getZhihuDailyContent(int id, @NonNull LoadZhihuDailyContentCallback callback) {
        if (mDb == null) {
            callback.onDataNotAvailable();
            return;
        }

        new AsyncTask<Void, Void, ZhihuDailyContent>() {

            @Override
            protected ZhihuDailyContent doInBackground(Void... voids) {
                return mDb.zhihuDailyContentDao().loadZhihuDailyContent(id);
            }

            @Override
            protected void onPostExecute(ZhihuDailyContent content) {
                super.onPostExecute(content);
                if (content == null) {
                    callback.onDataNotAvailable();
                } else {
                    callback.onContentLoaded(content);
                }
            }

        }.execute();
    }

    @Override
    public void favorite(boolean favorite) {

    }

    @Override
    public void saveContent(@NonNull ZhihuDailyContent content) {
        if (mDb != null) {
            mDb.beginTransaction();
            try {
                mDb.zhihuDailyContentDao().saveContent(content);
                mDb.setTransactionSuccessful();
            } finally {
                mDb.endTransaction();
            }
        }
    }

}
