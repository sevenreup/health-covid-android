package com.skybox.seven.covid.epoxy.main;

import android.util.Log;

import com.airbnb.epoxy.AutoModel;
import com.airbnb.epoxy.Typed3EpoxyController;
import com.skybox.seven.covid.epoxy.emergency.EmergencyContanctsCardModel_;
import com.skybox.seven.covid.model.MenuItem;

import java.util.ArrayList;
import java.util.List;

public class MainController extends Typed3EpoxyController<Boolean, Boolean,List<MenuItem>> {
    @AutoModel PersonalCardModel_ header;
    @AutoModel MainNotLogged_ notLogged_;
    @AutoModel
    EmergencyContanctsCardModel_ emergencyContancts_;
    private MainControllerCallback callback;

    public MainController(MainControllerCallback callback) {
        super();
        this.callback = callback;
    }

    @Override
    protected void buildModels(Boolean isLogged, Boolean showLogin, List<MenuItem> data) {
        Log.e("TAG", "buildModels: " + data.size());
        if (isLogged) {
            new PersonalCardModel_().listener((model, parentView, clickedView, position) -> callback.OnPersonalCardClick()).id("personal card").addTo(this);
        } else {
                new MainNotLogged_().id("not logged").closeClick((model, parentView, clickedView, position) -> callback.OnCloseLogNotification())
                        .loginClick((model, parentView, clickedView, position) -> callback.OnLoginClick())
                        .registerClick((model, parentView, clickedView, position) -> callback.OnRegisterClick())
                        .addIf(showLogin,this);
        }
        emergencyContancts_
                .emergencyContactslistener((model, parentView, clickedView, position) -> callback.OnEmergencyContactsClick())
                .selfTestContactslistener((model, parentView, clickedView, position) -> callback.OnSelfTestClick())
                .addTo(this);
        List<MainMenuItem_> mainMenuItem_s = new ArrayList<>();

        for (int i = 0; i < data.size(); i++) {
            MenuItem menu = data.get(i);
            MainMenuItem_ item = new MainMenuItem_()
                    .id(i).image(menu.getImage())
                    .text(menu.getTitle())
                    .listener((model, parentView, clickedView, position) -> callback.NavigateToPage(menu.getLocation()));
            mainMenuItem_s.add(item);
        }
        new HomeCarouselModelModel_().id("carousel").hasFixedSize(true).models(mainMenuItem_s).addTo(this);
    }

    public interface MainControllerCallback {
        void OnRegisterClick();
        void OnLoginClick();
        void OnCloseLogNotification();
        void NavigateToPage(int dest);
        void OnSelfTestClick();
        void OnPersonalCardClick();
        void OnEmergencyContactsClick();
    }
}
