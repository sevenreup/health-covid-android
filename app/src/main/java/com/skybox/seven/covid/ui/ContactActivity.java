package com.skybox.seven.covid.ui;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.google.android.material.bottomsheet.BottomSheetDialog;
import com.skybox.seven.covid.R;
import com.skybox.seven.covid.model.FamMember;

import java.util.ArrayList;

import androidx.appcompat.app.AppCompatActivity;

public class ContactActivity extends AppCompatActivity {

    LayoutInflater inflater;
    LinearLayout membersView;
    ArrayList<FamMember> members = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contact);

        membersView = findViewById(R.id.membersParent);
        RelativeLayout addPersonView = findViewById(R.id.addPerson);
        RelativeLayout addLocView = findViewById(R.id.addLocation);
        inflater = LayoutInflater.from(this);

        addPersonView.setOnClickListener(v -> {
            BottomSheetDialog bottomDialog = new BottomSheetDialog(this);
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

        addLocView.setOnClickListener(v -> {
            Intent intent = new Intent(this,Location.class);
            startActivity(intent);
        });

        FamMember member = new FamMember("Mijiga", "0998530227");
        members.add(member);

        setupFamily();
    }

    public void setupFamily() {
        membersView.removeAllViews();
        for (FamMember member : members) {
            View view = inflater.inflate(R.layout.member_item,null);
            ((TextView)view.findViewById(R.id.name)).setText(member.getName());
            membersView.addView(view);
        }
    }
}
