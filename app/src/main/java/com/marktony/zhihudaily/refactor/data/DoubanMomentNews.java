package com.marktony.zhihudaily.refactor.data;

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

    public class Posts {

        @Expose
        @SerializedName("display_style")
        private int displayStyle;

        @Expose
        @SerializedName("is_editor_choice")
        private boolean isEditorChoice;

        @Expose
        @SerializedName("published_time")
        private String publishedTime;

        @Expose
        @SerializedName("url")
        private String url;

        @Expose
        @SerializedName("short_url")
        private String shortUrl;

        @Expose
        @SerializedName("is_liked")
        private boolean isLiked;

        @Expose
        @SerializedName("author")
        private Author author;

        @Expose
        @SerializedName("column")
        private String column;

        @Expose
        @SerializedName("app_css")
        private int appCss;

        @Expose
        @SerializedName("abstract")
        private String abs;

        @Expose
        @SerializedName("date")
        private String date;

        @Expose
        @SerializedName("like_count")
        private int likeCount;

        @Expose
        @SerializedName("comments_count")
        private int commentsCount;

        @Expose
        @SerializedName("thumbs")
        private List<Thumbs> thumbs;

        @Expose
        @SerializedName("created_time")
        private String createdTime;

        @Expose
        @SerializedName("title")
        private String title;

        @Expose
        @SerializedName("share_pic_url")
        private String sharePicUrl;

        @Expose
        @SerializedName("type")
        private String type;

        @Expose
        @SerializedName("id")
        private int id;

        @Expose
        private boolean favorited;

        @Expose
        private boolean outdated;

        public class Thumbs {

            @Expose
            @SerializedName("medium")
            private Medium medium;

            @Expose
            @SerializedName("description")
            private String description;

            @Expose
            @SerializedName("large")
            private Large large;

            @Expose
            @SerializedName("tag_name")
            private String tagName;

            @Expose
            @SerializedName("small")
            private Small small;

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

            @Expose
            @SerializedName("url")
            private String url;

            @Expose
            @SerializedName("width")
            private int width;

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

            @Expose
            @SerializedName("url")
            private String url;

            @Expose
            @SerializedName("width")
            private int width;

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

            @Expose
            @SerializedName("url")
            private String url;

            @Expose
            @SerializedName("width")
            private int width;

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

    private class Author {

        @Expose
        @SerializedName("is_followed")
        private boolean isFollowed;

        @Expose
        @SerializedName("uid")
        private String uid;

        @Expose
        @SerializedName("url")
        private String url;

        @Expose
        @SerializedName("avatar")
        private String avatar;

        @Expose
        @SerializedName("name")
        private String name;

        @Expose
        @SerializedName("is_special_user")
        private boolean isSpecialUser;

        @Expose
        @SerializedName("n_posts")
        private int nPosts;

        @Expose
        @SerializedName("alt")
        private String alt;

        @Expose
        @SerializedName("large_avatar")
        private String largeAvatar;

        @Expose
        @SerializedName("id")
        private String id;

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
