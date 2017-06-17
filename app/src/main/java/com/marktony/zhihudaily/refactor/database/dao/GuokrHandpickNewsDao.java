package com.marktony.zhihudaily.refactor.database.dao;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import com.marktony.zhihudaily.refactor.data.GuokrHandpickNewsResult;

import java.util.List;

/**
 * Created by lizhaotailang on 2017/6/15.
 */

@Dao
public interface GuokrHandpickNewsDao {

    @Query("SELECT * FROM guokr_handpick_news")
    List<GuokrHandpickNewsResult> loadGuokrHandpickNews();

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertAll(List<GuokrHandpickNewsResult> items);

    @Query("SELECT * FROM guokr_handpick_news where id = :id")
    GuokrHandpickNewsResult loadGuokrHandpickItem(int id);

    @Update
    void updateGuokrHandpickNews(GuokrHandpickNewsResult item);

}
