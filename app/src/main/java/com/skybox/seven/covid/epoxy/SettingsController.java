package com.skybox.seven.covid.epoxy;

import android.view.View;

import com.airbnb.epoxy.AutoModel;
import com.airbnb.epoxy.EpoxyController;
import com.airbnb.epoxy.OnModelClickListener;
import com.skybox.seven.covid.epoxy.model.SettingsHeaderModel_;
import com.skybox.seven.covid.epoxy.model.SettingsItemModel;
import com.skybox.seven.covid.epoxy.model.SettingsItemModel_;

public class SettingsController extends EpoxyController {
    @AutoModel
    SettingsHeaderModel_ headerModel_;
    SettingsCallback callback;

    public SettingsController(SettingsCallback callback) {
        this.callback = callback;
    }

    @Override
    protected void buildModels() {
        headerModel_.addTo(this);
        new SettingsItemModel_().id("contacts")
                .title("Edit Contacts")
                .value("20")
                .listener((model, parentView, clickedView, position) -> callback.onContactsClick())
                .addTo(this);
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
    }

    public interface SettingsCallback {
        void onContactsClick();
        void onNotificationClick();
        void onLanguageClick();
        void onHelpClick();
    }
}
