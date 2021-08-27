package com.example.touristnotes;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class UserSettingsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.user_settings);
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
