package com.marktony.zhihudaily.database.dao;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import com.marktony.zhihudaily.data.DoubanMomentContent;

/**
 * Created by lizhaotailang on 2017/6/15.
 */

@Dao
public interface DoubanMomentContentDao {

    @Query("SELECT * FROM douban_moment_content WHERE id = :id")
    DoubanMomentContent loadDoubanMomentContent(int id);

    @Insert
    void saveContent(DoubanMomentContent content);

    @Update
    void updateContent(DoubanMomentContent content);

}
