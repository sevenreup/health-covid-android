package com.skybox.seven.covid.epoxy.settings;

import android.view.View;

import androidx.annotation.NonNull;

import com.airbnb.epoxy.EpoxyHolder;
import com.airbnb.epoxy.EpoxyModelClass;
import com.airbnb.epoxy.EpoxyModelWithHolder;
import com.skybox.seven.covid.R;

@EpoxyModelClass(layout = R.layout.model_settings_header)
public class SettingsHeaderModel extends EpoxyModelWithHolder<SettingsHeaderModel.SettingsHeaderHolder> {
    @Override
    protected SettingsHeaderHolder createNewHolder() {
        return new SettingsHeaderHolder();
    }

    @Override
    protected int getDefaultLayout() {
        return R.layout.model_settings_header;
    }

    public class SettingsHeaderHolder extends EpoxyHolder {
        @Override
        protected void bindView(@NonNull View itemView) {

        }
    }
}
