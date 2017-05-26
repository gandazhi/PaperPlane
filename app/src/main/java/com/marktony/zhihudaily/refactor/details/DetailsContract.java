package com.marktony.zhihudaily.refactor.details;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.annotation.StringRes;

import com.marktony.zhihudaily.refactor.BasePresenter;
import com.marktony.zhihudaily.refactor.BaseView;
import com.marktony.zhihudaily.refactor.data.DoubanMomentContent;
import com.marktony.zhihudaily.refactor.data.DoubanMomentNews;
import com.marktony.zhihudaily.refactor.data.GuokrHandpickContent;
import com.marktony.zhihudaily.refactor.data.ZhihuDailyContent;

import java.util.List;

/**
 * Created by lizhaotailang on 2017/5/24.
 */

public interface DetailsContract {

    interface View extends BaseView<Presenter> {

        void showMessage(@StringRes int stringRes);

        boolean isActive();

        void showZhihuDailyContent(@NonNull ZhihuDailyContent content);

        void showDoubanMomentContent(@NonNull DoubanMomentContent content, @Nullable List<DoubanMomentNews.Posts.Thumbs> list);

        void showGuokrHandpickContent(@NonNull GuokrHandpickContent content);

    }

    interface Presenter extends BasePresenter {

        void favorite(boolean favorite);

        void refresh(@NonNull String id);

        void loadDoubanContent(int id);

        void loadZhihuDailyContent(int id);

        void loadGuokrHandpickContent(int id);
    }

}
