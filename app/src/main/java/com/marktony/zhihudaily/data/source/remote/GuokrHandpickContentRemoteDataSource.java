package com.marktony.zhihudaily.data.source.remote;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import com.marktony.zhihudaily.data.GuokrHandpickContent;
import com.marktony.zhihudaily.data.GuokrHandpickContentResult;
import com.marktony.zhihudaily.data.source.datasource.GuokrHandpickContentDataSource;
import com.marktony.zhihudaily.retrofit.RetrofitService;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by lizhaotailang on 2017/5/26.
 * Implementation of the data source that accesses network.
 */

public class GuokrHandpickContentRemoteDataSource implements GuokrHandpickContentDataSource {

    @Nullable
    private static GuokrHandpickContentRemoteDataSource INSTANCE = null;

    // Prevent direct instantiation.
    private GuokrHandpickContentRemoteDataSource() {}

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
                        callback.onContentLoaded(response.body().getResult());
                    }

                    @Override
                    public void onFailure(Call<GuokrHandpickContent> call, Throwable t) {

                        callback.onDataNotAvailable();
                    }
                });
    }

    @Override
    public void saveContent(@NonNull GuokrHandpickContentResult content) {
        // Not required for the remote data source because the {@link TasksRepository} handles
        // converting from a {@code taskId} to a {@link task} using its cached data.
    }
}
