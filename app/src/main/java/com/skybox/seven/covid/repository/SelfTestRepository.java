package com.skybox.seven.covid.repository;

import android.os.AsyncTask;

import com.skybox.seven.covid.data.SelfTestResult;
import com.skybox.seven.covid.data.SelfTestResultDAO;

import java.util.List;

public class SelfTestRepository {
    private static SelfTestRepository INSTANCE = null;
    private SelfTestResultDAO resultDAO;

    public SelfTestRepository(SelfTestResultDAO resultDAO) {
        this.resultDAO = resultDAO;
    }

    public void addResults(SelfTestResult result) {
        resultDAO.insertAll(result);
    }

    public List<SelfTestResult> getAllTests() {
       return resultDAO.getAll();
    }

    public void deleteTest(SelfTestResult result) {
        new deleteTestAsync(resultDAO).execute(result);
    }

    private static class deleteTestAsync extends AsyncTask<SelfTestResult, Void, Void> {
        private SelfTestResultDAO selfTestResultDAO;

        public deleteTestAsync(SelfTestResultDAO selfTestResultDAO) {
            this.selfTestResultDAO = selfTestResultDAO;
        }

        @Override
        protected Void doInBackground(SelfTestResult... selfTestResults) {
            selfTestResultDAO.delete(selfTestResults[0]);
            return null;
        }
    }

    public static SelfTestRepository getINSTANCE(SelfTestResultDAO selfTestResultDAO) {
        if (INSTANCE == null) {
            synchronized (SelfTestRepository.class) {
                INSTANCE = new SelfTestRepository(selfTestResultDAO);
            }
        }
        return INSTANCE;
    }
}
