package com.marktony.zhihudaily.database.dao;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import com.marktony.zhihudaily.data.GuokrHandpickNewsResult;

import java.util.List;

/**
 * Created by lizhaotailang on 2017/6/15.
 */

@Dao
public interface GuokrHandpickNewsDao {

    @Query("SELECT * FROM guokr_handpick_news")
    List<GuokrHandpickNewsResult> queryAllItems();

    @Query("SELECT * FROM guokr_handpick_news WHERE id = :id")
    GuokrHandpickNewsResult queryItemById(int id);

    @Query("SELECT * FROM guokr_handpick_news WHERE favorite = 1")
    List<GuokrHandpickNewsResult> queryAllFavorites();

    @Query("SELECT * FROM guokr_handpick_news WHERE timestamp < :timestamp")
    List<GuokrHandpickNewsResult> queryAllTimeoutItems(long timestamp);

    @Insert
    void insertAll(List<GuokrHandpickNewsResult> items);

    @Update
    void update(GuokrHandpickNewsResult item);

    @Delete
    void delete(GuokrHandpickNewsResult item);

}
