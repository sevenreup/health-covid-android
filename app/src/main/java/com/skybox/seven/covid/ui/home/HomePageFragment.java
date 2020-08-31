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

import java.util.Objects;

public class HomePageFragment extends Fragment {

    private FragmentHomepageBinding binding;
    private ClickHandlers handlers;

    public HomePageFragment() {
    }



    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = FragmentHomepageBinding.inflate(inflater, container, false);
        handlers=new ClickHandlers(getContext());
        binding.setClickHandler(handlers);
        return binding.getRoot();
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
                    Navigation.findNavController(Objects.requireNonNull(getActivity()), R.id.nav_host_fragment).navigate(R.id.preventionFragment);
                break;

               

                case R.id.symptomsCard:
                    Toast.makeText(getActivity(), "Symptoms Card has been clicked", Toast.LENGTH_SHORT).show();

                    break;

                case R.id.qnaCard:
                    Navigation.findNavController(Objects.requireNonNull(getActivity()), R.id.nav_host_fragment).navigate(R.id.to_qnaFragment);
                    break;

                default:
                    Toast.makeText(getActivity(), "Error in a card", Toast.LENGTH_SHORT).show();
                    break;
                break;

                case R.id.mythCard:
                    Navigation.findNavController(getActivity(), R.id.nav_host_fragment).navigate(R.id.mythBustersFragment);

            }
        }

    }
}


