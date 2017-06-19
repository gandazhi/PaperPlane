package com.marktony.zhihudaily.database.dao;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import com.marktony.zhihudaily.data.GuokrHandpickContentResult;

import java.util.List;

/**
 * Created by lizhaotailang on 2017/6/15.
 */

@Dao
public interface GuokrHandpickContentDao {

    @Query("SELECT * FROM guokr_handpick_content")
    List<GuokrHandpickContentResult> loadAllGuokrContents();

    @Query("SELECT * FROM guokr_handpick_content WHERE id = :id")
    GuokrHandpickContentResult loadGuokrHandpickNewsItem(int id);

    @Insert
    void saveContent(GuokrHandpickContentResult content);

    @Update
    void updateContent(GuokrHandpickContentResult content);

}
