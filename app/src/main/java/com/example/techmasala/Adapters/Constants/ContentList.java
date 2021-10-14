package com.example.techmasala.Adapters.Constants;

public class ContentList {
    private String imgUrl;
    private String url;
    private String description;
    private String title;
    private String author;

    public ContentList(String imgUrl, String url, String description, String title, String author) {
        this.imgUrl = imgUrl;
        this.url = url;
        this.description = description;
        this.title = title;
        this.author = author;
    }
    public ContentList(String imgUrl, String description, String title, String author) {
        this.imgUrl = imgUrl;
        this.description = description;
        this.title = title;
        this.author = author;
    }

    public String getImgUrl() {
        return imgUrl;
    }

    public void setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }
}

