package com.example.techmasala.APIcalls.Contents.Tech;

import com.example.techmasala.APIcalls.Contents.Tech.Articles.Articles;

import java.util.List;

public class Tech{
    private String status;
    private String totalResults;
    private List<Articles> articles;

    public Tech(String status, String totalResults, List<Articles> articles) {
        this.status = status;
        this.totalResults = totalResults;
        this.articles = articles;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getTotalResults() {
        return totalResults;
    }

    public void setTotalResults(String totalResults) {
        this.totalResults = totalResults;
    }

    public List<Articles> getArticles() {
        return articles;
    }

    public void setArticles(List<Articles> articles) {
        this.articles = articles;
    }
}

