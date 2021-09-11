package com.example.touristnotes;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class Achievements extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.achievements);
    }

    public void onClick(View view) {
        Intent back_ach;
        back_ach = new Intent(this, MainActivity.class);
        startActivity(back_ach);
    }
}