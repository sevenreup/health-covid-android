package com.skybox.seven.covid.ui.fragment.main;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.google.android.gms.maps.model.LatLng;
import com.google.android.material.bottomsheet.BottomSheetDialog;
import com.skybox.seven.covid.R;
import com.skybox.seven.covid.model.FamMember;
import com.skybox.seven.covid.network.RetrofitFactory;
import com.skybox.seven.covid.network.RetrofitService;
import com.skybox.seven.covid.ui.Location;

import java.util.ArrayList;

import androidx.fragment.app.Fragment;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

/**
 * A simple {@link Fragment} subclass.
 */
public class ContactCreateFragment extends Fragment {
    LayoutInflater inflater;
    LinearLayout membersView;
    ArrayList<FamMember> members = new ArrayList<>();

    LatLng userLocation;

    public ContactCreateFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_contact_trace, container, false);
        membersView = v.findViewById(R.id.membersParent);
        RelativeLayout addPersonView = v.findViewById(R.id.addPerson);
        RelativeLayout addLocView = v.findViewById(R.id.addLocation);
        Button saveButton = v.findViewById(R.id.saveChanges);

        this.inflater = inflater;

        addPersonView.setOnClickListener(v1 -> {
            BottomSheetDialog bottomDialog = new BottomSheetDialog(getContext());
            View view = inflater.inflate(R.layout.person_dialog,null);

            ((Button)view.findViewById(R.id.saveMember)).setOnClickListener(vw->{
                String name = String.valueOf(((EditText)view.findViewById(R.id.userName)).getText());
                String phoneNumber = String.valueOf(((EditText)view.findViewById(R.id.phoneNumber)).getText());
                FamMember famMember = new FamMember(name,phoneNumber);
                members.add(famMember);
                setupFamily();
                bottomDialog.dismiss();
            });

            bottomDialog.setContentView(view);
            bottomDialog.show();
        });

        addLocView.setOnClickListener(v1 -> {
            Intent intent = new Intent(getActivity(), Location.class);
            startActivity(intent);
        });

        saveButton.setOnClickListener(v1 -> {
            Retrofit retrofit = RetrofitFactory.getRetrofit();
            RetrofitService service = retrofit.create(RetrofitService.class);
            service.saveContacts(members,userLocation).enqueue(new Callback<String>() {
                @Override
                public void onResponse(Call<String> call, Response<String> response) {
                    System.out.println(response+"wellooooo");
                }

                @Override
                public void onFailure(Call<String> call, Throwable t) {
                    System.out.println(call+"well damn");
                }
            });
        });


        //FamMember member = new FamMember("Mijiga", "0998530227");
        //members.add(member);

        setupFamily();

    return v;
    }

    private void setupFamily() {
        membersView.removeAllViews();
        for (FamMember member : members) {
            View view = inflater.inflate(R.layout.member_item,null);
            ((TextView)view.findViewById(R.id.name)).setText(member.getName());
            membersView.addView(view);
        }
    }
}
