package com.marktony.zhihudaily.refactor.database.dao;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import com.marktony.zhihudaily.refactor.data.GuokrHandpickNews;

import java.util.List;

/**
 * Created by lizhaotailang on 2017/6/15.
 */

@Dao
public interface GuokrHandpickNewsDao {

    @Query("SELECT * FROM guokr_handpick")
    List<GuokrHandpickNews.Result> loadGuokrHandpickNews();

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertAll(List<GuokrHandpickNews.Result> items);

    @Query("SELECT * FROM guokr_handpck where id = :id")
    GuokrHandpickNews.Result loadGuokrHandpickItem(int id);

    @Update
    void updateGuokrHandpickNews(GuokrHandpickNews.Result item);

}
