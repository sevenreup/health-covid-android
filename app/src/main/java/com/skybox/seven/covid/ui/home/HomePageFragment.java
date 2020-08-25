package com.skybox.seven.covid.ui.home;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.cardview.widget.CardView;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import com.skybox.seven.covid.R;
import com.skybox.seven.covid.databinding.FragmentHomepageBinding;

public class HomePageFragment extends Fragment {

    private FragmentHomepageBinding fragmentHomepageBinding;
    private ClickHandlers handlers;

    public HomePageFragment() {
    }



    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_homepage, container, false);
        fragmentHomepageBinding = DataBindingUtil.setContentView(getActivity(),R.layout.fragment_homepage);

            handlers=new ClickHandlers(getContext());
            fragmentHomepageBinding.setClickHandler(handlers);

        return v;
    }



    public class ClickHandlers{
        Context context;

        public ClickHandlers(Context context) {
            this.context = context;
        }

        public void onCardClick(View view)
        {
            switch (view.getId())
            {
                case R.id.preventionCard:
                    Navigation.findNavController(getActivity(), R.id.nav_host_fragment).navigate(R.id.preventionFragment);
                break;

                case R.id.symptomsCard:
                    Toast.makeText(getActivity(), "Symptoms Card has been clicked", Toast.LENGTH_SHORT).show();
            }
        }

    }
}


