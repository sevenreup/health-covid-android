package com.skybox.seven.covid.epoxy.mythbuster;

import com.airbnb.epoxy.AutoModel;
import com.airbnb.epoxy.Typed2EpoxyController;
import com.skybox.seven.covid.R;
import com.skybox.seven.covid.data.entities.Myth;
import com.skybox.seven.covid.epoxy.generic.ListLoadingModel_;
import com.skybox.seven.covid.epoxy.prevention.PreventionEpoxyModel_;

import java.util.List;

public class MythBusterController extends Typed2EpoxyController<Boolean, List<Myth>> {
    @AutoModel
    ListLoadingModel_ listLoadingModel_;
    MythCallbacks callbacks;

    public MythBusterController(MythCallbacks callbacks) {
        this.callbacks = callbacks;
    }

    @Override
    protected void buildModels(Boolean loading, List<Myth> mythbusters) {
        listLoadingModel_.addIf(loading, this);
        for (Myth mythbuster: mythbusters) {
            new PreventionEpoxyModel_().id(mythbuster.getMyth())
                    .title(mythbuster.getTitle())
                    .description(mythbuster.getMyth())
                    .image(R.drawable.ic_myth)
                    .listener((model, parentView, clickedView, position) -> callbacks.onMythClicked(mythbuster))
                    .addTo(this);
        }
    }

    public interface MythCallbacks {
        void onMythClicked(Myth myth);
    }
}
