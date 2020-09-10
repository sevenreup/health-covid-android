package com.skybox.seven.covid.data.repositories;

import com.skybox.seven.covid.data.daos.LanguageDAO;
import com.skybox.seven.covid.data.entities.Language;

import java.util.List;

public class LanguageRepository {
    private LanguageDAO languageDAO;

    public LanguageRepository(LanguageDAO languageDAO) {
        this.languageDAO = languageDAO;
    }

    public List<Language> getAll() {
        return languageDAO.getAllLanguages();
    }

    public void addAllLanguages(List<Language> languages) {
        languageDAO.insertAll(languages);
    }
}
