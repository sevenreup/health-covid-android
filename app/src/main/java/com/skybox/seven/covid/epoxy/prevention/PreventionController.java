package com.skybox.seven.covid.epoxy.prevention;

import com.airbnb.epoxy.AutoModel;
import com.airbnb.epoxy.Typed2EpoxyController;
import com.skybox.seven.covid.R;
import com.skybox.seven.covid.data.entities.Advice;
import com.skybox.seven.covid.epoxy.generic.ListLoadingModel_;

import java.util.List;

public class PreventionController extends Typed2EpoxyController<Boolean, List<Advice>> {
    @AutoModel
    ListLoadingModel_ listLoadingModel_;
    PreventionCallback callback;

    public PreventionController(PreventionCallback callback) {
        this.callback = callback;
    }

    @Override
    protected void buildModels(Boolean loading, List<Advice> preventions) {
        listLoadingModel_.addIf(loading, this);
        for (Advice prevention : preventions) {
            new PreventionEpoxyModel_().id(prevention.getTitle())
                    .image(R.drawable.ic_prevention)
                    .title(prevention.getShortTitle())
                    .description(prevention.getAdvice())
                    .listener((model, parentView, clickedView, position) -> callback.preventionClick(prevention))
                    .addTo(this);
        }
    }

    public interface PreventionCallback {
        void preventionClick(Advice advice);
    }
}
