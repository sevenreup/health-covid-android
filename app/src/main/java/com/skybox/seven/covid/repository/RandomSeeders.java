package com.skybox.seven.covid.repository;

import com.skybox.seven.covid.R;
import com.skybox.seven.covid.data.entities.Advice;
import com.skybox.seven.covid.data.entities.Language;
import com.skybox.seven.covid.data.entities.Myth;

import java.util.ArrayList;
import java.util.List;

public class RandomSeeders {
    public static List<Language> setUpLanguages() {
        List<Language> languages = new ArrayList<>();
        languages.add(new Language(1,"en", "US"));
        languages.add(new Language(2,"ny", "MW"));
        return languages;
    }
    public static List<String> setUpInfoGraphic() {
        List<String> ask = new ArrayList<>();
        ask.add("https://www.who.int/images/default-source/health-topics/coronavirus/risk-communications/general-public/safe-greetings.tmb-479v.png?sfvrsn=2e97004e_4");
        ask.add("https://www.who.int/images/default-source/health-topics/coronavirus/risk-communications/general-public/handshaking.tmb-479v.png?sfvrsn=4aed53c5_4");
        ask.add("https://www.who.int/images/default-source/health-topics/coronavirus/risk-communications/general-public/wearing-gloves.tmb-479v.png?sfvrsn=ec69b46a_4");
        ask.add("https://www.who.int/images/default-source/health-topics/coronavirus/eng-mythbusting-ncov-(19).tmb-479v.png?sfvrsn=99db25de_1");
        ask.add("https://www.who.int/images/default-source/health-topics/coronavirus/eng-mythbusting-ncov-(13).tmb-479v.png?sfvrsn=d2a2dc01_1");
        ask.add("https://www.who.int/images/default-source/health-topics/coronavirus/eng-mythbusting-ncov-(23).tmb-479v.png?sfvrsn=b399c676_1");
        return ask;
    }

    public static List<Advice> setUpAdvice() {
        List<Advice> adviceList = new ArrayList<>();
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
        return adviceList;
    }
    public static List<String> setUpMyth() {
        List<String> ask = new ArrayList<>();
        ask.add("https://www.who.int/images/default-source/health-topics/coronavirus/risk-communications/general-public/safe-greetings.tmb-479v.png?sfvrsn=2e97004e_4");
        ask.add("https://www.who.int/images/default-source/health-topics/coronavirus/risk-communications/general-public/handshaking.tmb-479v.png?sfvrsn=4aed53c5_4");
        ask.add("https://www.who.int/images/default-source/health-topics/coronavirus/risk-communications/general-public/wearing-gloves.tmb-479v.png?sfvrsn=ec69b46a_4");
        ask.add("https://www.who.int/images/default-source/health-topics/coronavirus/eng-mythbusting-ncov-(19).tmb-479v.png?sfvrsn=99db25de_1");
        ask.add("https://www.who.int/images/default-source/health-topics/coronavirus/eng-mythbusting-ncov-(13).tmb-479v.png?sfvrsn=d2a2dc01_1");
        ask.add("https://www.who.int/images/default-source/health-topics/coronavirus/eng-mythbusting-ncov-(23).tmb-479v.png?sfvrsn=b399c676_1");
        return ask;
    }

    public static List<Myth> setUpMythGraphicInfo() {
        List<Myth> mythList = new ArrayList<>();
        mythList.add(new Myth(1,1,"5G mobile networks DO NOT spread COVID-19",
                "5G mobile networks DO NOT spread COVID-19",
                "Viruses cannot travel on radio waves/mobile networks. COVID-19 is spreading in many countries that do" +
                        " not have 5G mobile networks COVID-19 is spread through respiratory droplets when an infected person coughs, " +
                        "sneezes or speaks. People can also be infected by touching a contaminated surface and then their eyes, mouth  " +
                        "or nose."));
        mythList.add(new Myth(2,1,"Exposing yourself to the sun or to temperatures higher than 25C degrees DOES NOT prevent the coronavirus disease (COVID-19)",
                "Exposing yourself to the sun or to temperatures higher than 25C degrees DOES NOT prevent the coronavirus disease (COVID-19)",
                "You can catch COVID-19, no matter how sunny or hot the weather is. Countries with hot weather have reported cases of COVID-19. To protect yourself," +
                        " make sure you clean your hands frequently and thoroughly and avoid touching your eyes, mouth, and nose. "));
        mythList.add(new Myth(3,1,"You can recover from the coronavirus disease (COVID-19). Catching the new coronavirus DOES NOT mean you" +
                " will have it for life.",
                "You can recover from the coronavirus disease (COVID-19). Catching the new coronavirus DOES NOT mean you" +
                        " will have it for life.",
                "Most of the people who catch COVID-19 can recover and eliminate the virus from their bodies. If you" +
                        " catch the disease, make sure you treat your symptoms. If you have cough, fever, and difficulty breathing, " +
                        "seek medical care early – but call your health facility by telephone first. Most patients recover thanks to " +
                        "supportive care."));
        mythList.add(new Myth(4,1,"Being able to hold your breath for 10 seconds or more without coughing or feeling discomfort DOES" +
                " NOT mean you are free from the coronavirus disease (COVID-19) or any other lung disease.",
                "Being able to hold your breath for 10 seconds or more without coughing or feeling discomfort DOES" +
                        " NOT mean you are free from the coronavirus disease (COVID-19) or any other lung disease.",
                "The most common symptoms of COVID-19 are dry cough, tiredness and fever. Some people may " +
                        "develop more severe forms of the disease, such as pneumonia. The best way to confirm if you have  the" +
                        " virus producing COVID-19 disease is with a laboratory test.  You cannot confirm it with this breathing" +
                        " exercise, which can even be dangerous"));
        mythList.add(new Myth(5,1,"Drinking alcohol does not protect you against COVID-19 and can be dangerous",
                "Drinking alcohol does not protect you against COVID-19 and can be dangerous",
                "Frequent or excessive alcohol consumption can increase your risk of health problems."));
        mythList.add(new Myth(6,1,"COVID-19 virus can be transmitted in areas with hot and humid climates",
                "COVID-19 virus can be transmitted in areas with hot and humid climates",
                "From the evidence so far, the COVID-19 virus can be transmitted in ALL AREAS, including areas with hot and humid weather. " +
                        "Regardless of climate, adopt protective measures if you live in, or travel to an area reporting COVID-19. The best way to protect yourself " +
                        "against COVID-19 is by frequently cleaning your hands. By doing this you eliminate viruses that may be on your hands and avoid infection that " +
                        "could occur by then touching your eyes, mouth, and nose."));
        return mythList;
    }
}
