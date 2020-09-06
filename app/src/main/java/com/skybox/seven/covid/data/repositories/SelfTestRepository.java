package com.skybox.seven.covid.data.repositories;

import android.os.AsyncTask;

import com.skybox.seven.covid.data.entities.SelfTestAnswer;
import com.skybox.seven.covid.data.daos.SelfTestAnswerDAO;

import java.util.List;

public class SelfTestRepository {
    private static SelfTestRepository INSTANCE = null;
    private SelfTestAnswerDAO resultDAO;

    public SelfTestRepository(SelfTestAnswerDAO resultDAO) {
        this.resultDAO = resultDAO;
    }

    public void addResults(SelfTestAnswer result) {
        resultDAO.insertAll(result);
    }

    public List<SelfTestAnswer> getAllTests() {
       return resultDAO.getAll();
    }

    public void deleteTest(SelfTestAnswer result) {
        new deleteTestAsync(resultDAO).execute(result);
    }

    private static class deleteTestAsync extends AsyncTask<SelfTestAnswer, Void, Void> {
        private SelfTestAnswerDAO selfTestAnswerDAO;

        public deleteTestAsync(SelfTestAnswerDAO selfTestAnswerDAO) {
            this.selfTestAnswerDAO = selfTestAnswerDAO;
        }

        @Override
        protected Void doInBackground(SelfTestAnswer... selfTestAnswers) {
            selfTestAnswerDAO.delete(selfTestAnswers[0]);
            return null;
        }
    }

    public static SelfTestRepository getINSTANCE(SelfTestAnswerDAO selfTestAnswerDAO) {
        if (INSTANCE == null) {
            synchronized (SelfTestRepository.class) {
                INSTANCE = new SelfTestRepository(selfTestAnswerDAO);
            }
        }
        return INSTANCE;
    }
}
