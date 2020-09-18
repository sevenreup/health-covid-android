package com.skybox.seven.covid.ui.symptoms;

import android.content.Context;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.skybox.seven.covid.R;
import com.skybox.seven.covid.databinding.FragmentHomepageBinding;
import com.skybox.seven.covid.databinding.FragmentSymptomsBinding;
import com.skybox.seven.covid.ui.home.HomePageFragment;


public class SymptomsFragment extends Fragment {
    private FragmentSymptomsBinding binding;
    private SymptomsFragment.ClickHandlers handlers;

    public SymptomsFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        binding = FragmentSymptomsBinding.inflate(inflater, container, false);
        handlers=new SymptomsFragment.ClickHandlers(getContext());
        binding.setClickHandler(handlers);
        return binding.getRoot(); }


    public class ClickHandlers{
        Context context;

        public ClickHandlers(Context context) {
            this.context = context;
        }

        public void onBackBtnClick(View view){
            Navigation.findNavController(requireActivity(), R.id.nav_host_fragment).navigateUp();
        }
    }


}