package com.marktony.zhihudaily.refactor.details;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.annotation.StringRes;

import com.marktony.zhihudaily.refactor.BasePresenter;
import com.marktony.zhihudaily.refactor.BaseView;
import com.marktony.zhihudaily.refactor.data.DoubanMomentContent;
import com.marktony.zhihudaily.refactor.data.DoubanMomentNews;
import com.marktony.zhihudaily.refactor.data.ZhihuDailyContent;

import java.util.List;

/**
 * Created by lizhaotailang on 2017/5/24.
 */

public interface DetailsContract {

    interface View extends BaseView<Presenter> {

        void setLoadingIndicator(boolean active);

        void showMessage(@StringRes int stringRes);

        void showZhihuDailyContent(@NonNull ZhihuDailyContent content);

        void showDoubanMomentContent(@NonNull DoubanMomentContent content, @Nullable List<DoubanMomentNews.Posts.Thumbs> list);

    }

    interface Presenter extends BasePresenter {

        // void load(@NonNull String id);

        void favorite(boolean favorite);

        void refresh(@NonNull String id);

        void loadDoubanContent(boolean forceUpdate, int id);

        void loadZhihuDailyContent(boolean forceUpdate, int id);
    }

}
