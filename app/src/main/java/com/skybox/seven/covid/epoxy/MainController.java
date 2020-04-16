package com.skybox.seven.covid.epoxy;

import android.view.View;

import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import com.airbnb.epoxy.OnModelClickListener;
import com.airbnb.epoxy.TypedEpoxyController;
import com.skybox.seven.covid.epoxy.model.MainMenuItem;
import com.skybox.seven.covid.epoxy.model.MainMenuItem_;
import com.skybox.seven.covid.model.MenuItem;

import java.util.List;

public class MainController extends TypedEpoxyController<List<MenuItem>> {
    private NavController controller;
    public MainController(NavController controller) {
        super();
        this.controller = controller;
    }

    @Override
    protected void buildModels(List<MenuItem> data) {
        for (MenuItem menu:
             data) {
            new MainMenuItem_().id(menu.getImage()).image(menu.getImage()).text(menu.getTitle()).listener(new OnModelClickListener<MainMenuItem_, MainMenuItem.MainMenuItemVH>() {
                @Override
                public void onClick(MainMenuItem_ model, MainMenuItem.MainMenuItemVH parentView, View clickedView, int position) {
                    controller.navigate(menu.getLocation());
                }
            }).addTo(this);
        }
    }
}
