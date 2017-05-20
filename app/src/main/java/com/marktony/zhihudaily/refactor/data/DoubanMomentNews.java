package com.marktony.zhihudaily.refactor.data;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by lizhaotailang on 2017/5/20.
 */

public class DoubanMomentNews {

    @SerializedName("count")
    private int count;

    @SerializedName("posts")
    private List<Posts> posts;

    @SerializedName("offset")
    private int offset;

    @SerializedName("date")
    private String date;

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

        @SerializedName("display_style")
        private int displayStyle;

        @SerializedName("is_editor_choice")
        private boolean isEditorChoice;

        @SerializedName("published_time")
        private String publishedTime;

        @SerializedName("url")
        private String url;

        @SerializedName("short_url")
        private String shortUrl;

        @SerializedName("is_liked")
        private boolean isLiked;

        @SerializedName("author")
        private Author author;

        @SerializedName("column")
        private String column;

        @SerializedName("app_css")
        private int appCss;

        @SerializedName("abstract")
        private String abs;

        @SerializedName("date")
        private String date;

        @SerializedName("like_count")
        private int likeCount;

        @SerializedName("comments_count")
        private int commentsCount;

        @SerializedName("thumbs")
        private List<Thumbs> thumbs;

        @SerializedName("created_time")
        private String createdTime;

        @SerializedName("title")
        private String title;

        @SerializedName("share_pic_url")
        private String sharePicUrl;

        @SerializedName("type")
        private String type;

        @SerializedName("id")
        private int id;

        public class Thumbs {

            @SerializedName("medium")
            private Medium medium;

            @SerializedName("description")
            private String description;

            @SerializedName("large")
            private Large large;

            @SerializedName("tag_name")
            private String tagName;

            @SerializedName("small")
            private Small small;

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

            @SerializedName("url")
            private String url;

            @SerializedName("width")
            private int width;

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

            @SerializedName("url")
            private String url;

            @SerializedName("width")
            private int width;

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

            @SerializedName("url")
            private String url;

            @SerializedName("width")
            private int width;

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
    }

    private class Author {

        @SerializedName("is_followed")
        private boolean isFollowed;

        @SerializedName("uid")
        private String uid;

        @SerializedName("url")
        private String url;

        @SerializedName("avatar")
        private String avatar;

        @SerializedName("name")
        private String name;

        @SerializedName("is_special_user")
        private boolean isSpecialUser;

        @SerializedName("n_posts")
        private int nPosts;

        @SerializedName("alt")
        private String alt;

        @SerializedName("large_avatar")
        private String largeAvatar;

        @SerializedName("id")
        private String id;

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
