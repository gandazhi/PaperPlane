package com.marktony.zhihudaily.data.source.remote;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import com.marktony.zhihudaily.data.DoubanMomentNews;
import com.marktony.zhihudaily.data.DoubanMomentNewsPosts;
import com.marktony.zhihudaily.data.source.datasource.DoubanMomentNewsDataSource;
import com.marktony.zhihudaily.retrofit.RetrofitService;
import com.marktony.zhihudaily.util.DateFormatUtil;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by lizhaotailang on 2017/5/21.
 * Implementation of the data source that accesses network.
 */

public class DoubanMomentNewsRemoteDataSource implements DoubanMomentNewsDataSource {

    @Nullable
    private static DoubanMomentNewsRemoteDataSource INSTANCE = null;

    // Prevent direct instantiation.
    private DoubanMomentNewsRemoteDataSource() {}

    public static DoubanMomentNewsRemoteDataSource getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new DoubanMomentNewsRemoteDataSource();
        }
        return INSTANCE;
    }

    @Override
    public void getDoubanMomentNews(boolean forceUpdate, boolean clearCache, long date, @NonNull LoadDoubanMomentDailyCallback callback) {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(RetrofitService.DOUBAN_MOMENT_BASE)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        RetrofitService.DoubanMomentService service = retrofit.create(RetrofitService.DoubanMomentService.class);

        service.getDoubanList(DateFormatUtil.formatDoubanMomentDateLongToString(date))
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
    public void getFavorites(@NonNull LoadDoubanMomentDailyCallback callback) {
        // Not required for the remote data source because the {@link TasksRepository} handles
        // converting from a {@code taskId} to a {@link task} using its cached data.
    }

    @Override
    public void getItem(int id, @NonNull GetNewsItemCallback callback) {
        // Not required for the remote data source because the {@link TasksRepository} handles
        // converting from a {@code taskId} to a {@link task} using its cached data.
    }

    @Override
    public void favoriteItem(int itemId, boolean favorited) {
        // Not required for the remote data source because the {@link TasksRepository} handles
        // converting from a {@code taskId} to a {@link task} using its cached data.
    }

    @Override
    public void outdateItem(int itemId) {
        // Not required for the remote data source because the {@link TasksRepository} handles
        // converting from a {@code taskId} to a {@link task} using its cached data.
    }

    @Override
    public void saveAll(@NonNull List<DoubanMomentNewsPosts> list) {
        // Not required for the remote data source because the {@link TasksRepository} handles
        // converting from a {@code taskId} to a {@link task} using its cached data.
    }

}
