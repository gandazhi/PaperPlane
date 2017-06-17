package com.marktony.zhihudaily.refactor.database.converter;

import android.arch.persistence.room.TypeConverter;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.marktony.zhihudaily.refactor.data.GuokrHandpickContentChannel;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by lizhaotailang on 2017/6/17.
 */

public class GuokrContentTypeConverters {

    @TypeConverter
    public static String stringListToString(List<String> strings) {
        return new Gson().toJson(strings);
    }

    @TypeConverter
    public static List<String> stringToStringList(String string) {
        Type listType = new TypeToken<ArrayList<String>>(){}.getType();
        return new Gson().fromJson(string, listType);
    }

    @TypeConverter
    public static String channelListToString(List<GuokrHandpickContentChannel> channels) {
        return new Gson().toJson(channels);
    }

    @TypeConverter
    public static List<GuokrHandpickContentChannel> stringToChannelList(String string) {
        Type listType = new TypeToken<ArrayList<GuokrHandpickContentChannel>>(){}.getType();
        return new Gson().fromJson(string, listType);
    }

}
