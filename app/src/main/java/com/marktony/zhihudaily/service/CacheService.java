package com.marktony.zhihudaily.service;

import android.app.Service;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.support.v4.content.LocalBroadcastManager;

import com.marktony.zhihudaily.data.DoubanMomentContent;
import com.marktony.zhihudaily.data.GuokrHandpickContentResult;
import com.marktony.zhihudaily.data.PostType;
import com.marktony.zhihudaily.data.ZhihuDailyContent;
import com.marktony.zhihudaily.database.AppDatabase;
import com.marktony.zhihudaily.database.DatabaseCreator;
import com.marktony.zhihudaily.retrofit.RetrofitService;

import java.io.IOException;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by lizhaotailang on 2017/6/19.
 */

public class CacheService extends Service {

    public static final String FLAG_ID = "flag_id";
    public static final String FLAG_TYPE = "flag_type";

    public static final String BROADCAST_FILTER_ACTION = "com.marktony.zhihudaily.LOCAL_BROADCAST";

    @Nullable
    private AppDatabase mDb = null;

    private LocalReceiver mReceiver;

    private RetrofitService.ZhihuDailyService mZhihuService;
    private RetrofitService.DoubanMomentService mDoubanService;
    private RetrofitService.GuokrHandpickService mGuokrService;

    @Override
    public void onCreate() {
        super.onCreate();

        mReceiver = new LocalReceiver();

        IntentFilter filter = new IntentFilter();
        filter.addAction(BROADCAST_FILTER_ACTION);
        LocalBroadcastManager.getInstance(this).registerReceiver(mReceiver, filter);

        mZhihuService = new Retrofit.Builder()
                .baseUrl(RetrofitService.ZHIHU_DAILY_BASE)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
                .create(RetrofitService.ZhihuDailyService.class);

        mDoubanService = new Retrofit.Builder()
                .baseUrl(RetrofitService.DOUBAN_MOMENT_BASE)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
                .create(RetrofitService.DoubanMomentService.class);

        mGuokrService = new Retrofit.Builder()
                .baseUrl(RetrofitService.GUOKR_HANDPICK_BASE)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
                .create(RetrofitService.GuokrHandpickService.class);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        // DO NOT forget to unregister the receiver.
        if (mReceiver != null) {
            LocalBroadcastManager.getInstance(this).unregisterReceiver(mReceiver);
        }
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    // A local broadcast receiver that receives broadcast from the corresponding fragment.
    class LocalReceiver extends BroadcastReceiver {

        @Override
        public void onReceive(Context context, Intent intent) {
            int id = intent.getIntExtra(FLAG_ID, 0);
            @PostType
            int type = intent.getIntExtra(FLAG_TYPE, 0);
            switch (type) {
                case PostType.TYPE_ZHIHU:
                    cacheZhihuDailyContent(id);
                    break;
                case PostType.TYPE_DOUBAN:
                    cacheDoubanContent(id);
                    break;
                case PostType.TYPE_GUOKR:
                    cacheGuokrContent(id);
                    break;
                default:
                    break;
            }
        }

    }

    // Get zhihu content data by accessing network.
    private void cacheZhihuDailyContent(int id) {

        if (mDb == null) {
            DatabaseCreator creator = DatabaseCreator.getInstance();
            if (!creator.isDatabaseCreated()) {
                creator.createDb(this);
            }
            mDb = creator.getDatabase();
        }

        new Thread(() -> {

            if (mDb != null && mDb.zhihuDailyContentDao().queryContentById(id) == null) {
                mDb.beginTransaction();
                try {
                    // Call execute() rather than enqueue()
                    // or you will go back to main thread in onResponse() function.
                    ZhihuDailyContent tmp = mZhihuService.getZhihuContent(id).execute().body();
                    if (tmp != null) {
                        mDb.zhihuDailyContentDao().insert(tmp);
                        mDb.setTransactionSuccessful();
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                } finally {
                    mDb.endTransaction();
                }
            }
        }).start();
    }

    // Get douban content data by accessing network.
    private void cacheDoubanContent(int id) {

        if (mDb == null) {
            DatabaseCreator creator = DatabaseCreator.getInstance();
            if (!creator.isDatabaseCreated()) {
                creator.createDb(this);
            }
            mDb = creator.getDatabase();
        }

        new Thread(() -> {
            if (mDb != null && mDb.doubanMomentContentDao().queryContentById(id) == null) {
                mDb.beginTransaction();
                try {
                    // Call execute() rather than enqueue()
                    // or you will go back to main thread in onResponse() function.
                    DoubanMomentContent tmp = mDoubanService.getDoubanContent(id).execute().body();
                    if (tmp != null) {
                        mDb.doubanMomentContentDao().insert(tmp);
                        mDb.setTransactionSuccessful();
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                } finally {
                    mDb.endTransaction();
                }
            }
        }).start();
    }

    // Get guokr content data by accessing network.
    private void cacheGuokrContent(int id) {

        if (mDb == null) {
            DatabaseCreator creator = DatabaseCreator.getInstance();
            if (!creator.isDatabaseCreated()) {
                creator.createDb(this);
            }
            mDb = creator.getDatabase();
        }

        new Thread(() -> {
            if (mDb != null && mDb.guokrHandpickContentDao().queryContentById(id) == null) {
                mDb.beginTransaction();
                try {
                    // Call execute() rather than enqueue()
                    // or you will go back to main thread in onResponse() function.
                    GuokrHandpickContentResult tmp = mGuokrService.getGuokrContent(id).execute().body().getResult();
                    if (tmp != null) {
                        mDb.guokrHandpickContentDao().insert(tmp);
                        mDb.setTransactionSuccessful();
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                } finally {
                    mDb.endTransaction();
                }
            }
        }).start();
    }

    private void clearTimeoutContent() {
        if (mDb == null) {
            DatabaseCreator creator = DatabaseCreator.getInstance();
            if (!creator.isDatabaseCreated()) {
                creator.createDb(this);
            }
            mDb = creator.getDatabase();
        }

        new Thread(() -> {
            if (mDb != null) {
                mDb.beginTransaction();
                try {

                } finally {
                    mDb.endTransaction();
                }
            }
        }).start();
    }

}
