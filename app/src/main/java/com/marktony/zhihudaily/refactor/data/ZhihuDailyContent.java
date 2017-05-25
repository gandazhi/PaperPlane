package com.marktony.zhihudaily.refactor.data;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by lizhaotailang on 2017/5/20.
 */

public class ZhihuDailyContent {

    @Expose
    @SerializedName("body")
    private String body;

    @Expose
    @SerializedName("image_source")
    private String imageSource;

    @Expose
    @SerializedName("title")
    private String title;

    @Expose
    @SerializedName("image")
    private String image;

    @Expose
    @SerializedName("share_url")
    private String shareUrl;

    @Expose
    @SerializedName("is")
    private List<String> js;

    @Expose
    @SerializedName("ga_prefix")
    private String gaPrefix;

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
    @SerializedName("css")
    private List<String> css;

    @Expose
    private boolean favorited;

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public String getImageSource() {
        return imageSource;
    }

    public void setImageSource(String imageSource) {
        this.imageSource = imageSource;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getShareUrl() {
        return shareUrl;
    }

    public void setShareUrl(String shareUrl) {
        this.shareUrl = shareUrl;
    }

    public List<String> getJs() {
        return js;
    }

    public void setJs(List<String> js) {
        this.js = js;
    }

    public String getGaPrefix() {
        return gaPrefix;
    }

    public void setGaPrefix(String gaPrefix) {
        this.gaPrefix = gaPrefix;
    }

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

    public List<String> getCss() {
        return css;
    }

    public void setCss(List<String> css) {
        this.css = css;
    }

    public boolean isFavorited() {
        return favorited;
    }

    public void setFavorited(boolean favorited) {
        this.favorited = favorited;
    }
}
