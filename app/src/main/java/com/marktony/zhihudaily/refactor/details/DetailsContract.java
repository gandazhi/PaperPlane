package com.marktony.zhihudaily.refactor.details;

import android.support.annotation.NonNull;
import android.support.annotation.StringRes;

import com.marktony.zhihudaily.refactor.BasePresenter;
import com.marktony.zhihudaily.refactor.BaseView;

/**
 * Created by lizhaotailang on 2017/5/24.
 */

public interface DetailsContract {

    interface View extends BaseView<Presenter> {

        void setLoadingIndicator(boolean active);

        void showMessage(@StringRes int stringRes);

        void setTitle(@NonNull String title);

        void showResult(@NonNull String content, boolean withoutBody);

    }

    interface Presenter extends BasePresenter {

        void favorite(boolean favorited);

    }

}
