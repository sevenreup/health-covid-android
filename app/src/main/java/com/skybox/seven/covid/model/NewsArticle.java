package com.skybox.seven.covid.model;

import org.json.JSONObject;

public class NewsArticle {

    private String author, title, description, url, urlToImage, publishedAt, content;

    private JSONObject source;

    public String getAuthor() {
        return author;
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public String getContent() {
        return content;
    }
}
