package com.marktony.zhihudaily.refactor.data;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by lizhaotailang on 2017/5/20.
 * Immutable model class for a zhihu daily news post.
 */

public class ZhihuDailyNews {

    @SerializedName("date")
    private String date;

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

    public class Question {

        @SerializedName("images")
        private List<String> images;

        @SerializedName("type")
        private int type;

        @SerializedName("id")
        private int id;

        @SerializedName("ga_prefix")
        private String gaPrefix;

        @SerializedName("title")
        private String title;

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

    }

}
