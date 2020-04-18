package com.skybox.seven.covid.ui.fragment.main;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.skybox.seven.covid.R;
import com.skybox.seven.covid.ui.adapters.MythAdapter;
import com.skybox.seven.covid.ui.adapters.MythModel;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 */
public class MythsFragment extends Fragment {
    private RecyclerView mythRecycler;
    private RecyclerView.Adapter MythAdapter;
    private LinearLayoutManager layoutManager;

    public MythsFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_myths, container, false);
        mythRecycler = v.findViewById(R.id.mythRecycler);
        layoutManager =new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false);
        MythAdapter = new MythAdapter(getMyMyths());
        mythRecycler.setLayoutManager(layoutManager);
        mythRecycler.setAdapter(MythAdapter);

  return v;  }

    private ArrayList<MythModel> getMyMyths() {
        ArrayList<MythModel> models = new ArrayList<>();
        MythModel m = new MythModel();
        m.setTitle("5G mobile networks DO NOT spread COVID-19");
        m.setParagraph("Viruses cannot travel on radio waves/mobile networks. COVID-19 is spreading in many countries that do" +
                " not have 5G mobile networks COVID-19 is spread through respiratory droplets when an infected person coughs, " +
                "sneezes or speaks. People can also be infected by touching a contaminated surface and then their eyes, mouth " +
                "or nose.");
        models.add(m);

        m = new MythModel();
        m.setTitle("Exposing yourself to the sun or to temperatures higher than 25C degrees DOES NOT prevent the coronavirus disease (COVID-19)");
        m.setParagraph("You can catch COVID-19, no matter how sunny or hot the weather is. Countries with hot weather have reported cases of COVID-19. To protect yourself, make sure you clean your hands frequently and thoroughly and avoid touching your eyes, mouth, and nose. ");
        models.add(m);

        m = new MythModel();
        m.setTitle("You can recover from the coronavirus disease (COVID-19). Catching the new coronavirus DOES NOT mean you" +
                " will have it for life.");
        m.setParagraph("Most of the people who catch COVID-19 can recover and eliminate the virus from their bodies. If you" +
                " catch the disease, make sure you treat your symptoms. If you have cough, fever, and difficulty breathing, " +
                "seek medical care early – but call your health facility by telephone first. Most patients recover thanks to " +
                "supportive care.");
        models.add(m);

        m = new MythModel();
        m.setTitle("Being able to hold your breath for 10 seconds or more without coughing or feeling discomfort DOES" +
                " NOT mean you are free from the coronavirus disease (COVID-19) or any other lung disease.");
        m.setParagraph("The most common symptoms of COVID-19 are dry cough, tiredness and fever. Some people may " +
                "develop more severe forms of the disease, such as pneumonia. The best way to confirm if you have  the" +
                " virus producing COVID-19 disease is with a laboratory test.  You cannot confirm it with this breathing" +
                " exercise, which can even be dangerous");
        models.add(m);

        m = new MythModel();
        m.setTitle("Drinking alcohol does not protect you against COVID-19 and can be dangerous");
        m.setParagraph("Frequent or excessive alcohol consumption can increase your risk of health problems.");
        models.add(m);

        m = new MythModel();
        m.setTitle("COVID-19 virus can be transmitted in areas with hot and humid climates");
        m.setParagraph("From the evidence so far, the COVID-19 virus can be transmitted in ALL AREAS, including areas" +
                " with hot and humid weather. Regardless of climate, adopt protective measures if you live in, or travel to " +
                "an area reporting COVID-19. The best way to protect yourself against COVID-19 is by frequently cleaning your" +
                " hands. By doing this you eliminate viruses that may be on your hands and avoid infection that could occur by then touching your eyes, mouth, and nose.");
        models.add(m);

        return models;
    }
}
