package com.marktony.zhihudaily.refactor.details;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.annotation.StringRes;

import com.marktony.zhihudaily.refactor.BasePresenter;
import com.marktony.zhihudaily.refactor.BaseView;
import com.marktony.zhihudaily.refactor.data.ContentType;
import com.marktony.zhihudaily.refactor.data.DoubanMomentContent;
import com.marktony.zhihudaily.refactor.data.DoubanMomentThumbs;
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

        void showDoubanMomentContent(@NonNull DoubanMomentContent content, @Nullable List<DoubanMomentThumbs> list);

        void showGuokrHandpickContent(@NonNull GuokrHandpickContent content);

        void share(@Nullable String link);

        void copyLink(@Nullable String link);

        void openWithBrowser(@Nullable String link);

    }

    interface Presenter extends BasePresenter {

        void favorite(ContentType type, boolean favorite);

        void loadDoubanContent(int id);

        void loadZhihuDailyContent(int id);

        void loadGuokrHandpickContent(int id);

        void getLink(ContentType type, int requestCode, int id);

    }

}
