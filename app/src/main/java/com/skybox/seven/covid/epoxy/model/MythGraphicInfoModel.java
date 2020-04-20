package com.skybox.seven.covid.epoxy.model;

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

@EpoxyModelClass(layout = R.layout.model_graphic_info_myth)
public class MythGraphicInfoModel extends EpoxyModelWithHolder<MythGraphicInfoModel.GrafInfoModelHolder> {

    @EpoxyAttribute
    String url;
    @EpoxyAttribute
    Context context;

    @Override
    protected MythGraphicInfoModel.GrafInfoModelHolder createNewHolder() { return new GrafInfoModelHolder(); }

    @Override
    public void bind(@NonNull GrafInfoModelHolder holder) {
        super.bind(holder);
        GlideApp.with(context)
                .load(url)
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .error(R.drawable.ic_error)
                .into(holder.image);
    }

    @Override
    protected int getDefaultLayout() {
        return R.layout.model_graphic_info_myth;
    }

    public class GrafInfoModelHolder extends EpoxyHolder {
        ImageView image;

        @Override
        protected void bindView(@NonNull View itemView) {
            ImageView image;
            image = itemView.findViewById(R.id.myth_info_image);
        }
    }
}
