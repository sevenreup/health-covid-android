package com.skybox.seven.covid.epoxy.health;


import android.content.Context;
import android.view.View;
import android.widget.ImageView;

import androidx.annotation.NonNull;

import com.airbnb.epoxy.EpoxyAttribute;
import com.airbnb.epoxy.EpoxyHolder;
import com.airbnb.epoxy.EpoxyModelClass;
import com.airbnb.epoxy.EpoxyModelWithHolder;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.skybox.seven.covid.GlideApp;
import com.skybox.seven.covid.R;


@EpoxyModelClass(layout = R.layout.model_info_graphic_main)
public class InfoGraphicModel extends EpoxyModelWithHolder<InfoGraphicModel.InfoGModelHolder> {
    @EpoxyAttribute String url;
    @EpoxyAttribute Context context;
    @EpoxyAttribute View.OnClickListener listener;
    @Override
    protected InfoGModelHolder createNewHolder() {
        return new InfoGModelHolder();
    }

    @Override
    public void bind(@NonNull InfoGModelHolder holder) {
        super.bind(holder);
        GlideApp.with(context)
                .load(url)
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .error(R.drawable.ic_error)
                .into(holder.image);
        holder.image.setOnClickListener(listener);
    }

    @Override
    protected int getDefaultLayout() {
        return R.layout.model_info_graphic_main;
    }

    public static class InfoGModelHolder extends EpoxyHolder {
        ImageView image;
        @Override
        protected void bindView(@NonNull View itemView) {
            image = itemView.findViewById(R.id.info_image);
        }
    }
}
