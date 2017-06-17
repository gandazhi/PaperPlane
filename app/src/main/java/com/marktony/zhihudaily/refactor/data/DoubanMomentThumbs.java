package com.marktony.zhihudaily.refactor.data;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Embedded;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by lizhaotailang on 2017/6/17.
 */
public class DoubanMomentThumbs {

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
    private DoubanMomentSmall small;

    @ColumnInfo(name = "thumb_id")
    @Expose
    @SerializedName("id")
    private int id;

    public DoubanMomentThumbs() {
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

    public DoubanMomentSmall getSmall() {
        return small;
    }

    public void setSmall(DoubanMomentSmall small) {
        this.small = small;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

}
