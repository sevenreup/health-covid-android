package com.skybox.seven.covid.epoxy.model;

import android.content.Context;

import androidx.recyclerview.widget.GridLayoutManager;

import com.airbnb.epoxy.Carousel;
import com.airbnb.epoxy.ModelView;
import com.skybox.seven.covid.R;
import com.skybox.seven.covid.util.GridItemDecoration;

import org.jetbrains.annotations.NotNull;

@ModelView(autoLayout = ModelView.Size.WRAP_WIDTH_MATCH_HEIGHT)
public class HomeCarouselModel extends Carousel {

    public HomeCarouselModel(Context context) {
        super(context);
    }

    @NotNull
    @Override
    protected LayoutManager createLayoutManager() {
        addItemDecoration(new GridItemDecoration(getContext(), R.dimen.menu_card_margin));
        return new GridLayoutManager(getContext(), 2, GridLayoutManager.VERTICAL, false);
    }

}
