package com.skybox.seven.covid.ui;

import android.os.Bundle;
import android.widget.Button;

import com.skybox.seven.covid.R;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button button1 = findViewById(R.id.contactTracing);
    }
}
