package com.skybox.seven.covid.data.repositories;

import com.skybox.seven.covid.data.daos.MythsDAO;
import com.skybox.seven.covid.data.entities.Myth;

import java.util.List;

public class MythRepository {
    private MythsDAO mythsDAO;

    public MythRepository(MythsDAO mythsDAO) {
        this.mythsDAO = mythsDAO;
    }

    public List<Myth> getAllMyths(Integer locale) {
        return mythsDAO.getAllLanguagesMyth(locale);
    }

    public void addAllMyths(List<Myth> myths) {
        mythsDAO.insertAll(myths);
    }
}
