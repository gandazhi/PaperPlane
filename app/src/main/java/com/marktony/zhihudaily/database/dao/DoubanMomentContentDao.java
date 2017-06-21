package com.marktony.zhihudaily.database.dao;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import com.marktony.zhihudaily.data.DoubanMomentContent;

import java.util.List;

/**
 * Created by lizhaotailang on 2017/6/15.
 */

@Dao
public interface DoubanMomentContentDao {

    @Query("SELECT * FROM douban_moment_content WHERE id = :id")
    DoubanMomentContent queryContentById(int id);

    @Query("SELECT * FROM douban_moment_content WHERE timestamp < :timestamp")
    List<DoubanMomentContent> queryAllTimeoutContents(long timestamp);

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    void insert(DoubanMomentContent content);

    @Update
    void update(DoubanMomentContent content);

    @Delete
    void delete(DoubanMomentContent content);

}
