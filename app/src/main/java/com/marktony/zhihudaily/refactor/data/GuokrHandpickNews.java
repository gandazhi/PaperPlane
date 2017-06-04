package com.marktony.zhihudaily.refactor.data;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Embedded;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by lizhaotailang on 2017/5/24.
 */

public class GuokrHandpickNews {

    @Expose
    @SerializedName("now")
    private String now;

    @Expose
    @SerializedName("ok")
    private boolean ok;

    @Expose
    @SerializedName("limit")
    private int limit;

    @Expose
    @SerializedName("result")
    private List<Result> result;

    @Expose
    @SerializedName("offset")
    private int offset;

    @Expose
    @SerializedName("total")
    private int total;

    public String getNow() {
        return now;
    }

    public void setNow(String now) {
        this.now = now;
    }

    public boolean isOk() {
        return ok;
    }

    public void setOk(boolean ok) {
        this.ok = ok;
    }

    public int getLimit() {
        return limit;
    }

    public void setLimit(int limit) {
        this.limit = limit;
    }

    public int getOffset() {
        return offset;
    }

    public void setOffset(int offset) {
        this.offset = offset;
    }

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public List<Result> getResult() {
        return result;
    }

    public void setResult(List<Result> result) {
        this.result = result;
    }

    @Entity
    public class Result {

        @ColumnInfo(name = "image")
        @Expose
        @SerializedName("image")
        private String image;

        @ColumnInfo(name = "is_replyable")
        @Expose
        @SerializedName("is_replyable")
        private boolean isReplyable;

        @ColumnInfo(name = "channels")
        @Expose
        @SerializedName("channels")
        private List<Channel> channels;

        @ColumnInfo(name = "channel_keys")
        @Expose
        @SerializedName("channel_keys")
        private List<String> channelKeys;

        @ColumnInfo(name = "preface")
        @Expose
        @SerializedName("preface")
        private String preface;

        @PrimaryKey
        @ColumnInfo(name = "id")
        @Expose
        @SerializedName("id")
        private int id;

        @ColumnInfo(name = "subject")
        @Embedded
        @Expose
        @SerializedName("subject")
        private Channel subject;

        @ColumnInfo(name = "copyright")
        @Expose
        @SerializedName("copyright")
        private String copyright;

        @ColumnInfo(name = "author")
        @Embedded
        @Expose
        @SerializedName("author")
        private Author author;

        @ColumnInfo(name = "image_description")
        @Expose
        @SerializedName("image_description")
        private String imageDescription;

        @ColumnInfo(name = "is_show_summary")
        @Expose
        @SerializedName("is_show_summary")
        private boolean isShowSummary;

        @ColumnInfo(name = "minisite_key")
        @Expose
        @SerializedName("minisite_key")
        private String minisiteKey;

        @ColumnInfo(name = "image_info")
        @Embedded
        @Expose
        @SerializedName("image_info")
        private ImageInfo imageInfo;

        @ColumnInfo(name = "subject_key")
        @Expose
        @SerializedName("subject_key")
        private String subjectKey;

        @ColumnInfo(name = "minisite")
        @Embedded
        @Expose
        @SerializedName("minisite")
        private Minisite minisite;

        @ColumnInfo(name = "tags")
        @Expose
        @SerializedName("tags")
        private List<String> tags;

        @ColumnInfo(name = "date_published")
        @Expose
        @SerializedName("date_published")
        private String datePublished;

        @ColumnInfo(name = "avatar")
        @Expose
        @SerializedName("avatar")
        private List<Author> authors;

        @ColumnInfo(name = "replies_count")
        @Expose
        @SerializedName("replies_count")
        private int repliesCount;

        @ColumnInfo(name = "is_author_external")
        @Expose
        @SerializedName("is_author_external")
        private boolean isAuthorExternal;

        @ColumnInfo(name = "recommends_count")
        @Expose
        @SerializedName("recommends_count")
        private int recommendsCount;

        @ColumnInfo(name = "title_hide")
        @Expose
        @SerializedName("title_hide")
        private String titleHide;

        @ColumnInfo(name = "date_modified")
        @Expose
        @SerializedName("date_modified")
        private String dateModified;

        @ColumnInfo(name = "url")
        @Expose
        @SerializedName("url")
        private String url;

        @ColumnInfo(name = "title")
        @Expose
        @SerializedName("title")
        private String title;

        @ColumnInfo(name = "small_image")
        @Expose
        @SerializedName("small_image")
        private String smallImage;

        @ColumnInfo(name = "summary")
        @Expose
        @SerializedName("summary")
        private String summary;

        @ColumnInfo(name = "ukey_author")
        @Expose
        @SerializedName("ukey_author")
        private String ukeyAuthor;

        @ColumnInfo(name = "date_created")
        @Expose
        @SerializedName("date_created")
        private String dateCreated;

        @ColumnInfo(name = "resource_url")
        @Expose
        @SerializedName("resource_url")
        private String resourceUrl;

        @ColumnInfo(name = "favorite")
        @Expose
        private boolean favorite;

        @ColumnInfo(name = "outdated")
        @Expose
        private boolean outdated;

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

        public String getImage() {
            return image;
        }

        public void setImage(String image) {
            this.image = image;
        }

        public boolean isReplyable() {
            return isReplyable;
        }

        public void setReplyable(boolean replyable) {
            isReplyable = replyable;
        }

        public List<Channel> getChannels() {
            return channels;
        }

        public void setChannels(List<Channel> channels) {
            this.channels = channels;
        }

        public List<String> getChannelKeys() {
            return channelKeys;
        }

        public void setChannelKeys(List<String> channelKeys) {
            this.channelKeys = channelKeys;
        }

        public String getPreface() {
            return preface;
        }

        public void setPreface(String preface) {
            this.preface = preface;
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public Channel getSubject() {
            return subject;
        }

        public void setSubject(Channel subject) {
            this.subject = subject;
        }

        public String getCopyright() {
            return copyright;
        }

        public void setCopyright(String copyright) {
            this.copyright = copyright;
        }

        public Author getAuthor() {
            return author;
        }

        public void setAuthor(Author author) {
            this.author = author;
        }

        public String getImageDescription() {
            return imageDescription;
        }

        public void setImageDescription(String imageDescription) {
            this.imageDescription = imageDescription;
        }

        public boolean isShowSummary() {
            return isShowSummary;
        }

        public void setShowSummary(boolean showSummary) {
            isShowSummary = showSummary;
        }

        public String getMinisiteKey() {
            return minisiteKey;
        }

        public void setMinisiteKey(String minisiteKey) {
            this.minisiteKey = minisiteKey;
        }

        public ImageInfo getImageInfo() {
            return imageInfo;
        }

        public void setImageInfo(ImageInfo imageInfo) {
            this.imageInfo = imageInfo;
        }

        public String getSubjectKey() {
            return subjectKey;
        }

        public void setSubjectKey(String subjectKey) {
            this.subjectKey = subjectKey;
        }

        public Minisite getMinisite() {
            return minisite;
        }

        public void setMinisite(Minisite minisite) {
            this.minisite = minisite;
        }

        public List<String> getTags() {
            return tags;
        }

        public void setTags(List<String> tags) {
            this.tags = tags;
        }

        public String getDatePublished() {
            return datePublished;
        }

        public void setDatePublished(String datePublished) {
            this.datePublished = datePublished;
        }

        public List<Author> getAuthors() {
            return authors;
        }

        public void setAuthors(List<Author> authors) {
            this.authors = authors;
        }

        public int getRepliesCount() {
            return repliesCount;
        }

        public void setRepliesCount(int repliesCount) {
            this.repliesCount = repliesCount;
        }

        public boolean isAuthorExternal() {
            return isAuthorExternal;
        }

        public void setAuthorExternal(boolean authorExternal) {
            isAuthorExternal = authorExternal;
        }

        public int getRecommendsCount() {
            return recommendsCount;
        }

        public void setRecommendsCount(int recommendsCount) {
            this.recommendsCount = recommendsCount;
        }

        public String getTitleHide() {
            return titleHide;
        }

        public void setTitleHide(String titleHide) {
            this.titleHide = titleHide;
        }

        public String getDateModified() {
            return dateModified;
        }

        public void setDateModified(String dateModified) {
            this.dateModified = dateModified;
        }

        public String getUrl() {
            return url;
        }

        public void setUrl(String url) {
            this.url = url;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public String getSmallImage() {
            return smallImage;
        }

        public void setSmallImage(String smallImage) {
            this.smallImage = smallImage;
        }

        public String getSummary() {
            return summary;
        }

        public void setSummary(String summary) {
            this.summary = summary;
        }

        public String getUkeyAuthor() {
            return ukeyAuthor;
        }

        public void setUkeyAuthor(String ukeyAuthor) {
            this.ukeyAuthor = ukeyAuthor;
        }

        public String getDateCreated() {
            return dateCreated;
        }

        public void setDateCreated(String dateCreated) {
            this.dateCreated = dateCreated;
        }

        public String getResourceUrl() {
            return resourceUrl;
        }

        public void setResourceUrl(String resourceUrl) {
            this.resourceUrl = resourceUrl;
        }

        public class Channel {

            @ColumnInfo(name = "channel_url")
            @Expose
            @SerializedName("url")
            private String url;

            @ColumnInfo(name = "channel_date_created")
            @Expose
            @SerializedName("date_created")
            private String dateCreated;

            @ColumnInfo(name = "channel_name")
            @Expose
            @SerializedName("name")
            private String name;

            @ColumnInfo(name = "channel_key")
            @Expose
            @SerializedName("key")
            private String key;

            @ColumnInfo(name = "channel_articles_count")
            @Expose
            @SerializedName("articles_count")
            private int articlesCount;

            public String getUrl() {
                return url;
            }

            public void setUrl(String url) {
                this.url = url;
            }

            public String getDateCreated() {
                return dateCreated;
            }

            public void setDateCreated(String dateCreated) {
                this.dateCreated = dateCreated;
            }

            public String getName() {
                return name;
            }

            public void setName(String name) {
                this.name = name;
            }

            public String getKey() {
                return key;
            }

            public void setKey(String key) {
                this.key = key;
            }

            public int getArticlesCount() {
                return articlesCount;
            }

            public void setArticlesCount(int articlesCount) {
                this.articlesCount = articlesCount;
            }
        }

        public class Author {

            @ColumnInfo(name = "author_ukey")
            @Expose
            @SerializedName("ukey")
            private String ukey;

            @ColumnInfo(name = "author_is_title_authorized")
            @Expose
            @SerializedName("is_title_authorized")
            private String isTitleAuthorized;

            @ColumnInfo(name = "author_nickname")
            @Expose
            @SerializedName("nickname")
            private String nickname;

            @ColumnInfo(name = "author_master_category")
            @Expose
            @SerializedName("master_category")
            private String masterCategory;

            @ColumnInfo(name = "author_amended_reliability")
            @Expose
            @SerializedName("amended_reliability")
            private String amendedReliability;

            @ColumnInfo(name = "author_is_exists")
            @Expose
            @SerializedName("is_exists")
            private boolean isExists;

            @ColumnInfo(name = "author_title")
            @Expose
            @SerializedName("title")
            private String title;

            @ColumnInfo(name = "author_url")
            @Expose
            @SerializedName("url")
            private String url;

            @ColumnInfo(name = "author_gender")
            @Expose
            @SerializedName("gender")
            private String gender;

            @ColumnInfo(name = "author_followers_count")
            @Expose
            @SerializedName("followers_count")
            private int followersCount;

            @ColumnInfo(name = "author_avatar")
            @Embedded
            @Expose
            @SerializedName("avatar")
            private Avatar avatar;

            @ColumnInfo(name = "author_resource_url")
            @Expose
            @SerializedName("resource_url")
            private String resourceUrl;

            public String getUkey() {
                return ukey;
            }

            public void setUkey(String ukey) {
                this.ukey = ukey;
            }

            public String getIsTitleAuthorized() {
                return isTitleAuthorized;
            }

            public void setIsTitleAuthorized(String isTitleAuthorized) {
                this.isTitleAuthorized = isTitleAuthorized;
            }

            public String getNickname() {
                return nickname;
            }

            public void setNickname(String nickname) {
                this.nickname = nickname;
            }

            public String getMasterCategory() {
                return masterCategory;
            }

            public void setMasterCategory(String masterCategory) {
                this.masterCategory = masterCategory;
            }

            public String getAmendedReliability() {
                return amendedReliability;
            }

            public void setAmendedReliability(String amendedReliability) {
                this.amendedReliability = amendedReliability;
            }

            public boolean isExists() {
                return isExists;
            }

            public void setExists(boolean exists) {
                isExists = exists;
            }

            public String getTitle() {
                return title;
            }

            public void setTitle(String title) {
                this.title = title;
            }

            public String getUrl() {
                return url;
            }

            public void setUrl(String url) {
                this.url = url;
            }

            public String getGender() {
                return gender;
            }

            public void setGender(String gender) {
                this.gender = gender;
            }

            public int getFollowersCount() {
                return followersCount;
            }

            public void setFollowersCount(int followersCount) {
                this.followersCount = followersCount;
            }

            public Avatar getAvatar() {
                return avatar;
            }

            public void setAvatar(Avatar avatar) {
                this.avatar = avatar;
            }

            public String getResourceUrl() {
                return resourceUrl;
            }

            public void setResourceUrl(String resourceUrl) {
                this.resourceUrl = resourceUrl;
            }

            public class Avatar {

                @ColumnInfo(name = "avatar_large")
                @Expose
                @SerializedName("large")
                private String large;

                @ColumnInfo(name = "avatar_small")
                @Expose
                @SerializedName("small")
                private String small;

                @ColumnInfo(name = "avatar_normal")
                @Expose
                @SerializedName("normal")
                private String normal;

                public String getLarge() {
                    return large;
                }

                public void setLarge(String large) {
                    this.large = large;
                }

                public String getSmall() {
                    return small;
                }

                public void setSmall(String small) {
                    this.small = small;
                }

                public String getNormal() {
                    return normal;
                }

                public void setNormal(String normal) {
                    this.normal = normal;
                }
            }

        }

        public class ImageInfo {

            @ColumnInfo(name = "image_info_url")
            @Expose
            @SerializedName("url")
            private String url;

            @ColumnInfo(name = "image_info_width")
            @Expose
            @SerializedName("width")
            private int width;

            @ColumnInfo(name = "image_info_height")
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

        public class Minisite {

            @ColumnInfo(name = "minisite_name")
            @Expose
            @SerializedName("name")
            private String name;

            @ColumnInfo(name = "minisite_url")
            @Expose
            @SerializedName("url")
            private String url;

            @ColumnInfo(name = "minisite_introduction")
            @Expose
            @SerializedName("introduction")
            private String introduction;

            @ColumnInfo(name = "minisite_key")
            @Expose
            @SerializedName("key")
            private String key;

            @ColumnInfo(name = "minisite_date_created")
            @Expose
            @SerializedName("date_created")
            private String dateCreated;

            @ColumnInfo(name = "minisite_icon")
            @Expose
            @SerializedName("icon")
            private String icon;

            public String getName() {
                return name;
            }

            public void setName(String name) {
                this.name = name;
            }

            public String getUrl() {
                return url;
            }

            public void setUrl(String url) {
                this.url = url;
            }

            public String getIntroduction() {
                return introduction;
            }

            public void setIntroduction(String introduction) {
                this.introduction = introduction;
            }

            public String getKey() {
                return key;
            }

            public void setKey(String key) {
                this.key = key;
            }

            public String getDateCreated() {
                return dateCreated;
            }

            public void setDateCreated(String dateCreated) {
                this.dateCreated = dateCreated;
            }

            public String getIcon() {
                return icon;
            }

            public void setIcon(String icon) {
                this.icon = icon;
            }
        }

    }

}
