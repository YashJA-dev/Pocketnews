package com.example.techmasala.APIcalls.Contents.Tech.Articles;

import com.google.gson.annotations.SerializedName;

public class Articles {
    @SerializedName("source")
    private Source source;
    @SerializedName("urlToImage")
    private String imgUrl;
    @SerializedName("title")
    private String title;
    @SerializedName("description")
    private String description;
    @SerializedName("url")
    private String url;
    @SerializedName("content")
    private String content;
    @SerializedName("publishedAt")
    private String publishDate;

    //    private String author;
    public Articles(Source source,String imgUrl, String title, String description, String url, String content, String publishDate) {
//        this.author=author;
        this.source=source;
        this.title = title;
        this.content = content;
        this.imgUrl = imgUrl;
        this.publishDate = publishDate;
        this.description = description;
        this.url = url;
    }

    public Source getSource() {
        return source;
    }

    public void setSource(Source source) {
        this.source = source;
    }

    public Articles() {

    }

    public String getImgUrl() {
        return imgUrl;
    }

    public void setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getPublishDate() {
        return publishDate;
    }

    public void setPublishDate(String publishDate) {
        this.publishDate = publishDate;
    }

//    public String getAuthor() {
//        return author;
//    }
//
//    public void setAuthor(String author) {
//        this.author = author;
//    }
}

