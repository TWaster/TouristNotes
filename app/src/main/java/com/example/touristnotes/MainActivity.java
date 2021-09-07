package com.example.touristnotes;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void onClick(View view) {
        switch (view.getId())
        {
            case R.id.button_settings:
                Intent i;
                i = new Intent(this, UserSettingsActivity.class);
                startActivity(i);
                break;
            case R.id.category:
                Intent j;
                j = new Intent(this, SelectCategoryActivity.class);
                startActivity(j);
                break;
        }
    }
}
