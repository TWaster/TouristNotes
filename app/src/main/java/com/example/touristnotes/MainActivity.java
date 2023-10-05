package com.example.touristnotes;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.graphics.Typeface;
import android.widget.Toast;


import com.denzcoskun.imageslider.ImageSlider;
import com.denzcoskun.imageslider.constants.ScaleTypes;
import com.denzcoskun.imageslider.models.SlideModel;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;


public class MainActivity extends AppCompatActivity {

    //Предустановка Слайдера на Главной странице
    //*****************************************************************************************
    //В последствии настроить отображение слайдера на некоторые выборки из популярного контента
    //*****************************************************************************************
    private ImageView userImageView;
    ImageSlider imageSlider;
    @SuppressLint("SetTextI18n")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        imageSlider = (ImageSlider) findViewById(R.id.image_slider);
        ArrayList<SlideModel> slideModels = new ArrayList<>();

        slideModels.add(new SlideModel("http://travelesnotes.ru/images/preview/1.png", ScaleTypes.FIT));
        slideModels.add(new SlideModel("http://travelesnotes.ru/images/preview/2.png", ScaleTypes.FIT));
        slideModels.add(new SlideModel("http://travelesnotes.ru/images/preview/3.png", ScaleTypes.FIT));
        imageSlider.setImageList(slideModels);

        TextView user_level = (TextView) findViewById(R.id.profile_level);
        user_level.setText(getIntent().getStringExtra("u_level") + " Ур.");
        TextView user_set_region = (TextView) findViewById(R.id.CheckedRegion);
        user_set_region.setText(getIntent().getStringExtra("u_region"));

        userImageView = findViewById(R.id.profile_image);
        Toast.makeText(MainActivity.this, getIntent().getStringExtra("u_avatar"), Toast.LENGTH_SHORT).show();
        Picasso.get().load(getIntent().getStringExtra("u_avatar")).into(userImageView);
    }

    //События на клики Главная страница
    @SuppressLint("NonConstantResourceId")
    public void onClick(View view) {
        switch (view.getId())
        {
            case R.id.button_settings:
                Intent i;
                i = new Intent(this, UserSettingsActivity.class);
                startActivity(i);
                break;
            case R.id.collections:
                Intent j;
                j = new Intent(this, SelectCollectionsActivity.class);
                startActivity(j);
                break;
            case R.id.profile:
                Intent prof;
                prof = new Intent(this, UserProfile.class);
                startActivity(prof);
                break;
            case R.id.profile_image:
                Intent prof_img;
                prof_img = new Intent(this, UserProfile.class);
                startActivity(prof_img);
                break;
            case R.id.achievments:
                Intent achieve;
                achieve = new Intent(this, Achievements.class);
                startActivity(achieve);
                break;
        }
    }
}
