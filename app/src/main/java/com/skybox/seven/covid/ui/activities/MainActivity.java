package com.skybox.seven.covid.ui.activities;

import android.os.Bundle;
import android.widget.Button;

import com.akexorcist.localizationactivity.ui.LocalizationActivity;
import com.skybox.seven.covid.R;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends LocalizationActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

//        Button button1 = findViewById(R.id.contactTracing);
    }
}
