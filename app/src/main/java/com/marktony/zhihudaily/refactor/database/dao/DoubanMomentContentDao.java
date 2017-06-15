package com.marktony.zhihudaily.refactor.database.dao;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import com.marktony.zhihudaily.refactor.data.DoubanMomentContent;

/**
 * Created by lizhaotailang on 2017/6/15.
 */

@Dao
public interface DoubanMomentContentDao {

    @Query("SELECT * FROM douban_moment_content where id = :id")
    DoubanMomentContent loadDoubanMomentContent(int id);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertDoubanMomentContent(DoubanMomentContent content);

    @Update
    void updateDoubanMomentContent(DoubanMomentContent content);

}
