package com.marktony.zhihudaily.favorites;


import com.marktony.zhihudaily.BasePresenter;
import com.marktony.zhihudaily.BaseView;
import com.marktony.zhihudaily.data.DoubanMomentNewsPosts;
import com.marktony.zhihudaily.data.GuokrHandpickNewsResult;
import com.marktony.zhihudaily.data.ZhihuDailyNewsQuestion;

import java.util.List;

/**
 * Created by lizhaotailang on 2017/6/6.
 */

public interface FavoritesContract {

    interface View extends BaseView<Presenter> {

        boolean isActive();

        void setLoadingIndicator(boolean active);

        void showFavorites(List<ZhihuDailyNewsQuestion> zhihuList,
                           List<DoubanMomentNewsPosts> doubanList,
                           List<GuokrHandpickNewsResult> guokrList);

    }

    interface Presenter extends BasePresenter {

        void loadFavorites();

    }

}
