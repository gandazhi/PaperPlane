package com.marktony.zhihudaily.refactor.data;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by lizhaotailang on 2017/5/20.
 * Immutable model class for a zhihu daily news post.
 */

public class ZhihuDailyNews {

    @Expose
    @SerializedName("date")
    private String date;

    @Expose
    @SerializedName("stories")
    private List<Question> stories;

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public List<Question> getStories() {
        return stories;
    }

    public void setStories(List<Question> stories) {
        this.stories = stories;
    }

    // The entity that will be stored by room
    @Entity(tableName = "zhihu_questions")
    public class Question {

        @ColumnInfo(name = "images")
        @Expose
        @SerializedName("images")
        private List<String> images;

        @ColumnInfo(name = "type")
        @Expose
        @SerializedName("type")
        private int type;

        @PrimaryKey
        @ColumnInfo(name = "id")
        @Expose
        @SerializedName("id")
        private int id;

        @ColumnInfo(name = "ga_prefix")
        @Expose
        @SerializedName("ga_prefix")
        private String gaPrefix;

        @ColumnInfo(name = "title")
        @Expose
        @SerializedName("title")
        private String title;

        @ColumnInfo(name = "favorite")
        @Expose
        private boolean favorite;

        @ColumnInfo(name = "outdated")
        @Expose
        private boolean outdated;

        public List<String> getImages() {
            return images;
        }

        public void setImages(List<String> images) {
            this.images = images;
        }

        public int getType() {
            return type;
        }

        public void setType(int type) {
            this.type = type;
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getGaPrefix() {
            return gaPrefix;
        }

        public void setGaPrefix(String gaPrefix) {
            this.gaPrefix = gaPrefix;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public boolean isFavorite() {
            return favorite;
        }

        public void setFavorite(boolean favorite) {
            this.favorite = favorite;
        }

        public boolean isOutdated() {
            return outdated;
        }

        public void setOutdated(boolean outdated) {
            this.outdated = outdated;
        }
    }

}
