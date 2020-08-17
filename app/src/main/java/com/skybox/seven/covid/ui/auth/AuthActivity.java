package com.skybox.seven.covid.ui.auth;

import android.content.Context;
import android.content.res.Resources;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;

import com.akexorcist.localizationactivity.core.LocalizationActivityDelegate;
import com.akexorcist.localizationactivity.core.OnLocaleChangedListener;
import com.skybox.seven.covid.R;


public class AuthActivity extends AppCompatActivity  implements OnLocaleChangedListener {
    private LocalizationActivityDelegate delegate = new LocalizationActivityDelegate(this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_auth);
        Bundle extras = getIntent().getExtras();

        if (extras != null) {
            boolean register = extras.getBoolean("register");
            if (register) {
                RegisterFragment registerFragment = new RegisterFragment();
                FragmentManager fm = getSupportFragmentManager();

                fm.beginTransaction().add(R.id.mainlayout, registerFragment).commit();
            } else {
                LoginFragment loginFragment = new LoginFragment();
                FragmentManager fm = getSupportFragmentManager();

                fm.beginTransaction().add(R.id.mainlayout, loginFragment).commit();
            }
        } else {
            LoginFragment loginFragment = new LoginFragment();
            FragmentManager fm = getSupportFragmentManager();

            fm.beginTransaction().add(R.id.mainlayout, loginFragment).commit();
        }
    }


    @Override
    protected void onResume() {
        super.onResume();
        delegate.onResume(this);
    }

    @Override
    protected void attachBaseContext(Context newBase) {
        super.attachBaseContext(delegate.attachBaseContext(newBase));
    }

    @Override
    public Context getApplicationContext() {
        return delegate.getApplicationContext(super.getApplicationContext());
    }

    @Override
    public Resources getResources() {
        return delegate.getResources(super.getResources());
    }

    @Override
    public void onAfterLocaleChanged() {

    }

    @Override
    public void onBeforeLocaleChanged() {

    }
}
