package com.marktony.zhihudaily.refactor.data;

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

    public class Question {

        @Expose
        @SerializedName("images")
        private List<String> images;

        @Expose
        @SerializedName("type")
        private int type;

        @Expose
        @SerializedName("id")
        private int id;

        @Expose
        @SerializedName("ga_prefix")
        private String gaPrefix;

        @Expose
        @SerializedName("title")
        private String title;

        @Expose
        private boolean favorited;

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

        public boolean isFavorited() {
            return favorited;
        }

        public void setFavorited(boolean favorited) {
            this.favorited = favorited;
        }

        public boolean isOutdated() {
            return outdated;
        }

        public void setOutdated(boolean outdated) {
            this.outdated = outdated;
        }
    }

}
