package com.marktony.zhihudaily.refactor.details;

import android.support.annotation.NonNull;

import com.marktony.zhihudaily.refactor.data.DoubanMomentContent;
import com.marktony.zhihudaily.refactor.data.DoubanMomentNews;
import com.marktony.zhihudaily.refactor.data.source.datasource.DoubanMomentContentDataSource;
import com.marktony.zhihudaily.refactor.data.source.repository.DoubanMomentContentRepository;
import com.marktony.zhihudaily.refactor.data.source.datasource.DoubanMomentNewsDataSource;
import com.marktony.zhihudaily.refactor.data.source.repository.DoubanMomentNewsRepository;
import com.marktony.zhihudaily.refactor.data.source.repository.ZhihuDailyNewsRepository;

/**
 * Created by lizhaotailang on 2017/5/24.
 */

public class DetailsPresenter implements DetailsContract.Presenter {

    @NonNull
    private final DetailsContract.View mView;

    private DoubanMomentNewsRepository mDoubanNewsRepository;
    private DoubanMomentContentRepository mDoubanContentRepository;

    public DetailsPresenter(@NonNull DetailsContract.View view,
                            @NonNull DoubanMomentNewsRepository doubanNewsRepository,
                            @NonNull DoubanMomentContentRepository doubanContentRepository) {
        this.mView = view;
        mView.setPresenter(this);
        this.mDoubanContentRepository = doubanContentRepository;
        this.mDoubanNewsRepository = doubanNewsRepository;
    }

    public DetailsPresenter(@NonNull DetailsContract.View view,
                            @NonNull ZhihuDailyNewsRepository zhihuRepository) {
        this.mView = view;
        mView.setPresenter(this);
    }

    @Override
    public void start() {

    }

    @Override
    public void favorite(boolean favorite) {

    }

    @Override
    public void refresh(@NonNull String id) {

    }

    @Override
    public void loadDoubanContent(boolean forceUpdate, int id) {

        if (!forceUpdate) {
            mView.setLoadingIndicator(true);
        }
        mDoubanContentRepository.getDoubanMomentContent(id, new DoubanMomentContentDataSource.LoadDoubanMomentContentCallback() {
            @Override
            public void onContentLoaded(@NonNull DoubanMomentContent content) {
                mDoubanNewsRepository.getItem(id, new DoubanMomentNewsDataSource.GetNewsItemCallback() {
                    @Override
                    public void onItemLoaded(@NonNull DoubanMomentNews.Posts item) {
                        mView.showDoubanMomentContent(content, item.getThumbs());
                        mView.setLoadingIndicator(false);
                    }

                    @Override
                    public void onDataNotAvailable() {
                        mView.setLoadingIndicator(false);
                    }
                });
            }

            @Override
            public void onDataNotAvailable() {
                mView.setLoadingIndicator(false);
            }
        });
    }

    @Override
    public void loadZhihuDailyContent(boolean forceUpdate, int id) {
        if (!forceUpdate) {
            mView.setLoadingIndicator(true);
        }
    }
}
