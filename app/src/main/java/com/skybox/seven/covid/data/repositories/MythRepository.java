package com.skybox.seven.covid.data.repositories;

import com.skybox.seven.covid.data.daos.MythsDAO;
import com.skybox.seven.covid.data.entities.Myth;
import com.skybox.seven.covid.data.resultentities.MythWithLanguages;

import java.util.List;

public class MythRepository {
    private static MythRepository INSTANCE = null;
    private MythsDAO mythsDAO;

    public MythRepository(MythsDAO mythsDAO) {
        this.mythsDAO = mythsDAO;
    }

    public List<MythWithLanguages> getAllMyths(Integer locale) {
        return mythsDAO.getAllLanguagesMyth();
    }

    public void addAllMyths(List<Myth> myths) {
        mythsDAO.insertAll(myths);
    }

    public static MythRepository getInstance(MythsDAO mythsDAO) {
        if (INSTANCE == null) {
            synchronized (MythRepository.class) {
                INSTANCE = new MythRepository(mythsDAO);
            }
        }
        return INSTANCE;
    }
}
