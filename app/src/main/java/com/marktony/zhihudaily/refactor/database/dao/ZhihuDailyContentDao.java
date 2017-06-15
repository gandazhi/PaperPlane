package com.marktony.zhihudaily.refactor.database.dao;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import com.marktony.zhihudaily.refactor.data.ZhihuDailyContent;

/**
 * Created by lizhaotailang on 2017/6/15.
 */

@Dao
public interface ZhihuDailyContentDao {

    @Query("SELECT * FROM zhihu_daily_content where id = :id")
    ZhihuDailyContent loadZhihuDailyContent(int id);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertZhihuDailyContent(ZhihuDailyContent content);

    @Update
    void updateZhihuDailyContent(ZhihuDailyContent item);

}
