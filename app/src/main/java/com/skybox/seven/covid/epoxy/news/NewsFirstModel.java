package com.skybox.seven.covid.epoxy.news;

import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;

import com.airbnb.epoxy.EpoxyAttribute;
import com.airbnb.epoxy.EpoxyHolder;
import com.airbnb.epoxy.EpoxyModelClass;
import com.airbnb.epoxy.EpoxyModelWithHolder;
import com.skybox.seven.covid.R;
import com.skybox.seven.covid.model.NewsArticle;

@EpoxyModelClass(layout = R.layout.news_card)
public class NewsFirstModel extends EpoxyModelWithHolder<NewsFirstModel.NewsFirstViewHolder> {
    @EpoxyAttribute
    NewsArticle article;

    @Override
    protected NewsFirstViewHolder createNewHolder() {
        return new NewsFirstViewHolder();
    }

    @Override
    protected int getDefaultLayout() {
        return R.layout.news_card;
    }

    @Override
    public void bind(@NonNull NewsFirstViewHolder holder) {
        super.bind(holder);
        holder.description.setText(article.getDescription());
        holder.title.setText(article.getTitle());
    }

    class NewsFirstViewHolder extends EpoxyHolder {
        public TextView title;
        public TextView description;

        @Override
        protected void bindView(@NonNull View itemView) {
            this.title = itemView.findViewById(R.id.title);
            this.description = itemView.findViewById(R.id.content);
        }
    }
}
