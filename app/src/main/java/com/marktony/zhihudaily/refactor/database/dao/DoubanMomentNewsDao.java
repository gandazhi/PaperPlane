package com.marktony.zhihudaily.refactor.database.dao;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import com.marktony.zhihudaily.refactor.data.DoubanMomentPosts;

import java.util.List;

/**
 * Created by lizhaotailang on 2017/6/15.
 */

@Dao
public interface DoubanMomentNewsDao {

    @Query("SELECT * FROM douban_moment_news")
    List<DoubanMomentPosts> loadDoubanMomentNews();

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertAll(List<DoubanMomentPosts> items);

    @Query("SELECT * FROM douban_moment_news where id = :id")
    DoubanMomentPosts loadDoubanMomentItem(int id);

    @Update
    void updateDoubanMomentNews(DoubanMomentPosts item);

}
