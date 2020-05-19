package com.skybox.seven.covid.data.repositories;

import com.skybox.seven.covid.data.daos.AdviceDAO;
import com.skybox.seven.covid.data.entities.Advice;

import java.util.List;

public class AdviceRepository {
    private static AdviceRepository INSTANCE = null;
    private AdviceDAO adviceDAO;

    public AdviceRepository(AdviceDAO adviceDAO) {
        this.adviceDAO = adviceDAO;
    }

    public List<Advice> getAllMyths(Integer locale) {
        return adviceDAO.getAllAdvices(locale);
    }

    public void addAllAdvices(List<Advice> advice) {
        adviceDAO.insertAll(advice);
    }

    public static AdviceRepository getInstance(AdviceDAO adviceDAO) {
        if (INSTANCE == null) {
            synchronized (AdviceRepository.class) {
                INSTANCE = new AdviceRepository(adviceDAO);
            }
        }
        return INSTANCE;
    }
}
