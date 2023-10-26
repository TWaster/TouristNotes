package com.example.touristnotes;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

public class SelectObjectActivity extends AppCompatActivity {

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.select_object);

        TextView HeadObj = findViewById(R.id.ObjTypeName);
        HeadObj.setText(getIntent().getStringExtra("SelectedTypeName"));

        Toast.makeText(SelectObjectActivity.this, getIntent().getStringExtra("SelectedTypeID"), Toast.LENGTH_LONG).show();

    }


}