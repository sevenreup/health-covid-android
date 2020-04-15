package com.skybox.seven.covid.ui;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.skybox.seven.covid.R;


public class UiTestActivity extends AppCompatActivity {
    EditText userName;
    EditText userNumber;
    Button loginButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        userName = findViewById(R.id.userName);
        userNumber = findViewById(R.id.userNumber);
        loginButton = findViewById(R.id.loginButton);

        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String userNameM = userName.getText().toString();
                String userNumberM = userNumber.getText().toString();
                Intent intent = new Intent(UiTestActivity.this, HomeActivity.class);
                intent.putExtra(HomeActivity.NAME_MESSAGE,userNameM);
                intent.putExtra(HomeActivity.PHONE_MESSAGE, userNumberM);
                startActivity(intent);
            }
        });
    }
}
