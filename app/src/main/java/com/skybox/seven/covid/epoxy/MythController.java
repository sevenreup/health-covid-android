package com.skybox.seven.covid.epoxy;

import android.content.Context;

import com.airbnb.epoxy.Typed3EpoxyController;
import com.skybox.seven.covid.data.entities.InfoGraphic;
import com.skybox.seven.covid.epoxy.model.MythGraphicInfoModel_;
import com.skybox.seven.covid.epoxy.model.MythMainModel_;
import com.skybox.seven.covid.data.entities.Myth;
import com.skybox.seven.covid.model.TipsChips;

import java.util.List;

public class MythController extends Typed3EpoxyController<TipsChips, List<Myth>, List<InfoGraphic>> {
    private Context context;
    private MythCallback callback;

    public MythController(Context context, MythCallback callback) {
        this.context = context;
        this.callback = callback;
    }

    @Override
    protected void buildModels(TipsChips current, List<Myth> mythList, List<InfoGraphic> mythGraphicInfoList) {
        switch (current) {
            case myth:
                for (Myth myth:
                        mythList) {
                    new MythMainModel_().id(myth.getTitle()).myth(myth).listener((model, parentView, clickedView, position) -> callback.onMythClick(mythList.get(position))).addTo(this);
                }
                break;
            case mythgraphicinfo:
                for (InfoGraphic graphics:
                        mythGraphicInfoList) {
                        new MythGraphicInfoModel_().id(graphics.getId()).context(context).url(graphics.getImage()).listener((model, parentView, clickedView, position) -> callback.onInfoGraphicClick(graphics.getImage())).addTo(this);
                }
                break;
            default:
                break;
        }
    }

    public interface MythCallback {
        void onMythClick(Myth myth);
        void onInfoGraphicClick(String image);
    }
}
