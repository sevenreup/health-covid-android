package com.skybox.seven.covid.data.repositories;

import com.skybox.seven.covid.data.daos.InfoGraphicDAO;
import com.skybox.seven.covid.data.entities.InfoGraphic;

import java.util.List;

public class InfoGraphicRepository {
    private InfoGraphicDAO infoGraphicDAO;

    public InfoGraphicRepository(InfoGraphicDAO infoGraphicDAO) {
        this.infoGraphicDAO = infoGraphicDAO;
    }

    public List<InfoGraphic> getAllInfoGraphics(Integer locale, Integer type) {
        return infoGraphicDAO.getAllInfoGraphics(locale, type);
    }

    public void addAllMyths(List<InfoGraphic> infoGraphics) {
        infoGraphicDAO.insertAll(infoGraphics);
    }
}
