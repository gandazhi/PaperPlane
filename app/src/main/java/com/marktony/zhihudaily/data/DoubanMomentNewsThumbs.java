package com.marktony.zhihudaily.data;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Embedded;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by lizhaotailang on 2017/6/17.
 */
public class DoubanMomentNewsThumbs {

    @Embedded
    @Expose
    @SerializedName("medium")
    private DoubanMomentMedium medium;

    @ColumnInfo(name = "thumb_description")
    @Expose
    @SerializedName("description")
    private String description;

    @Embedded
    @Expose
    @SerializedName("large")
    private DoubanMomentLarge large;

    @Expose
    @SerializedName("tag_name")
    private String tagName;

    @Embedded
    @Expose
    @SerializedName("small")
    private DoubanMomentNewsSmall small;

    @ColumnInfo(name = "thumb_id")
    @Expose
    @SerializedName("id")
    private int id;

    public DoubanMomentNewsThumbs() {
    }

    public DoubanMomentMedium getMedium() {
        return medium;
    }

    public void setMedium(DoubanMomentMedium medium) {
        this.medium = medium;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public DoubanMomentLarge getLarge() {
        return large;
    }

    public void setLarge(DoubanMomentLarge large) {
        this.large = large;
    }

    public String getTagName() {
        return tagName;
    }

    public void setTagName(String tag_name) {
        this.tagName = tag_name;
    }

    public DoubanMomentNewsSmall getSmall() {
        return small;
    }

    public void setSmall(DoubanMomentNewsSmall small) {
        this.small = small;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

}
