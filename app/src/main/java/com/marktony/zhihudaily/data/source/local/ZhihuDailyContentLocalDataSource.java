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
                return mDb.zhihuDailyContentDao().queryContentById(id);
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
    public void saveContent(@NonNull ZhihuDailyContent content) {
        if (mDb == null) {
            mDb = DatabaseCreator.getInstance().getDatabase();
        }

        if (mDb != null) {
            new Thread(() -> {
                // Query the timestamp from zhihu news database of corresponding id
                // and set the content's timestamp of query value.
                content.setTimestamp(mDb.zhihuDailyNewsDao().queryTimestampById(content.getId()));
                mDb.beginTransaction();
                try {
                    mDb.zhihuDailyContentDao().insert(content);
                    mDb.setTransactionSuccessful();
                } finally {
                    mDb.endTransaction();
                }
            }).start();
        }
    }

}
