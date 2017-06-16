package com.marktony.zhihudaily.refactor.data;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Embedded;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by lizhaotailang on 2017/5/20.
 */

public class DoubanMomentNews {

    @Expose
    @SerializedName("count")
    private int count;

    @Expose
    @SerializedName("posts")
    private List<Posts> posts;

    @Expose
    @SerializedName("offset")
    private int offset;

    @Expose
    @SerializedName("date")
    private String date;

    @Expose
    @SerializedName("total")
    private int total;

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public List<DoubanMomentNews.Posts> getPosts() {
        return posts;
    }

    public void setPosts(List<DoubanMomentNews.Posts> posts) {
        this.posts = posts;
    }

    public int getOffset() {
        return offset;
    }

    public void setOffset(int offset) {
        this.offset = offset;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    @Entity(tableName = "douban_moment_news")
    public class Posts {

        @ColumnInfo(name = "display_style")
        @Expose
        @SerializedName("display_style")
        private int displayStyle;

        @ColumnInfo(name = "is_editor_choice")
        @Expose
        @SerializedName("is_editor_choice")
        private boolean isEditorChoice;

        @ColumnInfo(name = "published_time")
        @Expose
        @SerializedName("published_time")
        private String publishedTime;

        @ColumnInfo(name = "url")
        @Expose
        @SerializedName("url")
        private String url;

        @ColumnInfo(name = "short_url")
        @Expose
        @SerializedName("short_url")
        private String shortUrl;

        @ColumnInfo(name = "is_liked")
        @Expose
        @SerializedName("is_liked")
        private boolean isLiked;

        @ColumnInfo(name = "author")
        @Embedded
        @Expose
        @SerializedName("author")
        private Author author;

        @ColumnInfo(name = "column")
        @Expose
        @SerializedName("column")
        private String column;

        @ColumnInfo(name = "app_css")
        @Expose
        @SerializedName("app_css")
        private int appCss;

        @ColumnInfo(name = "abstract")
        @Expose
        @SerializedName("abstract")
        private String abs;

        @ColumnInfo(name = "date")
        @Expose
        @SerializedName("date")
        private String date;

        @ColumnInfo(name = "like_count")
        @Expose
        @SerializedName("like_count")
        private int likeCount;

        @ColumnInfo(name = "comments_count")
        @Expose
        @SerializedName("comments_count")
        private int commentsCount;

        @ColumnInfo(name = "thumbs")
        @Expose
        @SerializedName("thumbs")
        private List<Thumbs> thumbs;

        @ColumnInfo(name = "created_time")
        @Expose
        @SerializedName("created_time")
        private String createdTime;

        @ColumnInfo(name = "title")
        @Expose
        @SerializedName("title")
        private String title;

        @ColumnInfo(name = "share_pic_url")
        @Expose
        @SerializedName("share_pic_url")
        private String sharePicUrl;

        @ColumnInfo(name = "type")
        @Expose
        @SerializedName("type")
        private String type;

        @ColumnInfo(name = "id")
        @PrimaryKey
        @Expose
        @SerializedName("id")
        private int id;

        @ColumnInfo(name = "favorite")
        @Expose
        private boolean favorite;

        @ColumnInfo(name = "outdated")
        @Expose
        private boolean outdated;

        public class Thumbs {

            @ColumnInfo(name = "thumb_medium")
            @Embedded
            @Expose
            @SerializedName("medium")
            private Medium medium;

            @ColumnInfo(name = "thumb_description")
            @Expose
            @SerializedName("description")
            private String description;

            @ColumnInfo(name = "thumb_large")
            @Embedded
            @Expose
            @SerializedName("large")
            private Large large;

            @ColumnInfo(name = "thumb_tag_name")
            @Expose
            @SerializedName("tag_name")
            private String tagName;

            @ColumnInfo(name = "thumb_small")
            @Embedded
            @Expose
            @SerializedName("small")
            private Small small;

            @ColumnInfo(name = "thumb_id")
            @Expose
            @SerializedName("id")
            private int id;

            public DoubanMomentNews.Posts.Medium getMedium() {
                return medium;
            }

            public void setMedium(DoubanMomentNews.Posts.Medium medium) {
                this.medium = medium;
            }

            public String getDescription() {
                return description;
            }

            public void setDescription(String description) {
                this.description = description;
            }

            public DoubanMomentNews.Posts.Large getLarge() {
                return large;
            }

            public void setLarge(DoubanMomentNews.Posts.Large large) {
                this.large = large;
            }

            public String getTagName() {
                return tagName;
            }

            public void setTagName(String tag_name) {
                this.tagName = tag_name;
            }

            public DoubanMomentNews.Posts.Small getSmall() {
                return small;
            }

            public void setSmall(DoubanMomentNews.Posts.Small small) {
                this.small = small;
            }

            public int getId() {
                return id;
            }

            public void setId(int id) {
                this.id = id;
            }

        }

        public class Small {

            @ColumnInfo(name = "small_url")
            @Expose
            @SerializedName("url")
            private String url;

            @ColumnInfo(name = "small_width")
            @Expose
            @SerializedName("width")
            private int width;

            @ColumnInfo(name = "small_height")
            @Expose
            @SerializedName("height")
            private int height;

            public String getUrl() {
                return url;
            }

            public void setUrl(String url) {
                this.url = url;
            }

            public int getWidth() {
                return width;
            }

            public void setWidth(int width) {
                this.width = width;
            }

            public int getHeight() {
                return height;
            }

            public void setHeight(int height) {
                this.height = height;
            }

        }

        public class Medium {

            @ColumnInfo(name = "medium_url")
            @Expose
            @SerializedName("url")
            private String url;

            @ColumnInfo(name = "medium_width")
            @Expose
            @SerializedName("width")
            private int width;

            @ColumnInfo(name = "medium_height")
            @Expose
            @SerializedName("height")
            private int height;

            public String getUrl() {
                return url;
            }

            public void setUrl(String url) {
                this.url = url;
            }

            public int getWidth() {
                return width;
            }

            public void setWidth(int width) {
                this.width = width;
            }

            public int getHeight() {
                return height;
            }

            public void setHeight(int height) {
                this.height = height;
            }

        }

        public class Large {

            @ColumnInfo(name = "large_url")
            @Expose
            @SerializedName("url")
            private String url;

            @ColumnInfo(name = "large_width")
            @Expose
            @SerializedName("width")
            private int width;

            @ColumnInfo(name = "large_height")
            @Expose
            @SerializedName("height")
            private int height;

            public String getUrl() {
                return url;
            }

            public void setUrl(String url) {
                this.url = url;
            }

            public int getWidth() {
                return width;
            }

            public void setWidth(int width) {
                this.width = width;
            }

            public int getHeight() {
                return height;
            }

            public void setHeight(int height) {
                this.height = height;
            }

        }

        public int getDisplayStyle() {
            return displayStyle;
        }

        public void setDisplayStyle(int displayStyle) {
            this.displayStyle = displayStyle;
        }

        public boolean is_editor_choice() {
            return isEditorChoice;
        }

        public void setEditorChoice(boolean editorChoice) {
            this.isEditorChoice = editorChoice;
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

        public String getShortUrl() {
            return shortUrl;
        }

        public void setShortUrl(String shortUrl) {
            this.shortUrl = shortUrl;
        }

        public boolean is_liked() {
            return isLiked;
        }

        public void setLiked(boolean liked) {
            this.isLiked = liked;
        }

        public DoubanMomentNews.Author getAuthor() {
            return author;
        }

        public void setAuthor(DoubanMomentNews.Author author) {
            this.author = author;
        }

        public String getColumn() {
            return column;
        }

        public void setColumn(String column) {
            this.column = column;
        }

        public int getAppCss() {
            return appCss;
        }

        public void setAppCss(int appCss) {
            this.appCss = appCss;
        }

        public String getAbs() {
            return abs;
        }

        public void setAbs(String abs) {
            this.abs = abs;
        }

        public String getDate() {
            return date;
        }

        public void setDate(String date) {
            this.date = date;
        }

        public int getLikeCount() {
            return likeCount;
        }

        public void setLikeCount(int likeCount) {
            this.likeCount = likeCount;
        }

        public int getCommentsCount() {
            return commentsCount;
        }

        public void setCommentsCount(int commentsCount) {
            this.commentsCount = commentsCount;
        }

        public List<Thumbs> getThumbs() {
            return thumbs;
        }

        public void setThumbs(List<Thumbs> thumbs) {
            this.thumbs = thumbs;
        }

        public String getCreatedTime() {
            return createdTime;
        }

        public void setCreatedTime(String createdTime) {
            this.createdTime = createdTime;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
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

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public boolean isEditorChoice() {
            return isEditorChoice;
        }

        public boolean isLiked() {
            return isLiked;
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

    private class Author {

        @ColumnInfo(name = "is_followed")
        @Expose
        @SerializedName("is_followed")
        private boolean isFollowed;

        @ColumnInfo(name = "uid")
        @Expose
        @SerializedName("uid")
        private String uid;

        @ColumnInfo(name = "author_url")
        @Expose
        @SerializedName("url")
        private String url;

        @ColumnInfo(name = "author_avatar")
        @Expose
        @SerializedName("avatar")
        private String avatar;

        @ColumnInfo(name = "author_name")
        @Expose
        @SerializedName("name")
        private String name;

        @ColumnInfo(name = "author_is_special_user")
        @Expose
        @SerializedName("is_special_user")
        private boolean isSpecialUser;

        @ColumnInfo(name = "author_n_posts")
        @Expose
        @SerializedName("n_posts")
        private int nPosts;

        @ColumnInfo(name = "author_alt")
        @Expose
        @SerializedName("alt")
        private String alt;

        @ColumnInfo(name = "author_large_avatar")
        @Expose
        @SerializedName("large_avatar")
        private String largeAvatar;

        @ColumnInfo(name = "author_id")
        @Expose
        @SerializedName("id")
        private String id;

        @ColumnInfo(name = "author_is_auth_author")
        @Expose
        @SerializedName("is_auth_author")
        private boolean isAuthAuthor;

        public boolean isFollowed() {
            return isFollowed;
        }

        public void setFollowed(boolean followed) {
            isFollowed = followed;
        }

        public String getUid() {
            return uid;
        }

        public void setUid(String uid) {
            this.uid = uid;
        }

        public String getUrl() {
            return url;
        }

        public void setUrl(String url) {
            this.url = url;
        }

        public String getAvatar() {
            return avatar;
        }

        public void setAvatar(String avatar) {
            this.avatar = avatar;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public boolean isSpecialUser() {
            return isSpecialUser;
        }

        public void setSpecialUser(boolean specialUser) {
            isSpecialUser = specialUser;
        }

        public int getnPosts() {
            return nPosts;
        }

        public void setnPosts(int nPosts) {
            this.nPosts = nPosts;
        }

        public String getAlt() {
            return alt;
        }

        public void setAlt(String alt) {
            this.alt = alt;
        }

        public String getLargeAvatar() {
            return largeAvatar;
        }

        public void setLargeAvatar(String largeAvatar) {
            this.largeAvatar = largeAvatar;
        }

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public boolean isAuthAuthor() {
            return isAuthAuthor;
        }

        public void setAuthAuthor(boolean authAuthor) {
            isAuthAuthor = authAuthor;
        }
    }

}
