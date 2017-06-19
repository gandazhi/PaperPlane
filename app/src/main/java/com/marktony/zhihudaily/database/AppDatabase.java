package com.marktony.zhihudaily.database;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.RoomDatabase;

import com.marktony.zhihudaily.data.DoubanMomentContent;
import com.marktony.zhihudaily.data.DoubanMomentNewsPosts;
import com.marktony.zhihudaily.data.GuokrHandpickContentResult;
import com.marktony.zhihudaily.data.GuokrHandpickNewsResult;
import com.marktony.zhihudaily.data.ZhihuDailyContent;
import com.marktony.zhihudaily.data.ZhihuDailyNewsQuestion;
import com.marktony.zhihudaily.database.dao.DoubanMomentContentDao;
import com.marktony.zhihudaily.database.dao.DoubanMomentNewsDao;
import com.marktony.zhihudaily.database.dao.GuokrHandpickContentDao;
import com.marktony.zhihudaily.database.dao.GuokrHandpickNewsDao;
import com.marktony.zhihudaily.database.dao.ZhihuDailyContentDao;
import com.marktony.zhihudaily.database.dao.ZhihuDailyNewsDao;


/**
 * Created by lizhaotailang on 2017/6/15.
 */

@Database(entities = {
        ZhihuDailyNewsQuestion.class,
        DoubanMomentNewsPosts.class,
        GuokrHandpickNewsResult.class,
        ZhihuDailyContent.class,
        DoubanMomentContent.class,
        GuokrHandpickContentResult.class},
        version = 1)
public abstract class AppDatabase extends RoomDatabase {

    static final String DATABASE_NAME = "paper-plane-db";

    public abstract ZhihuDailyNewsDao zhihuDailyNewsDao();

    public abstract DoubanMomentNewsDao doubanMomentNewsDao();

    public abstract GuokrHandpickNewsDao guokrHandpickNewsDao();

    public abstract ZhihuDailyContentDao zhihuDailyContentDao();

    public abstract DoubanMomentContentDao doubanMomentContentDao();

    public abstract GuokrHandpickContentDao guokrHandpickContentDao();

}
