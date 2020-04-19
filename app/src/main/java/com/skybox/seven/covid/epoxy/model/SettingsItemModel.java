package com.skybox.seven.covid.epoxy.model;

import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;

import com.airbnb.epoxy.EpoxyAttribute;
import com.airbnb.epoxy.EpoxyHolder;
import com.airbnb.epoxy.EpoxyModelClass;
import com.airbnb.epoxy.EpoxyModelWithHolder;
import com.skybox.seven.covid.R;

@EpoxyModelClass(layout = R.layout.model_settings_item)
public class SettingsItemModel extends EpoxyModelWithHolder<SettingsItemModel.SettingsItemHolder> {
    @EpoxyAttribute String title;
    @EpoxyAttribute String value;
    @EpoxyAttribute View.OnClickListener listener;

    @Override
    protected SettingsItemHolder createNewHolder() {
        return new SettingsItemHolder();
    }

    @Override
    public void bind(@NonNull SettingsItemHolder holder) {
        super.bind(holder);
        holder.title.setText(title);
        holder.value.setText(value);
        holder.container.setOnClickListener(listener);
    }

    @Override
    protected int getDefaultLayout() {
        return R.layout.model_settings_item;
    }

    public class SettingsItemHolder extends EpoxyHolder {

        TextView title, value;
        RelativeLayout container;

        @Override
        protected void bindView(@NonNull View itemView) {
            title = itemView.findViewById(R.id.setting_title);
            value = itemView.findViewById(R.id.setting_text);
            container = itemView.findViewById(R.id.setting_item_holder);
        }
    }
}
