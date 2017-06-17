package com.marktony.zhihudaily.refactor.timeline;


import android.support.annotation.NonNull;

import com.marktony.zhihudaily.refactor.BasePresenter;
import com.marktony.zhihudaily.refactor.BaseView;
import com.marktony.zhihudaily.refactor.data.DoubanMomentPosts;

import java.util.List;

/**
 * Created by lizhaotailang on 2017/5/21.
 */

public interface DoubanMomentContract {

    interface View extends BaseView<Presenter> {

        void setLoadingIndicator(boolean active);

        boolean isActive();

        void showResult(@NonNull List<DoubanMomentPosts> list);

    }

    interface Presenter extends BasePresenter {

        void load(boolean forceUpdate, boolean clearCache, long date);

        void outdate(int id);

    }

}
