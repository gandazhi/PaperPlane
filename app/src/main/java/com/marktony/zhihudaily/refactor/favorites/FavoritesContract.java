package com.marktony.zhihudaily.refactor.favorites;


import com.marktony.zhihudaily.refactor.BasePresenter;
import com.marktony.zhihudaily.refactor.BaseView;
import com.marktony.zhihudaily.refactor.data.DoubanMomentNews;
import com.marktony.zhihudaily.refactor.data.GuokrHandpickNews;
import com.marktony.zhihudaily.refactor.data.ZhihuDailyNews;

import java.util.List;

/**
 * Created by lizhaotailang on 2017/6/6.
 */

public interface FavoritesContract {

    interface View extends BaseView<Presenter> {

        boolean isActive();

        void setLoadingIndicator(boolean active);

        void showFavorites(List<ZhihuDailyNews.Question> zhihuList,
                           List<DoubanMomentNews.Posts> doubanList,
                           List<GuokrHandpickNews.Result> guokrList);

    }

    interface Presenter extends BasePresenter {



    }

}
