package com.marktony.zhihudaily.refactor.data;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by lizhaotailang on 2017/5/20.
 */

public class GuokrHandpickNews {

    @SerializedName("now")
    private String now;

    @SerializedName("ok")
    private boolean ok;

    @SerializedName("result")
    private List<Result> result;

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

    public List<Result> getResult() {
        return result;
    }

    public void setResult(List<Result> result) {
        this.result = result;
    }

    public class Result {

        @SerializedName("link_v2_sync_img")
        private String linkV2SyncImg;

        @SerializedName("source_name")
        private String sourceName;

        @SerializedName("video_url")
        private String videoUrl;

        @SerializedName("current_user_has_collected")
        private boolean currentUserHasCollected;

        @SerializedName("likings_count")
        private int likingsCount;

        @SerializedName("images")
        private String[] images;

        @SerializedName("video_duration")
        private String videoDuration;

        @SerializedName("id")
        private int id;

        @SerializedName("category")
        private String category;

        @SerializedName("style")
        private String style;

        @SerializedName("title")
        private String title;

        @SerializedName("source_data")
        private SourceData sourceData;

        @SerializedName("headline_img_tb")
        private String headlineImgTb;

        @SerializedName("link_v2")
        private String linkV2;

        @SerializedName("date_picked")
        private double datePicked;

        @SerializedName("is_top")
        private boolean isTop;

        @SerializedName("link")
        private String link;

        @SerializedName("headline_img")
        private String headlineImg;

        @SerializedName("replies_count")
        private int repliesCount;

        @SerializedName("current_user_has_liked")
        private boolean currentUserHasLiked;

        @SerializedName("page_source")
        private String pageSource;

        @SerializedName("author")
        private String author;

        @SerializedName("summary")
        private String summary;

        @SerializedName("source")
        private String source;

        @SerializedName("reply_root_id")
        private int replyRootId;

        @SerializedName("date_created")
        private double dateCreated;

        public String getLinkV2SyncImg() {
            return linkV2SyncImg;
        }

        public void setLinkV2SyncImg(String linkV2SyncImg) {
            this.linkV2SyncImg = linkV2SyncImg;
        }

        public String getSourceName() {
            return sourceName;
        }

        public void setSourceName(String sourceName) {
            this.sourceName = sourceName;
        }

        public String getVideoUrl() {
            return videoUrl;
        }

        public void setVideoUrl(String videoUrl) {
            this.videoUrl = videoUrl;
        }

        public boolean isCurrentUserHasCollected() {
            return currentUserHasCollected;
        }

        public void setCurrentUserHasCollected(boolean currentUserHasCollected) {
            this.currentUserHasCollected = currentUserHasCollected;
        }

        public int getLikingsCount() {
            return likingsCount;
        }

        public void setLikingsCount(int likingsCount) {
            this.likingsCount = likingsCount;
        }

        public String[] getImages() {
            return images;
        }

        public void setImages(String[] images) {
            this.images = images;
        }

        public String getVideoDuration() {
            return videoDuration;
        }

        public void setVideoDuration(String videoDuration) {
            this.videoDuration = videoDuration;
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getCategory() {
            return category;
        }

        public void setCategory(String category) {
            this.category = category;
        }

        public String getStyle() {
            return style;
        }

        public void setStyle(String style) {
            this.style = style;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public SourceData getSourceData() {
            return sourceData;
        }

        public void setSourceData(SourceData sourceData) {
            this.sourceData = sourceData;
        }

        public String getHeadlineImgTb() {
            return headlineImgTb;
        }

        public void setHeadlineImgTb(String headlineImgTb) {
            this.headlineImgTb = headlineImgTb;
        }

        public String getLinkV2() {
            return linkV2;
        }

        public void setLinkV2(String linkV2) {
            this.linkV2 = linkV2;
        }

        public double getDatePicked() {
            return datePicked;
        }

        public void setDatePicked(double datePicked) {
            this.datePicked = datePicked;
        }

        public boolean is_top() {
            return isTop;
        }

        public void setTop(boolean top) {
            this.isTop = top;
        }

        public String getLink() {
            return link;
        }

        public void setLink(String link) {
            this.link = link;
        }

        public String getHeadlineImg() {
            return headlineImg;
        }

        public void setHeadlineImg(String headlineImg) {
            this.headlineImg = headlineImg;
        }

        public int getRepliesCount() {
            return repliesCount;
        }

        public void setRepliesCount(int repliesCount) {
            this.repliesCount = repliesCount;
        }

        public boolean isCurrentUserHasLiked() {
            return currentUserHasLiked;
        }

        public void setCurrentUserHasLiked(boolean currentUserHasLiked) {
            this.currentUserHasLiked = currentUserHasLiked;
        }

        public String getPageSource() {
            return pageSource;
        }

        public void setPageSource(String pageSource) {
            this.pageSource = pageSource;
        }

        public String getAuthor() {
            return author;
        }

        public void setAuthor(String author) {
            this.author = author;
        }

        public String getSummary() {
            return summary;
        }

        public void setSummary(String summary) {
            this.summary = summary;
        }

        public String getSource() {
            return source;
        }

        public void setSource(String source) {
            this.source = source;
        }

        public int getReplyRootId() {
            return replyRootId;
        }

        public void setReplyRootId(int replyRootId) {
            this.replyRootId = replyRootId;
        }

        public double getDateCreated() {
            return dateCreated;
        }

        public void setDateCreated(double dateCreated) {
            this.dateCreated = dateCreated;
        }
    }

    public class SourceData {

        @SerializedName("image")
        private String image;

        @SerializedName("summary")
        private String summary;

        @SerializedName("id")
        private String id;

        @SerializedName("title")
        private String title;

        public String getImage() {
            return image;
        }

        public void setImage(String image) {
            this.image = image;
        }

        public String getSummary() {
            return summary;
        }

        public void setSummary(String summary) {
            this.summary = summary;
        }

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }
    }

}
