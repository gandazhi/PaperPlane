package com.marktony.zhihudaily.database.dao;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import com.marktony.zhihudaily.data.ZhihuDailyContent;

import java.util.List;

/**
 * Created by lizhaotailang on 2017/6/15.
 */

@Dao
public interface ZhihuDailyContentDao {

    @Query("SELECT * FROM zhihu_daily_content WHERE id = :id")
    ZhihuDailyContent queryContentById(int id);

    @Query("SELECT * FROM zhihu_daily_content WHERE timestamp > :timestamp")
    List<ZhihuDailyContent> queryAllTimeoutContents(long timestamp);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insert(ZhihuDailyContent content);

    @Update
    void update(ZhihuDailyContent content);

    @Delete
    void delete(ZhihuDailyContent content);

}
