package com.skybox.seven.covid.epoxy;

import android.view.View;

import com.airbnb.epoxy.AutoModel;
import com.airbnb.epoxy.EpoxyController;
import com.airbnb.epoxy.OnModelClickListener;
import com.airbnb.epoxy.Typed2EpoxyController;
import com.airbnb.epoxy.TypedEpoxyController;
import com.skybox.seven.covid.epoxy.model.MainNotLogged;
import com.skybox.seven.covid.epoxy.model.MainNotLogged_;
import com.skybox.seven.covid.epoxy.model.SettingsHeaderModel_;
import com.skybox.seven.covid.epoxy.model.SettingsItemModel_;

public class SettingsController extends Typed2EpoxyController<Boolean, Boolean> {
    @AutoModel
    SettingsHeaderModel_ headerModel_;
    @AutoModel
    MainNotLogged_ notLogged_;
    private SettingsCallback callback;

    public SettingsController(SettingsCallback callback) {
        this.callback = callback;
    }

    @Override
    protected void buildModels(Boolean isAuth, Boolean showLogin) {
        headerModel_.addIf(isAuth,this);
        notLogged_
                .closeClick((model, parentView, clickedView, position) -> callback.onLogNotClose())
                .loginClick((model, parentView, clickedView, position) -> callback.onLoginClick())
                .registerClick((model, parentView, clickedView, position) -> callback.onRegisterClick())
                .addIf((!isAuth && showLogin), this);
        new SettingsItemModel_().id("contacts")
                .title("Edit Contacts")
                .value("20")
                .listener((model, parentView, clickedView, position) -> callback.onContactsClick())
                .addIf(isAuth,this);
        new SettingsItemModel_().id("notifications")
                .title("Notifications")
                .value("On")
                .listener((model, parentView, clickedView, position) -> callback.onNotificationClick())
                .addTo(this);
        new SettingsItemModel_().id("Language")
                .title("Language")
                .value("En")
                .listener((model, parentView, clickedView, position) -> callback.onLanguageClick())
                .addTo(this);
        new SettingsItemModel_().id("help")
                .title("Help")
                .value("")
                .listener((model, parentView, clickedView, position) -> callback.onHelpClick())
                .addTo(this);
        new SettingsItemModel_().id("logout")
                .title("Logout")
                .value("")
                .listener((model, parentView, clickedView, position) -> callback.onLogoutClick())
                .addIf(isAuth,this);
    }

    public interface SettingsCallback {
        void onContactsClick();
        void onNotificationClick();
        void onLanguageClick();
        void onHelpClick();
        void onLogoutClick();
        void onLoginClick();
        void onRegisterClick();
        void onLogNotClose();
    }
}
