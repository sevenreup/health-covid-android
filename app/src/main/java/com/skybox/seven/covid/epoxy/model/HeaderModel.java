package com.skybox.seven.covid.epoxy.model;

import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;

import com.airbnb.epoxy.EpoxyAttribute;
import com.airbnb.epoxy.EpoxyHolder;
import com.airbnb.epoxy.EpoxyModelClass;
import com.airbnb.epoxy.EpoxyModelWithHolder;
import com.skybox.seven.covid.R;

@EpoxyModelClass(layout = R.layout.model_header)
public class HeaderModel extends EpoxyModelWithHolder<HeaderModel.HeaderViewHolder> {
    @EpoxyAttribute String header;
    @Override
    protected HeaderViewHolder createNewHolder() {
        return new HeaderViewHolder();
    }

    @Override
    public void bind(@NonNull HeaderViewHolder holder) {
        super.bind(holder);
        holder.header.setText(header);
    }

    @Override
    protected int getDefaultLayout() {
        return R.layout.model_header;
    }

    class HeaderViewHolder extends EpoxyHolder {
        TextView header;
        @Override
        protected void bindView(@NonNull View itemView) {
            header = itemView.findViewById(R.id.header_title);
        }
    }
}
