package com.skybox.seven.covid.viewmodels;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.skybox.seven.covid.model.Advice;
import com.skybox.seven.covid.model.InfoGraphic;
import com.skybox.seven.covid.model.Myth;
import com.skybox.seven.covid.model.MythGraphicInfo;
import com.skybox.seven.covid.model.TipsChips;
import com.skybox.seven.covid.repository.HealthRepository;
import com.skybox.seven.covid.repository.MythRepository;

import java.util.List;

public class TipsViewModel extends AndroidViewModel {
    public MutableLiveData<List<Myth>> mythList = new MutableLiveData<>();
    public MutableLiveData<List<MythGraphicInfo>> mythGraphicInfoList = new MutableLiveData<>();

    public MutableLiveData<Advice> activeAdvice = new MutableLiveData<>();
    public MutableLiveData<String> activeInfoGraphic = new MutableLiveData<>();
    public MutableLiveData<TipsChips> activeChip = new MutableLiveData<>(TipsChips.advice);

    public MutableLiveData<List<Advice>> adviceList = new MutableLiveData<>();
    public MutableLiveData<List<InfoGraphic>> infoGraphicList = new MutableLiveData<>();

    private HealthRepository healthRepository = new HealthRepository();

    private MythRepository mythRepository = new MythRepository();

    public TipsViewModel(@NonNull Application application) {
        super(application);
    }

    public void getMythList() {
        mythList.setValue(mythRepository.getMythList());
    }

    public void getMythGraphicList() {
        mythGraphicInfoList.setValue(mythRepository.getMythGraphicInfoList());
    }

    public void getAdviceList() {
        adviceList.setValue(healthRepository.getAdviceList());
    }

    public void getInfoGraphicList() {
        infoGraphicList.setValue(healthRepository.getInfoGraphicList());
    }
}
