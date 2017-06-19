package com.marktony.zhihudaily.database.converter;

import android.arch.persistence.room.TypeConverter;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.marktony.zhihudaily.data.DoubanMomentNewsThumbs;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by lizhaotailang on 2017/6/17.
 */

public class DoubanContentTypeConverters {

    @TypeConverter
    public String thumbListToString(List<DoubanMomentNewsThumbs> thumbs) {
        return new Gson().toJson(thumbs);
    }

    @TypeConverter
    public List<DoubanMomentNewsThumbs> stringToThumbList(String string) {
        Type listType = new TypeToken<ArrayList<DoubanMomentNewsThumbs>>(){}.getType();
        return new Gson().fromJson(string, listType);
    }

}
