package com.example.touristnotes;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import com.example.touristnotes.JSONReaderURL.NetworkService;

import com.example.touristnotes.pojo.adapters.CommentsAdapter;
import com.example.touristnotes.pojo.comments.Comment;
import com.example.touristnotes.pojo.comments.CommentsResult;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class ViewComments extends AppCompatActivity {
    //Переменные для адаптера
    private ListView listView;
    private View parentView;
    private ArrayList<Comment> CommentList;
    private CommentsAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.comment_list);
        //Получаем переданные значение через Intent

        //Toast.makeText(ViewComments.this, getIntent().getStringExtra("userID"), Toast.LENGTH_SHORT).show();
        CommentList = new ArrayList<>();
        parentView = findViewById(R.id.parentLayout);

        //Объявление листа для отображения выгрузки JSON
        listView = (ListView) findViewById(R.id.listView_Comments);
        //Событие клика по комментарию
        //listView.setOnItemClickListener(new AdapterView.OnItemClickListener(){});

        //Подгрузка комментариев с сервера
        NetworkService.getInstance()
                .getJSONApiCommentsRead()
                .getStringScalarComments(new CommentsResult(getIntent().getStringExtra("userID"), getIntent().getStringExtra("objectID"), "0"))
                .enqueue(new Callback<CommentsResult>() {
                    @Override
                    public void onResponse(Call<CommentsResult> call, Response<CommentsResult> response) {
                        CommentList = (ArrayList<Comment>) response.body().getComments();
                        adapter = new CommentsAdapter(ViewComments.this, CommentList);
                        listView.setAdapter(adapter);
                    }

                    @Override
                    public void onFailure(Call<CommentsResult> call, Throwable t) {

                    }
                });

    }
    public void CommentAddClick (View view) {
        //Описываем метод отправки на сервер!
        EditText CommentText = (EditText)findViewById(R.id.comment_text);
        //ДОБАВИТЬ ПРАВИЛО ПУСТОГО EDITTEXT!!!
        if (CommentText.getText().toString().trim().isEmpty()){
            Toast.makeText(ViewComments.this, "Введите текст!", Toast.LENGTH_SHORT).show();
        } else {
            NetworkService.getInstance()
                    .getJSONApiCommentAdd()
                    .getStringScalarCommentAdd(new Comment(
                            getIntent().getStringExtra("userID"),
                            getIntent().getStringExtra("objectID"),
                            CommentText.getText().toString(),
                            "0"))
                    .enqueue(new Callback<Comment>() {
                        @Override
                        public void onResponse(Call<Comment> call, Response<Comment> response) {
                            //Toast.makeText(ViewComments.this, "Ваш комментарий добавлен", Toast.LENGTH_SHORT).show();
                            //Toast.makeText(ViewComments.this, "Получено 50 опыта", Toast.LENGTH_SHORT).show();
                        }
                        @Override
                        public void onFailure(Call<Comment> call, Throwable t) {
                        }
                    });
            CommentText.setText(null);
            recreate();
        }
    }
}
