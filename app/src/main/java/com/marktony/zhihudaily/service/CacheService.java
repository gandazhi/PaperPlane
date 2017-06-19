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
import com.marktony.zhihudaily.data.GuokrHandpickContent;
import com.marktony.zhihudaily.data.PostType;
import com.marktony.zhihudaily.data.ZhihuDailyContent;
import com.marktony.zhihudaily.database.AppDatabase;
import com.marktony.zhihudaily.database.DatabaseCreator;
import com.marktony.zhihudaily.retrofit.RetrofitService;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
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

        DatabaseCreator creator = DatabaseCreator.getInstance();
        if (!creator.isDatabaseCreated()) {
            creator.createDb(this);
        }
        mDb = creator.getDatabase();

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

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        return super.onStartCommand(intent, flags, startId);
    }

    @Override
    public boolean onUnbind(Intent intent) {
        return super.onUnbind(intent);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        // DO NOT forget to unregister the receiver.
        if (mReceiver != null) {
            LocalBroadcastManager.getInstance(this).unregisterReceiver(mReceiver);
        }
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

        if (mDb == null
                || mDb.zhihuDailyNewsDao().loadZhihuDailyNewsItem(id) == null
                || mDb.zhihuDailyContentDao().loadZhihuDailyContent(id) == null) {
            return;
        }

        mZhihuService.getZhihuContent(id).enqueue(new Callback<ZhihuDailyContent>() {
            @Override
            public void onResponse(Call<ZhihuDailyContent> call, Response<ZhihuDailyContent> response) {
                if (response.body() != null && mDb != null) {
                    mDb.beginTransaction();
                    try {
                        mDb.zhihuDailyContentDao().saveContent(response.body());
                        mDb.setTransactionSuccessful();
                    } finally {
                        mDb.endTransaction();
                    }
                }
            }

            @Override
            public void onFailure(Call<ZhihuDailyContent> call, Throwable t) {

            }
        });
    }

    // Get douban content data by accessing network.
    private void cacheDoubanContent(int id) {

        if (mDb == null
                || mDb.doubanMomentNewsDao().loadDoubanMomentItem(id) == null
                || mDb.doubanMomentContentDao().loadDoubanMomentContent(id) == null) {
            return;
        }

        mDoubanService.getDoubanContent(id).enqueue(new Callback<DoubanMomentContent>() {
            @Override
            public void onResponse(Call<DoubanMomentContent> call, Response<DoubanMomentContent> response) {
                if (response.body() != null && mDb != null) {
                    mDb.beginTransaction();
                    try {
                        mDb.doubanMomentContentDao().saveContent(response.body());
                        mDb.setTransactionSuccessful();
                    } finally {
                        mDb.endTransaction();
                    }
                }
            }

            @Override
            public void onFailure(Call<DoubanMomentContent> call, Throwable t) {

            }
        });
    }

    // Get guokr content data by accessing network.
    private void cacheGuokrContent(int id) {

        if (mDb == null
                || mDb.guokrHandpickNewsDao().loadGuokrHandpickItem(id) == null
                || mDb.guokrHandpickContentDao().loadGuokrHandpickNewsItem(id) == null) {
            return;
        }

        mGuokrService.getGuokrContent(id).enqueue(new Callback<GuokrHandpickContent>() {
            @Override
            public void onResponse(Call<GuokrHandpickContent> call, Response<GuokrHandpickContent> response) {
                if (response.body() != null && mDb != null) {
                    mDb.beginTransaction();
                    try {
                        mDb.guokrHandpickContentDao().saveContent(response.body().getResult());
                        mDb.setTransactionSuccessful();
                    } finally {
                        mDb.endTransaction();
                    }
                }
            }

            @Override
            public void onFailure(Call<GuokrHandpickContent> call, Throwable t) {

            }
        });
    }

}
