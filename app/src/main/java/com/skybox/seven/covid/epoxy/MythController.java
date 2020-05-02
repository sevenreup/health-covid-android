package com.skybox.seven.covid.epoxy;

import android.content.Context;
import android.os.Handler;

import com.airbnb.epoxy.Typed3EpoxyController;
import com.skybox.seven.covid.epoxy.model.MythGraphicInfoModel_;
import com.skybox.seven.covid.epoxy.model.MythMainModel_;
import com.skybox.seven.covid.model.Myth;
import com.skybox.seven.covid.model.MythGraphicInfo;

import java.util.List;

public class MythController extends Typed3EpoxyController<Myth.CurrentChip, List<Myth>, List<MythGraphicInfo>> {
    Context context;

    public MythController(Context context) {
        this.context = context;
    }

    @Override
    protected void buildModels(Myth.CurrentChip current, List<Myth> mythList, List<MythGraphicInfo> mythGraphicInfoList) {
        switch (current) {
            case myth:
                for (Myth myth:
                        mythList) {
                    new MythMainModel_().id(myth.getTitle()).myth(myth).addTo(this);
                }
                break;
            case mythgraphicinfo:
                for (MythGraphicInfo graphics:
                        mythGraphicInfoList) {
                    for (String image:
                            graphics.getImages()) {
                        new MythGraphicInfoModel_().id(image).context(context).url(image).addTo(this);
                    }
                }
                break;
            default:
                break;
        }
    }
}
