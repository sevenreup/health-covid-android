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
        setUpAdvice();
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

    private void setUpAdvice() {
        adviceList.add(new Advice("Wash your hands", "Regularly and thoroughly clean your hands with an alcohol-based hand rub or wash them with soap and water.",
                "Washing your hands with soap and water or using alcohol-based hand rub kills viruses that may be on your hands.", R.drawable.ic_wash_hands));
        adviceList.add(new Advice("Social distancing","Maintain at least 1 metre (3 feet) distance between yourself and others.",
                "When someone coughs, sneezes, or speaks they spray small liquid droplets from their nose or mouth which may contain virus. If you are too close, you can breathe in the droplets, including the COVID-19 virus if the person has the disease.",
                R.drawable.ic_wash_hands));
        adviceList.add(new Advice("Avoid going to crowded places.", "Avoid going to crowded places.",
                "Where people come together in crowds, you are more likely to come into close contact with someone that has COIVD-19 and it is more difficult to maintain physical distance of 1 metre (3 feet).",
                R.drawable.ic_wash_hands));
        adviceList.add(new Advice("Avoid touching eyes, nose and mouth.","Avoid touching eyes, nose and mouth.",
                "Hands touch many surfaces and can pick up viruses. Once contaminated, hands can transfer the virus to your eyes, nose or mouth. From there, the virus can enter your body and infect you..",
                R.drawable.ic_wash_hands));
        adviceList.add(new Advice("Make sure you, and the people around you, follow good respiratory hygiene.",
                "Make sure you, and the people around you, follow good respiratory hygiene. This means covering your mouth and nose with your bent elbow or tissue when you cough or sneeze. Then dispose of the used tissue immediately and wash your hands",
                "Droplets spread virus. By following good respiratory hygiene, you protect the people around you from viruses such as cold, flu and COVID-19.",
                R.drawable.ic_wash_hands));
        adviceList.add(new Advice("Stay home and self-isolate",
                "Stay home and self-isolate even with minor symptoms such as cough, headache, mild fever, until you recover. Have someone bring you supplies. If you need to leave your house, wear a mask to avoid infecting others.",
                "Avoiding contact with others will protect them from possible COVID-19 and other viruses.",
                R.drawable.ic_wash_hands));
        adviceList.add(new Advice("seek medical attention",
                "If you have a fever, cough and difficulty breathing, seek medical attention, but call by telephone in advance if possible and follow the directions of your local health authority.",
                "National and local authorities will have the most up to date information on the situation in your area. Calling in advance will allow your health care provider to quickly direct you to the right health facility. This will also protect you and help prevent spread of viruses and other infections.",
                R.drawable.ic_wash_hands));
        adviceList.add(new Advice("Keep up to date",
                "Keep up to date on the latest information from trusted sources, such as WHO or your local and national health authorities.",
                "Local and national authorities are best placed to advise on what people in your area should be doing to protect themselves.",
                R.drawable.ic_wash_hands));
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
