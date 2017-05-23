package com.marktony.zhihudaily.refactor.timeline;

import android.support.annotation.NonNull;

import com.marktony.zhihudaily.refactor.BasePresenter;
import com.marktony.zhihudaily.refactor.BaseView;
import com.marktony.zhihudaily.refactor.data.ZhihuDailyNews;

import java.util.List;

/**
 * Created by lizhaotailang on 2017/5/21.
 */

public interface ZhihuDailyContract {

    interface View extends BaseView<Presenter> {

        void setLoadingIndicator(boolean active);

        void showResult(@NonNull List<ZhihuDailyNews.Question> list);

    }

    interface Presenter extends BasePresenter {

        void loadNews(boolean isLoadMore, boolean forceUpdate, long date);

    }

}
