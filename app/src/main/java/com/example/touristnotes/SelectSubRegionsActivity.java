package com.example.touristnotes;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class SelectSubRegionsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.select_sub_regions);
    }

    public void onClick(View view) {
        Intent i;
        i = new Intent(this, UserSettingsActivity.class);
        startActivity(i);
    }
}