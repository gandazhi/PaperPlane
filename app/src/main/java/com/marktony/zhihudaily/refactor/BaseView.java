package com.marktony.zhihudaily.refactor;

import android.view.View;

/**
 * Created by lizhaotailang on 2017/5/20.
 * The base of all view layers.
 */

public interface BaseView<T> {

    void setPresenter(T presenter);

    void initViews(View view);

}
