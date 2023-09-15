package com.example.touristnotes;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;

public class UserSettingsActivity extends AppCompatActivity {
    public static final String APP_PREFERENCES = "UserLoginSP";
    public static final String APP_PREFERENCES_NAME = "Login";
    public static final String APP_PREFERENCES_PASSWORD = "Password";
    SharedPreferences UserSP;
    Intent goto_login = new Intent();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.user_settings);
    }

    public void LogoutClick(View view) {
        //Очистка SharedPreferences
        UserSP = getSharedPreferences(APP_PREFERENCES, Context.MODE_PRIVATE);
        @SuppressLint("CommitPrefEdits")
        SharedPreferences.Editor editor = UserSP.edit();
        editor.putString(APP_PREFERENCES_NAME, null);
        editor.putString(APP_PREFERENCES_PASSWORD, null);
        editor.apply();
        //Переход на окно Логин/Регистрация
        goto_login.setClass(this, LoginWindow.class);
        startActivity(goto_login);
        finish();
    }

    public void onClick(View view) {

            switch (view.getId())
            {
                case R.id.country:
                    Intent i;
                    i = new Intent(this, SelectCountryActivity.class);
                    startActivity(i);
                    break;
                case R.id.region:
                    Intent j;
                    j = new Intent(this, SelectRegionsActivity.class);
                    startActivity(j);
                    break;
                case R.id.sub_region:
                    Intent h;
                    h = new Intent(this, SelectSubRegionsActivity.class);
                    startActivity(h);
                    break;
                case R.id.button_back:
                    Intent z;
                    z = new Intent(this, MainActivity.class);
                    startActivity(z);
                    break;
            }
        }
    }
