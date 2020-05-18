package com.skybox.seven.covid.repository;

import com.skybox.seven.covid.data.entities.Myth;
import com.skybox.seven.covid.model.MythGraphicInfo;

import java.util.ArrayList;
import java.util.List;

public class MythRepository {
    private List<MythGraphicInfo>mythGraphicInfoList = new ArrayList<>();
    private List<Myth>mythList = new ArrayList<>();

    public MythRepository() {
        setUpMyth();
        setUpMythGraphicInfo();
    }

    private void setUpMyth() {
        List<String> ask = new ArrayList<>();
        ask.add("https://www.who.int/images/default-source/health-topics/coronavirus/risk-communications/general-public/safe-greetings.tmb-479v.png?sfvrsn=2e97004e_4");
        ask.add("https://www.who.int/images/default-source/health-topics/coronavirus/risk-communications/general-public/handshaking.tmb-479v.png?sfvrsn=4aed53c5_4");
        ask.add("https://www.who.int/images/default-source/health-topics/coronavirus/risk-communications/general-public/wearing-gloves.tmb-479v.png?sfvrsn=ec69b46a_4");
        ask.add("https://www.who.int/images/default-source/health-topics/coronavirus/eng-mythbusting-ncov-(19).tmb-479v.png?sfvrsn=99db25de_1");
        ask.add("https://www.who.int/images/default-source/health-topics/coronavirus/eng-mythbusting-ncov-(13).tmb-479v.png?sfvrsn=d2a2dc01_1");
        ask.add("https://www.who.int/images/default-source/health-topics/coronavirus/eng-mythbusting-ncov-(23).tmb-479v.png?sfvrsn=b399c676_1");
        mythGraphicInfoList.add(new MythGraphicInfo("Ask WHO", ask));
    }

    private void setUpMythGraphicInfo() {
        mythList.add(new Myth("5G mobile networks DO NOT spread COVID-19",
                "5G mobile networks DO NOT spread COVID-19",
                "Viruses cannot travel on radio waves/mobile networks. COVID-19 is spreading in many countries that do" +
                        " not have 5G mobile networks COVID-19 is spread through respiratory droplets when an infected person coughs, " +
                        "sneezes or speaks. People can also be infected by touching a contaminated surface and then their eyes, mouth  " +
                        "or nose."));
        mythList.add(new Myth("Exposing yourself to the sun or to temperatures higher than 25C degrees DOES NOT prevent the coronavirus disease (COVID-19)",
                "Exposing yourself to the sun or to temperatures higher than 25C degrees DOES NOT prevent the coronavirus disease (COVID-19)",
                "You can catch COVID-19, no matter how sunny or hot the weather is. Countries with hot weather have reported cases of COVID-19. To protect yourself," +
                        " make sure you clean your hands frequently and thoroughly and avoid touching your eyes, mouth, and nose. "));
        mythList.add(new Myth("You can recover from the coronavirus disease (COVID-19). Catching the new coronavirus DOES NOT mean you" +
                " will have it for life.",
                "You can recover from the coronavirus disease (COVID-19). Catching the new coronavirus DOES NOT mean you" +
                        " will have it for life.",
                "Most of the people who catch COVID-19 can recover and eliminate the virus from their bodies. If you" +
                        " catch the disease, make sure you treat your symptoms. If you have cough, fever, and difficulty breathing, " +
                        "seek medical care early – but call your health facility by telephone first. Most patients recover thanks to " +
                        "supportive care."));
        mythList.add(new Myth("Being able to hold your breath for 10 seconds or more without coughing or feeling discomfort DOES" +
                " NOT mean you are free from the coronavirus disease (COVID-19) or any other lung disease.",
                "Being able to hold your breath for 10 seconds or more without coughing or feeling discomfort DOES" +
                        " NOT mean you are free from the coronavirus disease (COVID-19) or any other lung disease.",
                "The most common symptoms of COVID-19 are dry cough, tiredness and fever. Some people may " +
                        "develop more severe forms of the disease, such as pneumonia. The best way to confirm if you have  the" +
                        " virus producing COVID-19 disease is with a laboratory test.  You cannot confirm it with this breathing" +
                        " exercise, which can even be dangerous"));
        mythList.add(new Myth("Drinking alcohol does not protect you against COVID-19 and can be dangerous",
                "Drinking alcohol does not protect you against COVID-19 and can be dangerous",
                "Frequent or excessive alcohol consumption can increase your risk of health problems."));
        mythList.add(new Myth("COVID-19 virus can be transmitted in areas with hot and humid climates",
                "COVID-19 virus can be transmitted in areas with hot and humid climates",
                "From the evidence so far, the COVID-19 virus can be transmitted in ALL AREAS, including areas with hot and humid weather. " +
                        "Regardless of climate, adopt protective measures if you live in, or travel to an area reporting COVID-19. The best way to protect yourself " +
                        "against COVID-19 is by frequently cleaning your hands. By doing this you eliminate viruses that may be on your hands and avoid infection that " +
                        "could occur by then touching your eyes, mouth, and nose."));
    }

    public List<MythGraphicInfo> getMythGraphicInfoList() {
        return mythGraphicInfoList;
    }

    public void setMythGraphicInfoList(List<MythGraphicInfo> mythGraphicInfoList) {
        this.mythGraphicInfoList = mythGraphicInfoList;
    }

    public List<Myth> getMythList() {
        return mythList;
    }

    public void setMythList(List<Myth> mythList) {
        this.mythList = mythList;
    }
}
