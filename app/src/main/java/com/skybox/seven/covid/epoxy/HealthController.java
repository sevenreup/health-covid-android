package com.skybox.seven.covid.epoxy;

import android.content.Context;

import com.airbnb.epoxy.Typed3EpoxyController;
import com.skybox.seven.covid.epoxy.model.AdviceMainModel_;
import com.skybox.seven.covid.epoxy.model.InfoGraphicModel_;
import com.skybox.seven.covid.data.Advice;
import com.skybox.seven.covid.model.InfoGraphic;
import com.skybox.seven.covid.model.TipsChips;

import java.util.List;

public class HealthController extends Typed3EpoxyController<TipsChips, List<Advice>, List<InfoGraphic>> {
    Context context;
    HealthTipsCallback callback;

    public HealthController(Context context, HealthTipsCallback callback) {
        this.context = context;
        this.callback = callback;
    }

    @Override
    protected void buildModels(TipsChips current, List<Advice> adviceList, List<InfoGraphic> infoGraphicList) {

        switch (current) {
            case advice:
                for (Advice advice:
                     adviceList) {
                    new AdviceMainModel_().id(advice.getTitle()).advice(advice).listener((model, parentView, clickedView, position) -> callback.onAdviceClick(adviceList.get(position))).addTo(this);
                }
                break;
            case infographic:
                for (InfoGraphic graphics:
                     infoGraphicList) {
                    for (String image:
                         graphics.getImages()) {
                        new InfoGraphicModel_().id(image).context(context).listener((model, parentView, clickedView, position) -> callback.onInfoGraphicClick(image)).url(image).addTo(this);
                    }
                }
                break;
            default:
                break;
        }
    }

    public interface HealthTipsCallback {
        void onAdviceClick(Advice advice);
        void onInfoGraphicClick(String graphic);
    }
}
