package com.skybox.seven.covid.epoxy;

import com.airbnb.epoxy.Typed3EpoxyController;
import com.skybox.seven.covid.model.Advice;
import com.skybox.seven.covid.model.InfoGraphic;

import java.util.List;

public class HealthController extends Typed3EpoxyController<Advice.CurrentChip, List<Advice>, List<InfoGraphic>> {

    @Override
    protected void buildModels(Advice.CurrentChip current, List<Advice> adviceList, List<InfoGraphic> infoGraphicList) {
        switch (current) {
            case advice:
                break;
            case infographic:
                break;
            default:
                break;
        }
    }
}
