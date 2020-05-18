package com.skybox.seven.covid.viewmodels;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.skybox.seven.covid.data.entities.SelfTestResult;
import com.skybox.seven.covid.repository.SelfTestRepository;

import java.util.List;

public class SelfTestViewModel extends ViewModel {
    private SelfTestRepository selfTestRepository;
    public MutableLiveData<List<SelfTestResult>> selfTestLiveData = new MutableLiveData<List<SelfTestResult>>();

    public SelfTestViewModel(SelfTestRepository selfTestRepository) {
        this.selfTestRepository = selfTestRepository;
    }

    public void getAllTests() {
        selfTestLiveData.setValue(selfTestRepository.getAllTests());
    }

    public void deleteTest(SelfTestResult result) {
        selfTestRepository.deleteTest(result);
    }

    public void addTest(SelfTestResult result) {
        selfTestRepository.addResults(result);
    }
}
