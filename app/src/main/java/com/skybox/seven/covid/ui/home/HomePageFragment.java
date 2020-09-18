package com.skybox.seven.covid.ui.home;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;
import com.skybox.seven.covid.R;
import com.skybox.seven.covid.databinding.FragmentHomepageBinding;

import java.util.Calendar;

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

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        setGreetings();
    }

    public void setGreetings(){

        Calendar cal = Calendar.getInstance();

        int timeOfDay = cal.get(Calendar.HOUR_OF_DAY);

        if (timeOfDay < 12){
            binding.greetingView.setText(getResources().getText(R.string.good_morning));
        }else if (timeOfDay < 16){
            binding.greetingView.setText(getResources().getText(R.string.good_afternoon));
        }else if (timeOfDay < 21){
            binding.greetingView.setText(getResources().getText(R.string.good_evening));
        } else {
        binding.greetingView.setText(getResources().getText(R.string.good_night));
    }
    }

    public class ClickHandlers{
        Context context;

        public ClickHandlers(Context context) {
            this.context = context;
        }

        public void detailsClick(View view){
            Navigation.findNavController(requireActivity(), R.id.nav_host_fragment).navigate(R.id.action_homePageFragment_to_detailsFragment);
        }

        public void onCardClick(View view)
        {
            switch (view.getId())
            {
                case R.id.preventionCard:
                    Navigation.findNavController(requireActivity(), R.id.nav_host_fragment).navigate(R.id.preventionFragment);
                break;

                case R.id.symptomsCard:
                    Navigation.findNavController(getActivity(), R.id.nav_host_fragment).navigate(R.id.symptomsFragment);
                break;

                case R.id.mythCard:
                    Navigation.findNavController(requireActivity(), R.id.nav_host_fragment).navigate(R.id.mythBustersFragment);
                    break;

                case R.id.qnaCard:
                    Navigation.findNavController(requireActivity(), R.id.nav_host_fragment).navigate(R.id.to_qna);
                    break;

                default:
                    Toast.makeText(requireActivity(), "Error in a card", Toast.LENGTH_SHORT).show();
                    break;


            }
        }

    }
}


