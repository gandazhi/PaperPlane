package com.marktony.zhihudaily.refactor.data.source.remote;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import com.marktony.zhihudaily.refactor.data.GuokrHandpickNews;
import com.marktony.zhihudaily.refactor.data.source.datasource.GuokrHandpickDataSource;
import com.marktony.zhihudaily.refactor.retrofit.RetrofitService;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by lizhaotailang on 2017/5/24.
 */

public class GuokrHandpickNewsRemoteDataSource implements GuokrHandpickDataSource{

    @Nullable
    private static GuokrHandpickNewsRemoteDataSource INSTANCE = null;

    private GuokrHandpickNewsRemoteDataSource() {

    }

    public static GuokrHandpickNewsRemoteDataSource getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new GuokrHandpickNewsRemoteDataSource();
        }
        return INSTANCE;
    }

    @Override
    public void getGuokrHandpickNews(boolean forceUpdate, boolean clearCache, int offset, int limit, @NonNull LoadGuokrHandpickNewsCallback callback) {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(RetrofitService.GUOKR_HANDPICK_BASE)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        RetrofitService.GuokrHandpickService service = retrofit.create(RetrofitService.GuokrHandpickService.class);

        service.getGuokrHandpick(offset, limit)
                .enqueue(new Callback<GuokrHandpickNews>() {
                    @Override
                    public void onResponse(Call<GuokrHandpickNews> call, Response<GuokrHandpickNews> response) {
                        callback.onNewsLoad(response.body().getResult());
                    }

                    @Override
                    public void onFailure(Call<GuokrHandpickNews> call, Throwable t) {
                        callback.onDataNotAvailable();
                    }
                });
    }

    @Override
    public void getItem(int itemId, @NonNull GetNewsItemCallback callback) {

    }

    @Override
    public void favoriteItem(int itemId, boolean favorite) {

    }

    @Override
    public void outdateItem(int itemId) {

    }

    @Override
    public void saveItem(@NonNull GuokrHandpickNews.Result item) {

    }
}
