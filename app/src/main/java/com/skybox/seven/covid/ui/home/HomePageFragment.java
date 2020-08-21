package com.skybox.seven.covid.ui.home;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import com.skybox.seven.covid.R;

public class HomePageFragment extends Fragment {

    public   CardView cardView;

    public HomePageFragment() {
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_homepage, container, false);

        cardView = cardView.findViewById(R.id.preventionCard);
        cardView.setOnClickListener(cardOnClickListener);

        return v;
    }


    private View.OnClickListener cardOnClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            cardClicked();
        }
    };

    private void cardClicked(){
        Navigation.findNavController(getActivity(), R.id.nav_host_fragment).navigate(R.id.preventionFragment);
    }

}


