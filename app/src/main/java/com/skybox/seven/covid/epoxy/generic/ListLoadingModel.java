package com.skybox.seven.covid.epoxy.generic;

import android.view.View;

import androidx.annotation.NonNull;

import com.airbnb.epoxy.EpoxyHolder;
import com.airbnb.epoxy.EpoxyModelClass;
import com.airbnb.epoxy.EpoxyModelWithHolder;
import com.skybox.seven.covid.R;

@EpoxyModelClass(layout = R.layout.model_list_loading)
public class ListLoadingModel extends EpoxyModelWithHolder<ListLoadingModel.ListLoadingViewHolder> {
    @Override
    protected ListLoadingViewHolder createNewHolder() {
        return new ListLoadingViewHolder();
    }

    @Override
    protected int getDefaultLayout() {
        return R.layout.model_list_loading;
    }

    static class ListLoadingViewHolder extends EpoxyHolder {
        @Override
        protected void bindView(@NonNull View itemView) {

        }
    }
}
