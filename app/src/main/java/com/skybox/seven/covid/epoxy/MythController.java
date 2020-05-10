package com.skybox.seven.covid.epoxy;

import android.content.Context;
import android.os.Handler;
import android.view.View;

import com.airbnb.epoxy.OnModelClickListener;
import com.airbnb.epoxy.Typed3EpoxyController;
import com.skybox.seven.covid.epoxy.model.MythGraphicInfoModel;
import com.skybox.seven.covid.epoxy.model.MythGraphicInfoModel_;
import com.skybox.seven.covid.epoxy.model.MythMainModel;
import com.skybox.seven.covid.epoxy.model.MythMainModel_;
import com.skybox.seven.covid.model.Myth;
import com.skybox.seven.covid.model.MythGraphicInfo;
import com.skybox.seven.covid.model.TipsChips;

import java.util.List;

public class MythController extends Typed3EpoxyController<TipsChips, List<Myth>, List<MythGraphicInfo>> {
    private Context context;
    private MythCallback callback;

    public MythController(Context context, MythCallback callback) {
        this.context = context;
        this.callback = callback;
    }

    @Override
    protected void buildModels(TipsChips current, List<Myth> mythList, List<MythGraphicInfo> mythGraphicInfoList) {
        switch (current) {
            case myth:
                for (Myth myth:
                        mythList) {
                    new MythMainModel_().id(myth.getTitle()).myth(myth).listener((model, parentView, clickedView, position) -> callback.onMythClick(mythList.get(position))).addTo(this);
                }
                break;
            case mythgraphicinfo:
                for (MythGraphicInfo graphics:
                        mythGraphicInfoList) {
                    for (String image:
                            graphics.getImages()) {
                        new MythGraphicInfoModel_().id(image).context(context).url(image).listener((model, parentView, clickedView, position) -> callback.onInfoGraphicClick(image)).addTo(this);
                    }
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
