package com.marktony.zhihudaily.refactor.data;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by lizhaotailang on 2017/5/20.
 */

public class DoubanMomentContent {

    @SerializedName("display_style")
    private int displayStyle;

    @SerializedName("short_url")
    private String shortUrl;

    @SerializedName("abstract")
    private String abs;

    @SerializedName("app_css")
    private int appCss;

    @SerializedName("like_count")
    private int likeCount;

    @SerializedName("thumbs")
    private List<DoubanMomentNews.Posts.Thumbs> thumbs;

    @SerializedName("created_time")
    private String createdTime;

    @SerializedName("id")
    private int id;

    @SerializedName("is_editor_choice")
    private boolean isEditorChoice;

    @SerializedName("original_url")
    private String originalUrl;

    @SerializedName("content")
    private String content;

    @SerializedName("share_pic_url")
    private String sharePicUrl;

    @SerializedName("type")
    private String type;

    @SerializedName("is_liked")
    private boolean isLiked;

    @SerializedName("photos")
    private List<DoubanMomentNews.Posts.Thumbs> photos;

    @SerializedName("published_time")
    private String publishedTime;

    @SerializedName("url")
    private String url;

    @SerializedName("column")
    private String column;

    @SerializedName("comments_count")
    private int commentsCount;

    @SerializedName("title")
    private String title;

    public int getDisplayStyle() {
        return displayStyle;
    }

    public void setDisplayStyle(int displayStyle) {
        this.displayStyle = displayStyle;
    }

    public String getShortUrl() {
        return shortUrl;
    }

    public void setShortUrl(String shortUrl) {
        this.shortUrl = shortUrl;
    }

    public String getAbs() {
        return abs;
    }

    public void setAbs(String abs) {
        this.abs = abs;
    }

    public int getAppCss() {
        return appCss;
    }

    public void setAppCss(int appCss) {
        this.appCss = appCss;
    }

    public int getLikeCount() {
        return likeCount;
    }

    public void setLikeCount(int likeCount) {
        this.likeCount = likeCount;
    }

    public List<DoubanMomentNews.Posts.Thumbs> getThumbs() {
        return thumbs;
    }

    public void setThumbs(List<DoubanMomentNews.Posts.Thumbs> thumbs) {
        this.thumbs = thumbs;
    }

    public String getCreatedTime() {
        return createdTime;
    }

    public void setCreatedTime(String createdTime) {
        this.createdTime = createdTime;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public boolean isEditorChoice() {
        return isEditorChoice;
    }

    public void setEditorChoice(boolean editorChoice) {
        isEditorChoice = editorChoice;
    }

    public String getOriginalUrl() {
        return originalUrl;
    }

    public void setOriginalUrl(String originalUrl) {
        this.originalUrl = originalUrl;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getSharePicUrl() {
        return sharePicUrl;
    }

    public void setSharePicUrl(String sharePicUrl) {
        this.sharePicUrl = sharePicUrl;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public boolean isLiked() {
        return isLiked;
    }

    public void setLiked(boolean liked) {
        isLiked = liked;
    }

    public List<DoubanMomentNews.Posts.Thumbs> getPhotos() {
        return photos;
    }

    public void setPhotos(List<DoubanMomentNews.Posts.Thumbs> photos) {
        this.photos = photos;
    }

    public String getPublishedTime() {
        return publishedTime;
    }

    public void setPublishedTime(String publishedTime) {
        this.publishedTime = publishedTime;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getColumn() {
        return column;
    }

    public void setColumn(String column) {
        this.column = column;
    }

    public int getCommentsCount() {
        return commentsCount;
    }

    public void setCommentsCount(int commentsCount) {
        this.commentsCount = commentsCount;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
