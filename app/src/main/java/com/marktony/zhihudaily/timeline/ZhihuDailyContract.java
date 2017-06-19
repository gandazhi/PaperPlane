package com.marktony.zhihudaily.timeline;

import android.support.annotation.NonNull;

import com.marktony.zhihudaily.BasePresenter;
import com.marktony.zhihudaily.BaseView;
import com.marktony.zhihudaily.data.ZhihuDailyNewsQuestion;

import java.util.List;

/**
 * Created by lizhaotailang on 2017/5/21.
 */

public interface ZhihuDailyContract {

    interface View extends BaseView<Presenter> {

        boolean isActive();

        void setLoadingIndicator(boolean active);

        void showResult(@NonNull List<ZhihuDailyNewsQuestion> list);

    }

    interface Presenter extends BasePresenter {

        void loadNews(boolean forceUpdate, boolean clearCache, long date);

        void outdate(int id);

    }

}
