package com.marktony.zhihudaily.refactor.details;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.annotation.StringRes;

import com.marktony.zhihudaily.refactor.BasePresenter;
import com.marktony.zhihudaily.refactor.BaseView;
import com.marktony.zhihudaily.refactor.data.ArticleType;

/**
 * Created by lizhaotailang on 2017/5/24.
 */

public interface DetailsContract {

    interface View extends BaseView<Presenter> {

        void setLoadingIndicator(boolean active);

        void showMessage(@StringRes int stringRes);

        void setTitle(@NonNull String title);

        void showResult(@ArticleType.TYPE int type, @NonNull String content, boolean withoutBody);

        void showCover(@Nullable String url);

    }

    interface Presenter extends BasePresenter {

        void favorite(boolean favorited);

        void refresh(@NonNull String id);

    }

}
