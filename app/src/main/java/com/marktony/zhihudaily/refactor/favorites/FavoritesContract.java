package com.marktony.zhihudaily.refactor.favorites;


import com.marktony.zhihudaily.refactor.BasePresenter;
import com.marktony.zhihudaily.refactor.BaseView;
import com.marktony.zhihudaily.refactor.data.DoubanMomentPosts;
import com.marktony.zhihudaily.refactor.data.GuokrHandpickNewsResult;
import com.marktony.zhihudaily.refactor.data.ZhihuDailyNewsQuestion;

import java.util.List;

/**
 * Created by lizhaotailang on 2017/6/6.
 */

public interface FavoritesContract {

    interface View extends BaseView<Presenter> {

        boolean isActive();

        void setLoadingIndicator(boolean active);

        void showFavorites(List<ZhihuDailyNewsQuestion> zhihuList,
                           List<DoubanMomentPosts> doubanList,
                           List<GuokrHandpickNewsResult> guokrList);

    }

    interface Presenter extends BasePresenter {



    }

}
