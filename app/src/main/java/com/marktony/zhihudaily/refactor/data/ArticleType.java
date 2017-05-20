package com.marktony.zhihudaily.refactor.data;

import android.support.annotation.IntDef;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

/**
 * Created by lizhaotailang on 2017/5/20.
 * Use annotations rather than enum.
 */

public class ArticleType {

    private static final int ZHIHU_DAILY = 0x00;
    private static final int GUOKR_HANDPICK = 0x01;
    private static final int DOUBAN_MOMENT = 0x02;

    @IntDef({ZHIHU_DAILY, GUOKR_HANDPICK, DOUBAN_MOMENT})
    @Retention(RetentionPolicy.SOURCE)
    public @interface TYPE {

    }

}
