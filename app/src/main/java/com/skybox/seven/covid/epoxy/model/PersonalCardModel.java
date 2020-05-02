package com.skybox.seven.covid.epoxy.model;

import android.view.View;

import androidx.annotation.NonNull;

import com.airbnb.epoxy.EpoxyAttribute;
import com.airbnb.epoxy.EpoxyHolder;
import com.airbnb.epoxy.EpoxyModelClass;
import com.airbnb.epoxy.EpoxyModelWithHolder;
import com.skybox.seven.covid.R;

@EpoxyModelClass(layout = R.layout.model_personal_card)
public class PersonalCardModel extends EpoxyModelWithHolder<PersonalCardModel.PersonCardHolder> {
    @EpoxyAttribute String name;
    @Override
    protected PersonCardHolder createNewHolder() {
        return new PersonCardHolder();
    }

    @Override
    protected int getDefaultLayout() {
        return R.layout.model_personal_card;
    }

    public class PersonCardHolder extends EpoxyHolder {
        @Override
        protected void bindView(@NonNull View itemView) {

        }
    }
}
