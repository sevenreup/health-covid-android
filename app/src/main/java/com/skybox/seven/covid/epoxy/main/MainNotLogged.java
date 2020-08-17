package com.skybox.seven.covid.epoxy.main;

import android.view.View;
import android.widget.ImageView;

import androidx.annotation.NonNull;

import com.airbnb.epoxy.EpoxyAttribute;
import com.airbnb.epoxy.EpoxyHolder;
import com.airbnb.epoxy.EpoxyModelClass;
import com.airbnb.epoxy.EpoxyModelWithHolder;
import com.google.android.material.button.MaterialButton;
import com.skybox.seven.covid.R;

@EpoxyModelClass(layout = R.layout.model_main_not_logged)
public class MainNotLogged extends EpoxyModelWithHolder<MainNotLogged.MNLogHolder> {
    @EpoxyAttribute View.OnClickListener closeClick;
    @EpoxyAttribute View.OnClickListener loginClick;
    @EpoxyAttribute View.OnClickListener registerClick;
    @Override
    protected MNLogHolder createNewHolder() {
        return new MNLogHolder();
    }

    @Override
    public void bind(@NonNull MNLogHolder holder) {
        super.bind(holder);
        holder.close.setOnClickListener(closeClick);
        holder.login.setOnClickListener(loginClick);
        holder.register.setOnClickListener(registerClick);
    }

    @Override
    protected int getDefaultLayout() {
        return R.layout.model_main_not_logged;
    }

    public class MNLogHolder extends EpoxyHolder {

        MaterialButton login, register;
        ImageView close;

        @Override
        protected void bindView(@NonNull View itemView) {
            close = itemView.findViewById(R.id.clear_not);
            login = itemView.findViewById(R.id.model_login);
            register = itemView.findViewById(R.id.model_register);
        }
    }
}
