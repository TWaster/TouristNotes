package com.example.touristnotes;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class UserProfile extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.user_profile);
    }

    public void onClick(View view) {
        Intent back_profile;
        back_profile = new Intent(this, MainActivity.class);
        startActivity(back_profile);
    }
}