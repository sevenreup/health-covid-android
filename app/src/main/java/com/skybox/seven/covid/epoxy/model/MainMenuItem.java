package com.skybox.seven.covid.epoxy.model;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;

import com.airbnb.epoxy.EpoxyAttribute;
import com.airbnb.epoxy.EpoxyHolder;
import com.airbnb.epoxy.EpoxyModelClass;
import com.airbnb.epoxy.EpoxyModelWithHolder;
import com.google.android.material.card.MaterialCardView;
import com.skybox.seven.covid.R;

@EpoxyModelClass(layout = R.layout.model_main_menu_item)
public class MainMenuItem extends EpoxyModelWithHolder<MainMenuItem.MainMenuItemVH> {
    @EpoxyAttribute int image;
    @EpoxyAttribute int text;
    @EpoxyAttribute View.OnClickListener listener;
    @Override
    protected MainMenuItemVH createNewHolder() {
        return new MainMenuItemVH();
    }

    @Override
    public void bind(@NonNull MainMenuItemVH holder) {
        super.bind(holder);
        holder.image.setImageResource(image);
        holder.title.setText(text);
        holder.container.setOnClickListener(listener);
    }

    @Override
    protected int getDefaultLayout() {
        return R.layout.model_main_menu_item;
    }

    public class MainMenuItemVH extends EpoxyHolder{
        public MaterialCardView container;
        public ImageView image;
        public TextView title;
        @Override
        protected void bindView(@NonNull View itemView) {
            container = itemView.findViewById(R.id.container);
            image = itemView.findViewById(R.id.model_image);
            title = itemView.findViewById(R.id.model_title);
        }
    }
}
