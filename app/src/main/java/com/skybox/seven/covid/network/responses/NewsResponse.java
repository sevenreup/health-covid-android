package com.skybox.seven.covid.network.responses;

import com.google.gson.annotations.SerializedName;
import com.skybox.seven.covid.model.NewsArticle;

import java.util.ArrayList;
import java.util.List;

public class NewsResponse {
    @SerializedName("status")
    private String status;
    @SerializedName("totalResults")
    private int total;
    @SerializedName("articles")
    private List<NewsArticle> articles;

    public NewsResponse() {
        articles = new ArrayList<>();
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public List<NewsArticle> getArticles() {
        return articles;
    }

    public void setArticles(List<NewsArticle> articles) {
        this.articles = articles;
    }
}
