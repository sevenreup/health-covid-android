package com.skybox.seven.covid.epoxy;

import android.view.View;

import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import com.airbnb.epoxy.AutoModel;
import com.airbnb.epoxy.CarouselModel_;
import com.airbnb.epoxy.OnModelClickListener;
import com.airbnb.epoxy.TypedEpoxyController;
import com.skybox.seven.covid.epoxy.model.HomeCarouselModelModel_;
import com.skybox.seven.covid.epoxy.model.MainMenuItem;
import com.skybox.seven.covid.epoxy.model.MainMenuItem_;
import com.skybox.seven.covid.epoxy.model.PersonalCardModel_;
import com.skybox.seven.covid.model.MenuItem;

import java.util.ArrayList;
import java.util.List;

public class MainController extends TypedEpoxyController<List<MenuItem>> {
    @AutoModel PersonalCardModel_ header;
    private NavController controller;
    public MainController(NavController controller) {
        super();
        this.controller = controller;
    }

    @Override
    protected void buildModels(List<MenuItem> data) {
        header.addTo(this);
        List<MainMenuItem_> mainMenuItem_s = new ArrayList<>();
        for (MenuItem menu:
             data) {
            MainMenuItem_ item = new MainMenuItem_().id(menu.getImage()).image(menu.getImage()).text(menu.getTitle()).listener((model, parentView, clickedView, position) -> controller.navigate(menu.getLocation()));
            mainMenuItem_s.add(item);
        }
        new HomeCarouselModelModel_().id("carousel").models(mainMenuItem_s).addTo(this);
    }
}
