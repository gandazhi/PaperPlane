package com.marktony.zhihudaily.refactor.favorites;

import com.marktony.zhihudaily.refactor.BasePresenter;
import com.marktony.zhihudaily.refactor.BaseView;

/**
 * Created by lizhaotailang on 2017/6/6.
 */

public interface FavoritesContract {

    interface View extends BaseView<Presenter> {

        boolean isActive();

        void setLoadingIndicator(boolean active);

    }

    interface Presenter extends BasePresenter {



    }

}
