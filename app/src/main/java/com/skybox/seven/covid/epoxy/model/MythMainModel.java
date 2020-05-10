package com.skybox.seven.covid.epoxy.model;

import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;

import com.airbnb.epoxy.EpoxyAttribute;
import com.airbnb.epoxy.EpoxyHolder;
import com.airbnb.epoxy.EpoxyModelClass;
import com.airbnb.epoxy.EpoxyModelWithHolder;
import com.google.android.material.card.MaterialCardView;
import com.skybox.seven.covid.R;
import com.skybox.seven.covid.model.Myth;


@EpoxyModelClass(layout = R.layout.mythrow)
public class MythMainModel extends EpoxyModelWithHolder<MythMainModel.MyMainModelHolder> {
    @EpoxyAttribute
    Myth myth;
    @EpoxyAttribute
    View.OnClickListener listener;

    @Override
    protected MythMainModel.MyMainModelHolder createNewHolder() { return new MyMainModelHolder(); }

    @Override
    protected int getDefaultLayout() {
        return R.layout.mythrow;
    }

    @Override
    public void bind(@NonNull MyMainModelHolder holder) {
        super.bind(holder);
        holder.paragraph.setText(myth.getParagraph());
        holder.title.setText(myth.getTitle());
    }

    public class MyMainModelHolder extends EpoxyHolder {
        MaterialCardView holder;
        TextView title, paragraph;
        @Override
        protected void bindView(@NonNull View itemView) {
            holder = itemView.findViewById(R.id.myth_holder);
            title = itemView.findViewById(R.id.mythTitle);
            paragraph = itemView.findViewById(R.id.mythPara);
        }
    }
}
