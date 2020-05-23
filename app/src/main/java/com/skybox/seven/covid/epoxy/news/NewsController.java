package com.skybox.seven.covid.epoxy.news;

import com.airbnb.epoxy.AutoModel;
import com.airbnb.epoxy.Typed2EpoxyController;
import com.skybox.seven.covid.epoxy.generic.ListLoadingModel_;
import com.skybox.seven.covid.model.NewsArticle;

import java.util.List;

public class NewsController extends Typed2EpoxyController<Boolean, List<NewsArticle>> {
    @AutoModel ListLoadingModel_ loadingModel_;
    @Override
    protected void buildModels(Boolean loading, List<NewsArticle> articles) {
        loadingModel_.addIf(loading, this);
        for (NewsArticle article:
             articles) {
            new NewsFirstModel_().id(article.getTitle()).article(article).addTo(this);
        }
    }
}
