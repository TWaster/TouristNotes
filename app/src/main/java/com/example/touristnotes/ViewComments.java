package com.example.touristnotes;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;


public class ViewComments extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.comment_list);
        //1-Реализовать подрузку комментариев с сервера
    }
}
