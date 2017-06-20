package com.marktony.zhihudaily.favorites;

import android.support.annotation.NonNull;

import com.marktony.zhihudaily.data.DoubanMomentNewsPosts;
import com.marktony.zhihudaily.data.GuokrHandpickNewsResult;
import com.marktony.zhihudaily.data.ZhihuDailyNewsQuestion;
import com.marktony.zhihudaily.data.source.datasource.DoubanMomentNewsDataSource;
import com.marktony.zhihudaily.data.source.datasource.GuokrHandpickDataSource;
import com.marktony.zhihudaily.data.source.datasource.ZhihuDailyNewsDataSource;
import com.marktony.zhihudaily.data.source.repository.DoubanMomentNewsRepository;
import com.marktony.zhihudaily.data.source.repository.GuokrHandpickNewsRepository;
import com.marktony.zhihudaily.data.source.repository.ZhihuDailyNewsRepository;

import java.util.List;

/**
 * Created by lizhaotailang on 2017/6/6.
 */

public class FavoritesPresenter implements FavoritesContract.Presenter {

    @NonNull
    private final FavoritesContract.View mView;

    @NonNull
    private final ZhihuDailyNewsRepository mZhihuRepository;

    @NonNull
    private final DoubanMomentNewsRepository mDoubanRepository;

    @NonNull
    private final GuokrHandpickNewsRepository mGuokrRepository;

    public FavoritesPresenter(@NonNull FavoritesContract.View view,
                              @NonNull ZhihuDailyNewsRepository zhihuRepository,
                              @NonNull DoubanMomentNewsRepository doubanRepository,
                              @NonNull GuokrHandpickNewsRepository guokrRepository) {
        mView = view;
        mView.setPresenter(this);
        this.mZhihuRepository = zhihuRepository;
        this.mDoubanRepository = doubanRepository;
        this.mGuokrRepository = guokrRepository;
    }

    @Override
    public void start() {

    }

    @Override
    public void loadFavorites() {
        mZhihuRepository.getFavorites(new ZhihuDailyNewsDataSource.LoadZhihuDailyNewsCallback() {
            @Override
            public void onNewsLoaded(@NonNull List<ZhihuDailyNewsQuestion> zhihuList) {

                mDoubanRepository.getFavorites(new DoubanMomentNewsDataSource.LoadDoubanMomentDailyCallback() {
                    @Override
                    public void onNewsLoaded(@NonNull List<DoubanMomentNewsPosts> doubanList) {

                        mGuokrRepository.getFavorites(new GuokrHandpickDataSource.LoadGuokrHandpickNewsCallback() {
                            @Override
                            public void onNewsLoad(@NonNull List<GuokrHandpickNewsResult> guokrList) {
                                if (mView.isActive()) {
                                    mView.showFavorites(zhihuList, doubanList, guokrList);
                                }
                                mView.setLoadingIndicator(false);
                            }

                            @Override
                            public void onDataNotAvailable(){
                                mView.setLoadingIndicator(false);
                            }
                        });
                    }

                    @Override
                    public void onDataNotAvailable() {

                    }
                });
            }

            @Override
            public void onDataNotAvailable() {

            }
        });
    }
}
