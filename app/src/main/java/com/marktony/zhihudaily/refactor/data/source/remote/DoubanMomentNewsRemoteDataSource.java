package com.marktony.zhihudaily.refactor.data.source.remote;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import com.marktony.zhihudaily.refactor.data.DoubanMomentNews;
import com.marktony.zhihudaily.refactor.data.source.DoubanMomentNewsDataSource;
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

public class DoubanMomentNewsRemoteDataSource implements DoubanMomentNewsDataSource {

    @Nullable
    private static DoubanMomentNewsRemoteDataSource INSTANCE = null;

    private DoubanMomentNewsRemoteDataSource() {

    }

    public static DoubanMomentNewsRemoteDataSource getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new DoubanMomentNewsRemoteDataSource();
        }
        return INSTANCE;
    }

    @Override
    public void getDoubanMomentNews(boolean loadMore, long date, @NonNull LoadDoubanMomentDailyCallback callback) {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(RetrofitService.DOUBAN_MOMENT_BASE)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        RetrofitService.DoubanMomentService service = retrofit.create(RetrofitService.DoubanMomentService.class);

        service.getDoubanList(DateFormatUtil.DoubanDateFormat(date))
                .enqueue(new Callback<DoubanMomentNews>() {
                    @Override
                    public void onResponse(Call<DoubanMomentNews> call, Response<DoubanMomentNews> response) {
                        callback.onNewsLoaded(response.body().getPosts());
                    }

                    @Override
                    public void onFailure(Call<DoubanMomentNews> call, Throwable t) {
                        callback.onDataNotAvailable();
                    }
                });
    }

    @Override
    public void getItem(@NonNull String id, @NonNull GetNewsItemCallback callback) {

    }

    @Override
    public void favoriteItem(@NonNull String itemId, boolean favorited) {

    }

    @Override
    public void outdateItem(@NonNull String itemId) {

    }

    @Override
    public void refreshDoubanMomentNews() {

    }

    @Override
    public void saveItem(@NonNull DoubanMomentNews.Posts item) {

    }

}
