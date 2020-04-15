package com.skybox.seven.covid.ui;

import android.os.Bundle;
import android.widget.RelativeLayout;

import com.google.android.material.bottomsheet.BottomSheetDialog;
import com.skybox.seven.covid.R;

import androidx.appcompat.app.AppCompatActivity;

public class ContactActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contact);

        RelativeLayout addPersonView = findViewById(R.id.addPerson);

        addPersonView.setOnClickListener(v -> {
            BottomSheetDialog bottomDialog = new BottomSheetDialog(this);
            
        });
    }
}
