package com.skybox.seven.covid.repository;

import com.skybox.seven.covid.R;
import com.skybox.seven.covid.data.entities.Advice;
import com.skybox.seven.covid.data.entities.InfoGraphic;

import java.util.ArrayList;
import java.util.List;

public class HealthRepository {
    private List<InfoGraphic> infoGraphicList = new ArrayList<>();
    private List<Advice> adviceList = new ArrayList<>();

    public HealthRepository() {
        setUpInfoGraphic();
    }

    private void setUpInfoGraphic() {
        List<String> ask = new ArrayList<>();
        ask.add("https://www.who.int/images/default-source/health-topics/coronavirus/risk-communications/general-public/safe-greetings.tmb-479v.png?sfvrsn=2e97004e_4");
        ask.add("https://www.who.int/images/default-source/health-topics/coronavirus/risk-communications/general-public/handshaking.tmb-479v.png?sfvrsn=4aed53c5_4");
        ask.add("https://www.who.int/images/default-source/health-topics/coronavirus/risk-communications/general-public/wearing-gloves.tmb-479v.png?sfvrsn=ec69b46a_4");
        ask.add("https://www.who.int/images/default-source/health-topics/coronavirus/eng-mythbusting-ncov-(19).tmb-479v.png?sfvrsn=99db25de_1");
        ask.add("https://www.who.int/images/default-source/health-topics/coronavirus/eng-mythbusting-ncov-(13).tmb-479v.png?sfvrsn=d2a2dc01_1");
        ask.add("https://www.who.int/images/default-source/health-topics/coronavirus/eng-mythbusting-ncov-(23).tmb-479v.png?sfvrsn=b399c676_1");
        infoGraphicList.add(new InfoGraphic("Ask WHO", ask));
    }

    public List<InfoGraphic> getInfoGraphicList() {
        return infoGraphicList;
    }

    public void setInfoGraphicList(List<InfoGraphic> infoGraphicList) {
        this.infoGraphicList = infoGraphicList;
    }

    public List<Advice> getAdviceList() {
        return adviceList;
    }

    public void setAdviceList(List<Advice> adviceList) {
        this.adviceList = adviceList;
    }
}
