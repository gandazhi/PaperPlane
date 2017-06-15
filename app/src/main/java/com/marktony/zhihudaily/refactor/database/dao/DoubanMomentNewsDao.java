package com.marktony.zhihudaily.refactor.database.dao;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import com.marktony.zhihudaily.refactor.data.DoubanMomentNews;

import java.util.List;

/**
 * Created by lizhaotailang on 2017/6/15.
 */

@Dao
public interface DoubanMomentNewsDao {

    @Query("SELECT * FROM douban_moment")
    List<DoubanMomentNews.Posts> loadDoubanMomentNews();

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertAll(List<DoubanMomentNews.Posts> items);

    @Query("SELECT * FROM douban_moment where id = :id")
    DoubanMomentNews.Posts loadDoubanMomentItem(int id);

    @Update
    void updateDoubanMomentNews(DoubanMomentNews.Posts item);

}
