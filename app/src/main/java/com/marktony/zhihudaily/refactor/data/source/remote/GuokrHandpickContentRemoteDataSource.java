package com.marktony.zhihudaily.refactor.data.source.remote;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.Log;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.marktony.zhihudaily.refactor.data.GuokrHandpickContent;
import com.marktony.zhihudaily.refactor.data.source.datasource.GuokrHandpickContentDataSource;
import com.marktony.zhihudaily.refactor.retrofit.RetrofitService;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by lizhaotailang on 2017/5/26.
 */

public class GuokrHandpickContentRemoteDataSource implements GuokrHandpickContentDataSource {

    @Nullable
    private static GuokrHandpickContentRemoteDataSource INSTANCE = null;

    private GuokrHandpickContentRemoteDataSource() {

    }

    public static GuokrHandpickContentRemoteDataSource getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new GuokrHandpickContentRemoteDataSource();
        }
        return INSTANCE;
    }

    public static void destroyInstance() {
        INSTANCE = null;
    }

    @Override
    public void getGuokrHandpickContent(int id, @NonNull LoadGuokrHandpickContentCallback callback) {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(RetrofitService.GUOKR_HANDPICK_BASE)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        RetrofitService.GuokrHandpickService service = retrofit.create(RetrofitService.GuokrHandpickService.class);

        service.getGuokrContent(id)
                .enqueue(new Callback<GuokrHandpickContent>() {
                    @Override
                    public void onResponse(Call<GuokrHandpickContent> call, Response<GuokrHandpickContent> response) {
                        Log.d("TAG", "onResponse: " + response.raw().request().url());
                        callback.onContentLoaded(response.body());
                    }

                    @Override
                    public void onFailure(Call<GuokrHandpickContent> call, Throwable t) {
                        Log.d("TAG", "onFailure: " + t.getLocalizedMessage());
                        callback.onDataNotAvailable();
                    }
                });
    }

    @Override
    public void favorite(boolean favorite) {

    }

    @Override
    public void saveContent(@NonNull GuokrHandpickContent content) {

    }
}
