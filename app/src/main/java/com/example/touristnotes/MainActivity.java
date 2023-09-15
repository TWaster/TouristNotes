package com.example.touristnotes;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.denzcoskun.imageslider.ImageSlider;
import com.denzcoskun.imageslider.models.SlideModel;

import java.util.ArrayList;
import java.util.List;


public class MainActivity extends AppCompatActivity {
    //Предустановка Слайдера на Главной странице
    //*****************************************************************************************
    //В последствии настроить отображение слайдера на некоторые выборки из популярного контента
    //*****************************************************************************************
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ImageSlider imageSlider = findViewById(R.id.image_slider);
        List<SlideModel> slideModels = new ArrayList<>();

        slideModels.add(new SlideModel("http://travelesnotes.ru/images/preview/1.png","Image 1"));
        slideModels.add(new SlideModel("http://travelesnotes.ru/images/preview/2.png","Image 2"));
        slideModels.add(new SlideModel("http://travelesnotes.ru/images/preview/3.png","Image 3"));

        imageSlider.setImageList(slideModels, true);
    }

    //События на клики Главная страница
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
                Intent achiev;
                achiev = new Intent(this, Achievements.class);
                startActivity(achiev);
                break;
        }
    }
}
