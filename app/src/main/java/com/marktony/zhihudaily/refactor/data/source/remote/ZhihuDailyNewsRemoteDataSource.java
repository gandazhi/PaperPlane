package com.marktony.zhihudaily.refactor.data.source.remote;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import com.marktony.zhihudaily.refactor.data.ZhihuDailyNews;
import com.marktony.zhihudaily.refactor.data.source.datasource.ZhihuDailyNewsDataSource;
import com.marktony.zhihudaily.refactor.retrofit.RetrofitService;
import com.marktony.zhihudaily.refactor.util.DateFormatUtil;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by lizhaotailang on 2017/5/21.
 */

public class ZhihuDailyNewsRemoteDataSource implements ZhihuDailyNewsDataSource {

    @Nullable
    private static ZhihuDailyNewsRemoteDataSource INSTANCE = null;

    public static ZhihuDailyNewsRemoteDataSource getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new ZhihuDailyNewsRemoteDataSource();
        }
        return INSTANCE;
    }

    @Override
    public void getZhihuDailyNews(boolean loadMore, long date, @NonNull LoadZhihuDailyNewsCallback callback) {

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(RetrofitService.ZHIHU_DAILY_BASE)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        RetrofitService.ZhihuDailyService service = retrofit.create(RetrofitService.ZhihuDailyService.class);

        service.getZhihuList(DateFormatUtil.ZhihuDailyDateFormat(date))
                .enqueue(new Callback<ZhihuDailyNews>() {
                    @Override
                    public void onResponse(Call<ZhihuDailyNews> call, Response<ZhihuDailyNews> response) {
                        callback.onNewsLoaded(response.body().getStories());
                    }

                    @Override
                    public void onFailure(Call<ZhihuDailyNews> call, Throwable t) {
                        callback.onDataNotAvailable();
                    }
                });

    }

    @Override
    public void getItem(int itemId, @NonNull GetNewsItemCallback callback) {

    }

    @Override
    public void favoriteItem(int itemId, boolean favorited) {

    }

    @Override
    public void outdateItem(int itemId) {

    }

    @Override
    public void refreshZhihuDailyNews() {

    }

    @Override
    public void saveItem(@NonNull ZhihuDailyNews.Question item) {

    }
}
