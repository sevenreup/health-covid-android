package com.skybox.seven.covid.epoxy.generic;

import android.view.View;

import androidx.annotation.NonNull;

import com.airbnb.epoxy.EpoxyHolder;
import com.airbnb.epoxy.EpoxyModelClass;
import com.airbnb.epoxy.EpoxyModelWithHolder;
import com.skybox.seven.covid.R;

@EpoxyModelClass(layout = R.layout.model_list_network_error)
public class ListNetworkErrorModel extends EpoxyModelWithHolder<ListNetworkErrorModel.ListNetworkErrorViewHolder> {
    @Override
    protected ListNetworkErrorViewHolder createNewHolder() {
        return new ListNetworkErrorViewHolder();
    }

    @Override
    protected int getDefaultLayout() {
        return R.layout.model_list_network_error;
    }

    static class ListNetworkErrorViewHolder extends EpoxyHolder {

        @Override
        protected void bindView(@NonNull View itemView) {

        }
    }
}
