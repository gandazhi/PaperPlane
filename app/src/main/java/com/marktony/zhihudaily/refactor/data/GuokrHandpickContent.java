package com.marktony.zhihudaily.refactor.data;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by lizhaotailang on 2017/5/26.
 * Be noticed that the field 'authors' is ignored cause it is a variable type.
 */

public class GuokrHandpickContent {

    @Expose
    @SerializedName("now")
    private String now;

    @Expose
    @SerializedName("ok")
    private boolean ok;

    @Expose
    @SerializedName("result")
    private Result result;

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

    public Result getResult() {
        return result;
    }

    public void setResult(Result result) {
        this.result = result;
    }

    public class Result {

        @Expose
        @SerializedName("image")
        private String image;

        @Expose
        @SerializedName("is_replyable")
        private boolean isReplyable;

        @Expose
        @SerializedName("channels")
        private List<Channel> channels;

        @Expose
        @SerializedName("channel_keys")
        private List<String> channelKeys;

        @Expose
        @SerializedName("preface")
        private String preface;

        @Expose
        @SerializedName("id")
        private int id;

        @Expose
        @SerializedName("subject")
        private Channel subject;

        @Expose
        @SerializedName("copyright")
        private String copyright;

        @Expose
        @SerializedName("author")
        private Author author;

        @Expose
        @SerializedName("image_description")
        private String imageDescription;

        @Expose
        @SerializedName("content")
        private String content;

        @Expose
        @SerializedName("is_show_summary")
        private boolean isShowSummary;

        @Expose
        @SerializedName("minisite_key")
        private String minisiteKey;

        @Expose
        @SerializedName("image_info")
        private ImageInfo imageInfo;

        @Expose
        @SerializedName("subject_key")
        private String subjectKey;

        @Expose
        @SerializedName("minisite")
        private Minisite minisite;

        @Expose
        @SerializedName("tags")
        private List<String> tags;

        @Expose
        @SerializedName("date_published")
        private String datePublished;

        @Expose
        @SerializedName("replies_count")
        private int repliesCount;

        @Expose
        @SerializedName("is_author_external")
        private boolean isAuthorExternal;

        @Expose
        @SerializedName("recommends_count")
        private int recommendsCount;

        @Expose
        @SerializedName("title_hide")
        private String titleHide;

        @Expose
        @SerializedName("date_modified")
        private String dateModified;

        @Expose
        @SerializedName("url")
        private String url;

        @Expose
        @SerializedName("title")
        private String title;

        @Expose
        @SerializedName("small_image")
        private String smallImage;

        @Expose
        @SerializedName("summary")
        private String summary;

        @Expose
        @SerializedName("ukey_author")
        private String ukeyAuthor;

        @Expose
        @SerializedName("date_created")
        private String dateCreated;

        @Expose
        @SerializedName("resource_url")
        private String resourceUrl;

        @Expose
        private boolean favorite;

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

        public String getContent() {
            return content;
        }

        public void setContent(String content) {
            this.content = content;
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

        public boolean isFavorite() {
            return favorite;
        }

        public void setFavorite(boolean favorite) {
            this.favorite = favorite;
        }

        public class Channel {

            @Expose
            @SerializedName("url")
            private String url;

            @Expose
            @SerializedName("date_created")
            private String dateCreated;

            @Expose
            @SerializedName("name")
            private String name;

            @Expose
            @SerializedName("key")
            private String key;

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

            @Expose
            @SerializedName("ukey")
            private String ukey;

            @Expose
            @SerializedName("is_title_authorized")
            private String isTitleAuthorized;

            @Expose
            @SerializedName("nickname")
            private String nickname;

            @Expose
            @SerializedName("master_category")
            private String masterCategory;

            @Expose
            @SerializedName("amended_reliability")
            private String amendedReliability;

            @Expose
            @SerializedName("is_exists")
            private boolean isExists;

            @Expose
            @SerializedName("title")
            private String title;

            @Expose
            @SerializedName("url")
            private String url;

            @Expose
            @SerializedName("gender")
            private String gender;

            @Expose
            @SerializedName("followers_count")
            private int followersCount;

            @Expose
            @SerializedName("avatar")
            private Avatar avatar;

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

                @Expose
                @SerializedName("large")
                private String large;

                @Expose
                @SerializedName("small")
                private String small;

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

        public class Minisite {

            @Expose
            @SerializedName("name")
            private String name;

            @Expose
            @SerializedName("url")
            private String url;

            @Expose
            @SerializedName("introduction")
            private String introduction;

            @Expose
            @SerializedName("key")
            private String key;

            @Expose
            @SerializedName("date_created")
            private String dateCreated;

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
