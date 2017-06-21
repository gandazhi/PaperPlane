package com.marktony.zhihudaily.database.dao;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import com.marktony.zhihudaily.data.GuokrHandpickContentResult;
import com.marktony.zhihudaily.data.GuokrHandpickNewsResult;

import java.util.List;

/**
 * Created by lizhaotailang on 2017/6/15.
 */

@Dao
public interface GuokrHandpickContentDao {

    @Query("SELECT * FROM guokr_handpick_content WHERE id = :id")
    GuokrHandpickContentResult queryContentById(int id);

    @Query("SELECT * FROM guokr_handpick_content WHERE timestamp < :timestamp")
    List<GuokrHandpickContentResult> queryAllTimeoutContents(long timestamp);

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    void insert(GuokrHandpickContentResult content);

    @Update
    void update(GuokrHandpickContentResult content);

    @Delete
    void delete(GuokrHandpickContentResult item);

}
