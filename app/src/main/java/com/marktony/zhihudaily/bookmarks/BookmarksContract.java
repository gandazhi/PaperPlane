package com.marktony.zhihudaily.bookmarks;

import com.marktony.zhihudaily.BasePresenter;
import com.marktony.zhihudaily.BaseView;
import com.marktony.zhihudaily.bean.DoubanMomentNews;
import com.marktony.zhihudaily.bean.GuokrHandpickNews;
import com.marktony.zhihudaily.bean.ZhihuDailyNews;

import java.util.ArrayList;

/**
 * Created by lizhaotailang on 2016/12/20.
 */

public interface BookmarksContract {

    interface View extends BaseView<Presenter> {

        void showResults(ArrayList<ZhihuDailyNews.Question> zhihuList,
                        ArrayList<GuokrHandpickNews.result> guokrList,
                        ArrayList<DoubanMomentNews.posts> doubanList);

        void notifyDataChanged();

        void showLoading();

        void stopLoading();

    }

    interface Presenter extends BasePresenter {

        void loadResults(boolean refresh);

        void startZhihuReading(int position);

        void startGuokrReading(int position);

        void startDoubanReading(int position);

        void refresh();

    }

}
