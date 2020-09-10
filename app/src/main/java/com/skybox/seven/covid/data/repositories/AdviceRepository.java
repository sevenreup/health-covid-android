package com.skybox.seven.covid.data.repositories;

import com.skybox.seven.covid.data.daos.AdviceDAO;
import com.skybox.seven.covid.data.entities.Advice;

import java.util.List;

public class AdviceRepository {
    private AdviceDAO adviceDAO;

    public AdviceRepository(AdviceDAO adviceDAO) {
        this.adviceDAO = adviceDAO;
    }

    public List<Advice> getAllAdvice(Integer locale) {
        return adviceDAO.getAllAdvices(locale);
    }

    public void addAllAdvices(List<Advice> advice) {
        adviceDAO.insertAll(advice);
    }
}
