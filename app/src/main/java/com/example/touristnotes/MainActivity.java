package com.example.touristnotes;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.denzcoskun.imageslider.ImageSlider;
import com.denzcoskun.imageslider.constants.ScaleTypes;
import com.denzcoskun.imageslider.models.SlideModel;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    //Предустановка Слайдера на Главной странице
    //*****************************************************************************************
    //В последствии настроить отображение слайдера на некоторые выборки из популярного контента
    //*****************************************************************************************
    ImageSlider imageSlider;
    @SuppressLint("SetTextI18n")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        imageSlider = findViewById(R.id.image_slider);
        ArrayList<SlideModel> slideModels = new ArrayList<>();

        slideModels.add(new SlideModel("http://travelesnotes.ru/images/preview/1.png", ScaleTypes.FIT));
        slideModels.add(new SlideModel("http://travelesnotes.ru/images/preview/2.png", ScaleTypes.FIT));
        slideModels.add(new SlideModel("http://travelesnotes.ru/images/preview/3.png", ScaleTypes.FIT));
        imageSlider.setImageList(slideModels);

        TextView user_level = findViewById(R.id.profile_level);
        user_level.setText(getIntent().getStringExtra("u_level") + " Ур.");
        TextView user_set_region = findViewById(R.id.CheckedRegion);
        user_set_region.setText(getIntent().getStringExtra("u_region"));
        ImageView userImageView = findViewById(R.id.user_image);
        Picasso.get().load(getIntent().getStringExtra("u_avatar")).into(userImageView);
    }

    //События на клики Главная страница
    @SuppressLint("NonConstantResourceId")
    public void onClick(View view) {
        switch (view.getId())
        {
            case R.id.button_settings:
                Intent settings;
                settings = new Intent(this, UserSettingsActivity.class);
                startActivity(settings);
                break;
            case R.id.collections:
                Intent collections;
                collections = new Intent(this, SelectCollectionsActivity.class);
                startActivity(collections);
                break;
            case R.id.profile:
                //Intent profile;
                //profile = new Intent(this, UserProfile.class);
                final Intent intent_prof = new Intent(this, UserProfile.class);
                
                startActivity(intent_prof);
                break;
            case R.id.user_image:
                Intent prof_img;
                prof_img = new Intent(this, UserProfile.class);
                startActivity(prof_img);
                break;
            case R.id.achievments:
                Intent achievments;
                achievments = new Intent(this, Achievements.class);
                startActivity(achievments);
                break;
        }
    }
}
