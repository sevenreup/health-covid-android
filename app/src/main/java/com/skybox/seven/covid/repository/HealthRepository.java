package com.skybox.seven.covid.repository;

import com.skybox.seven.covid.data.entities.Advice;
import com.skybox.seven.covid.data.entities.InfoGraphic;

import java.util.ArrayList;
import java.util.List;

public class HealthRepository {
    private List<InfoGraphic> infoGraphicList = new ArrayList<>();
    private List<Advice> adviceList = new ArrayList<>();

    public HealthRepository() {
    }



    public List<InfoGraphic> getInfoGraphicList() {
        return infoGraphicList;
    }

    public void setInfoGraphicList(List<InfoGraphic> infoGraphicList) {
        this.infoGraphicList = infoGraphicList;
    }

    public List<Advice> getAdviceList() {
        return adviceList;
    }

    public void setAdviceList(List<Advice> adviceList) {
        this.adviceList = adviceList;
    }
}
