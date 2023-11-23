package com.example.touristnotes;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.touristnotes.JSONReaderURL.NetworkService;
import com.example.touristnotes.pojo.objects.MarkedObject;
import com.example.touristnotes.pojo.objects.Object;
import com.example.touristnotes.pojo.objects.ObjectsResult;
import com.google.android.material.snackbar.Snackbar;
import com.squareup.picasso.Picasso;

import java.util.Objects;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ObjectActivity extends AppCompatActivity {
    public String objectID;
    //Подключение SharedPreference к форме
    public static final String APP_PREFERENCES = "UserLoginSP";
    public static final String OBJECT_ID = "ObjectID";

    SharedPreferences UserSP;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.object);
        final Intent intent = new Intent(this, ObjectActivity.class);
        String SelectedObject = getIntent().getStringExtra("SelectedObjectID");
        UserSP = getSharedPreferences(APP_PREFERENCES, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = UserSP.edit();

        NetworkService.getInstance()
                .getJSONApiObjectInfo()
                .getStringScalarObjectInfo(new Object(SelectedObject, UserSP.getString("UserID","")))//Добавить передачу в виде: (SelectedObject, UserID)
                .enqueue(new Callback<Object>() {
                    @Override
                    public void onResponse(@NonNull Call<Object> call, @NonNull Response<Object> response) {
                        Object objectInfo = response.body();

                        //Описание точек вывода
                        TextView NameObject = findViewById(R.id.ObjectName);
                        TextView ObjDescription = findViewById(R.id.Obj_description);
                        TextView ObjectRating = findViewById(R.id.ratingValue);
                        ImageView ImageObject = findViewById(R.id.image_object);
                        ImageView ObjectMarked = findViewById(R.id.check_object);
                        Button MarkedObject = findViewById(R.id.markedobject);
                        ImageView PB_Dificulty = findViewById(R.id.PB_Dificulty);

                        //Описание воводов в точки
                        NameObject.setText(objectInfo.getName());
                        ObjDescription.setText(objectInfo.getInfo());
                        Picasso.get().load(objectInfo.getImage()).into(ImageObject);
                        ObjectRating.setText(objectInfo.getRating());
                        //Прогресс бар вывода сложности объекта
                        switch (objectInfo.getDifficulty()){
                            case "1":
                                PB_Dificulty.setImageResource(R.drawable.pb_1);
                                break;
                            case "2":
                                PB_Dificulty.setImageResource(R.drawable.pb_2);
                                break;
                            case "3":
                                PB_Dificulty.setImageResource(R.drawable.pb_3);
                                break;
                            case "4":
                                PB_Dificulty.setImageResource(R.drawable.pb_4);
                                break;
                            case "5":
                                PB_Dificulty.setImageResource(R.drawable.pb_5);
                                break;
                        }


                        if (Objects.equals(response.body().getMarked(), "1")) {
                            ObjectMarked.setVisibility(View.VISIBLE);
                            MarkedObject.setVisibility(View.INVISIBLE);
                        } else {
                            ObjectMarked.setVisibility(View.INVISIBLE);
                            MarkedObject.setVisibility(View.VISIBLE);
                        }

                        //Данные для передачи
                        editor.putString(OBJECT_ID, objectInfo.getId());
                        editor.apply();
                        Toast.makeText(ObjectActivity.this,UserSP.getString("ObjectID",""),Toast.LENGTH_LONG).show();
                    }

                    @Override
                    public void onFailure(@NonNull Call<Object> call, @NonNull Throwable t) {

                    }
                });
    }

    public void MarkedObject(View view){
        NetworkService.getInstance()
                .getJSONApiMarkedObject()
                .getStringScalarMarkedObject(new MarkedObject(UserSP.getString("ObjectID", ""), UserSP.getString("UserID",""))) //Реализовать корректную передачу objectID из другого класса.
                .enqueue(new Callback<MarkedObject>() {
                    @Override
                    public void onResponse(@NonNull Call<MarkedObject> call, @NonNull Response<MarkedObject> response) {

                    }

                    @Override
                    public void onFailure(@NonNull Call<MarkedObject> call, @NonNull Throwable t) {

                    }
                });
        recreate();
    }
}