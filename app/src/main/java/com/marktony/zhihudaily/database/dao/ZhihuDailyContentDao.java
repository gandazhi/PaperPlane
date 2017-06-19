package com.marktony.zhihudaily.database.dao;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import com.marktony.zhihudaily.data.ZhihuDailyContent;

import java.util.List;

/**
 * Created by lizhaotailang on 2017/6/15.
 */

@Dao
public interface ZhihuDailyContentDao {

    @Query("SELECT * FROM zhihu_daily_content")
    List<ZhihuDailyContent> loadAllZhihuContents();

    @Query("SELECT * FROM zhihu_daily_content WHERE id = :id")
    ZhihuDailyContent loadZhihuDailyContent(int id);

    @Insert
    void saveContent(ZhihuDailyContent content);

    @Update
    void updateContent(ZhihuDailyContent content);

    @Delete
    void deleteContent(ZhihuDailyContent content);

}
