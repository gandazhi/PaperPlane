package com.marktony.zhihudaily.timeline;


import android.support.annotation.NonNull;

import com.marktony.zhihudaily.BasePresenter;
import com.marktony.zhihudaily.BaseView;
import com.marktony.zhihudaily.data.DoubanMomentNewsPosts;

import java.util.List;

/**
 * Created by lizhaotailang on 2017/5/21.
 */

public interface DoubanMomentContract {

    interface View extends BaseView<Presenter> {

        void setLoadingIndicator(boolean active);

        boolean isActive();

        void showResult(@NonNull List<DoubanMomentNewsPosts> list);

    }

    interface Presenter extends BasePresenter {

        void load(boolean forceUpdate, boolean clearCache, long date);

        void outdate(int id);

    }

}
