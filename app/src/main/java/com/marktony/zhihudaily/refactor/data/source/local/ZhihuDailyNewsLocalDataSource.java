package com.marktony.zhihudaily.refactor.data.source.local;

import android.content.Context;
import android.os.AsyncTask;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import com.marktony.zhihudaily.refactor.data.ZhihuDailyNewsQuestion;
import com.marktony.zhihudaily.refactor.data.source.datasource.ZhihuDailyNewsDataSource;
import com.marktony.zhihudaily.refactor.database.AppDatabase;
import com.marktony.zhihudaily.refactor.database.DatabaseCreator;

import java.util.List;

/**
 * Created by lizhaotailang on 2017/5/21.
 */

public class ZhihuDailyNewsLocalDataSource implements ZhihuDailyNewsDataSource {

    @Nullable
    private static ZhihuDailyNewsLocalDataSource INSTANCE = null;

    @Nullable
    private AppDatabase mDb = null;

    private ZhihuDailyNewsLocalDataSource(@NonNull Context context) {
        DatabaseCreator creator = DatabaseCreator.getInstance();
        if (!creator.isDatabaseCreated()) {
            creator.createDb(context);
        }
        mDb = creator.getDatabase();
    }

    public static ZhihuDailyNewsLocalDataSource getInstance(@NonNull Context context) {
        if (INSTANCE == null) {
            INSTANCE = new ZhihuDailyNewsLocalDataSource(context);
        }
        return INSTANCE;
    }

    public static void destroyInstance() {
        INSTANCE = null;
    }

    @Override
    public void getZhihuDailyNews(boolean forceUpdate, boolean clearCache, long date, @NonNull LoadZhihuDailyNewsCallback callback) {
        if (mDb == null) {
            callback.onDataNotAvailable();
            return;
        }

        new AsyncTask<Void, Void, List<ZhihuDailyNewsQuestion>>() {

            @Override
            protected List<ZhihuDailyNewsQuestion> doInBackground(Void... voids) {
                return mDb.zhihuDailyNewsDao().loadAllZhihuDailyNews();
            }

            @Override
            protected void onPostExecute(List<ZhihuDailyNewsQuestion> list) {
                super.onPostExecute(list);
                if (list == null) {
                    callback.onDataNotAvailable();
                } else {
                    callback.onNewsLoaded(list);
                }
            }

        }.execute();

    }

    @Override
    public void getItem(int itemId, @NonNull GetNewsItemCallback callback) {
        if (mDb == null) {
            callback.onDataNotAvailable();
            return;
        }

        new AsyncTask<Void, Void, ZhihuDailyNewsQuestion>() {

            @Override
            protected ZhihuDailyNewsQuestion doInBackground(Void... voids) {
                return mDb.zhihuDailyNewsDao().loadZhihuDailyNewsItem(itemId);
            }

            @Override
            protected void onPostExecute(ZhihuDailyNewsQuestion item) {
                super.onPostExecute(item);
                if (item == null) {
                    callback.onDataNotAvailable();
                } else {
                    callback.onItemLoaded(item);
                }
            }
        }.execute();

    }

    @Override
    public void favoriteItem(int itemId, boolean favorited) {

    }

    @Override
    public void outdateItem(int itemId) {

    }

    @Override
    public void saveAll(@NonNull List<ZhihuDailyNewsQuestion> list) {
        if (mDb != null) {
            mDb.beginTransaction();
            try {
                mDb.zhihuDailyNewsDao().insertAll(list);
                mDb.setTransactionSuccessful();
            } finally {
                mDb.endTransaction();
            }
        }
    }

    @Override
    public void saveItem(@NonNull ZhihuDailyNewsQuestion item) {
        if (mDb != null) {
            mDb.beginTransaction();
            try {
                mDb.zhihuDailyNewsDao().insertItem(item);
                mDb.setTransactionSuccessful();
            } finally {
                mDb.endTransaction();
            }
        }
    }
}
