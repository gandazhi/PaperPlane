package com.marktony.zhihudaily.details;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.annotation.StringRes;

import com.marktony.zhihudaily.BasePresenter;
import com.marktony.zhihudaily.BaseView;
import com.marktony.zhihudaily.data.ContentType;
import com.marktony.zhihudaily.data.DoubanMomentContent;
import com.marktony.zhihudaily.data.DoubanMomentNewsThumbs;
import com.marktony.zhihudaily.data.GuokrHandpickContentResult;
import com.marktony.zhihudaily.data.ZhihuDailyContent;

import java.util.List;

/**
 * Created by lizhaotailang on 2017/5/24.
 */

public interface DetailsContract {

    interface View extends BaseView<Presenter> {

        void showMessage(@StringRes int stringRes);

        boolean isActive();

        void showZhihuDailyContent(@NonNull ZhihuDailyContent content);

        void showDoubanMomentContent(@NonNull DoubanMomentContent content, @Nullable List<DoubanMomentNewsThumbs> list);

        void showGuokrHandpickContent(@NonNull GuokrHandpickContentResult content);

        void share(@Nullable String link);

        void copyLink(@Nullable String link);

        void openWithBrowser(@Nullable String link);

    }

    interface Presenter extends BasePresenter {

        void favorite(ContentType type, int id, boolean favorite);

        void loadDoubanContent(int id);

        void loadZhihuDailyContent(int id);

        void loadGuokrHandpickContent(int id);

        void getLink(ContentType type, int requestCode, int id);

    }

}
