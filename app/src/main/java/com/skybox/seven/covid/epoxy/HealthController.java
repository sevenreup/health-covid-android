package com.skybox.seven.covid.epoxy;

import android.util.Log;

import com.airbnb.epoxy.Typed3EpoxyController;
import com.skybox.seven.covid.epoxy.model.AdviceMainModel_;
import com.skybox.seven.covid.model.Advice;
import com.skybox.seven.covid.model.InfoGraphic;

import java.util.List;

public class HealthController extends Typed3EpoxyController<Advice.CurrentChip, List<Advice>, List<InfoGraphic>> {

    @Override
    protected void buildModels(Advice.CurrentChip current, List<Advice> adviceList, List<InfoGraphic> infoGraphicList) {
        Log.i("TAG", "buildModels: " + current + " " + adviceList);
        switch (current) {
            case advice:
                for (Advice advice:
                     adviceList) {
                    new AdviceMainModel_().id(advice.getTitle()).advice(advice).addTo(this);
                }
                break;
            case infographic:
                break;
            default:
                break;
        }
    }
}
