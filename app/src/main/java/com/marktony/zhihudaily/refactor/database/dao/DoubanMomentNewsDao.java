package com.marktony.zhihudaily.refactor.database.dao;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import com.marktony.zhihudaily.refactor.data.DoubanMomentNewsPosts;

import java.util.List;

/**
 * Created by lizhaotailang on 2017/6/15.
 */

@Dao
public interface DoubanMomentNewsDao {

    @Query("SELECT * FROM douban_moment_news")
    List<DoubanMomentNewsPosts> loadDoubanMomentNews();

    @Insert
    void saveAll(List<DoubanMomentNewsPosts> items);

    @Insert
    void saveItem(DoubanMomentNewsPosts item);

    @Query("SELECT * FROM douban_moment_news WHERE id = :id")
    DoubanMomentNewsPosts loadDoubanMomentItem(int id);

    @Update
    void updateDoubanMomentNews(DoubanMomentNewsPosts item);

}
