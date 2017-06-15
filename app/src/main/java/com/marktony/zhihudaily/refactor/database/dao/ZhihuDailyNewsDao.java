package com.marktony.zhihudaily.refactor.database.dao;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import com.marktony.zhihudaily.refactor.data.ZhihuDailyNews;

import java.util.List;

/**
 * Created by lizhaotailang on 2017/6/15.
 */

@Dao
public interface ZhihuDailyNewsDao {

    @Query("SELECT * FROM zhihu_daily")
    List<ZhihuDailyNews.Question> loadAllZhihuDailyNews();

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertAll(List<ZhihuDailyNews.Question> items);

    @Query("SELECT * FROM zhihu_daily where id = :id")
    ZhihuDailyNews.Question loadZhihuDailyNewsItem(int id);

    @Update
    void updateZhihuDailyNews(ZhihuDailyNews.Question question);

}
