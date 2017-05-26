package com.marktony.zhihudaily.refactor.details;

import android.support.annotation.NonNull;

import com.marktony.zhihudaily.refactor.data.DoubanMomentContent;
import com.marktony.zhihudaily.refactor.data.DoubanMomentNews;
import com.marktony.zhihudaily.refactor.data.GuokrHandpickContent;
import com.marktony.zhihudaily.refactor.data.ZhihuDailyContent;
import com.marktony.zhihudaily.refactor.data.source.datasource.DoubanMomentContentDataSource;
import com.marktony.zhihudaily.refactor.data.source.datasource.GuokrHandpickContentDataSource;
import com.marktony.zhihudaily.refactor.data.source.datasource.ZhihuDailyContentDataSource;
import com.marktony.zhihudaily.refactor.data.source.repository.DoubanMomentContentRepository;
import com.marktony.zhihudaily.refactor.data.source.datasource.DoubanMomentNewsDataSource;
import com.marktony.zhihudaily.refactor.data.source.repository.DoubanMomentNewsRepository;
import com.marktony.zhihudaily.refactor.data.source.repository.GuokrHandpickContentRepository;
import com.marktony.zhihudaily.refactor.data.source.repository.ZhihuDailyContentRepository;

/**
 * Created by lizhaotailang on 2017/5/24.
 */

public class DetailsPresenter implements DetailsContract.Presenter {

    @NonNull
    private final DetailsContract.View mView;

    private DoubanMomentNewsRepository mDoubanNewsRepository;
    private DoubanMomentContentRepository mDoubanContentRepository;

    private ZhihuDailyContentRepository mZhihuContentRepository;

    private GuokrHandpickContentRepository mGuokrContentRepository;

    public DetailsPresenter(@NonNull DetailsContract.View view,
                            @NonNull DoubanMomentNewsRepository doubanNewsRepository,
                            @NonNull DoubanMomentContentRepository doubanContentRepository) {
        this.mView = view;
        mView.setPresenter(this);
        this.mDoubanContentRepository = doubanContentRepository;
        this.mDoubanNewsRepository = doubanNewsRepository;
    }

    public DetailsPresenter(@NonNull DetailsContract.View view,
                            @NonNull ZhihuDailyContentRepository zhihuContentRepository) {
        this.mView = view;
        mView.setPresenter(this);
        mZhihuContentRepository = zhihuContentRepository;
    }

    public DetailsPresenter(@NonNull DetailsContract.View view,
                           @NonNull GuokrHandpickContentRepository guokrContentRepository) {
        this.mView = view;
        this.mView.setPresenter(this);
        mGuokrContentRepository = guokrContentRepository;
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
    public void loadDoubanContent(int id) {
        mDoubanContentRepository.getDoubanMomentContent(id, new DoubanMomentContentDataSource.LoadDoubanMomentContentCallback() {
            @Override
            public void onContentLoaded(@NonNull DoubanMomentContent content) {
                mDoubanNewsRepository.getItem(id, new DoubanMomentNewsDataSource.GetNewsItemCallback() {
                    @Override
                    public void onItemLoaded(@NonNull DoubanMomentNews.Posts item) {
                        if (mView.isActive()) {
                            mView.showDoubanMomentContent(content, item.getThumbs());
                        }
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

    @Override
    public void loadZhihuDailyContent(int id) {
        mZhihuContentRepository.getZhihuDailyContent(id, new ZhihuDailyContentDataSource.LoadZhihuDailyContentCallback() {
            @Override
            public void onContentLoaded(@NonNull ZhihuDailyContent content) {
                if (mView.isActive()) {
                    mView.showZhihuDailyContent(content);
                }
            }

            @Override
            public void onDataNotAvailable() {

            }
        });
    }

    @Override
    public void loadGuokrHandpickContent(int id) {
        mGuokrContentRepository.getGuokrHandpickContent(id, new GuokrHandpickContentDataSource.LoadGuokrHandpickContentCallback() {
            @Override
            public void onContentLoaded(@NonNull GuokrHandpickContent content) {
                if (mView.isActive()) {
                    mView.showGuokrHandpickContent(content);
                }
            }

            @Override
            public void onDataNotAvailable() {

            }
        });
    }
}
