package com.example.touristnotes;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.touristnotes.JSONReaderURL.NetworkService;
import com.example.touristnotes.pojo.objects.MarkedObject;
import com.example.touristnotes.pojo.objects.Object;
import com.example.touristnotes.pojo.objects.ObjectsResult;
import com.google.android.material.snackbar.Snackbar;
import com.squareup.picasso.Picasso;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ObjectActivity extends AppCompatActivity {
    public String objectID;
    //Подключение SharedPreference к форме
    public static final String APP_PREFERENCES = "UserLoginSP";
    SharedPreferences UserSP;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.object);
        final Intent intent = new Intent(this, ObjectActivity.class);
        String SelectedObject = getIntent().getStringExtra("SelectedObjectID");
        UserSP = getSharedPreferences(APP_PREFERENCES, Context.MODE_PRIVATE);

        NetworkService.getInstance()
                .getJSONApiObjectInfo()
                .getStringScalarObjectInfo(new Object(SelectedObject))
                .enqueue(new Callback<Object>() {
                    @Override
                    public void onResponse(@NonNull Call<Object> call, @NonNull Response<Object> response) {
                        Object objectInfo = response.body();
                        //Описание точек вывода
                        TextView NameObject = findViewById(R.id.ObjectName);
                        TextView ObjDescription = findViewById(R.id.Obj_description);
                        TextView ObjectRating = findViewById(R.id.ratingValue);
                        ImageView ImageObject = findViewById(R.id.image_object);

                        //Описание воводов в точки
                        NameObject.setText(objectInfo.getName());
                        ObjDescription.setText(objectInfo.getInfo());
                        Picasso.get().load(objectInfo.getImage()).into(ImageObject);
                        ObjectRating.setText(objectInfo.getRating());

                        //Данные для передачи
                        objectID = objectInfo.getId();
                    }

                    @Override
                    public void onFailure(@NonNull Call<Object> call, @NonNull Throwable t) {

                    }
                });
    }

    public void MarkedObject(View view){
        NetworkService.getInstance()
                .getJSONApiMarkedObject()
                .getStringScalarMarkedObject(new MarkedObject(objectID, UserSP.getString("UserID","")))
                .enqueue(new Callback<MarkedObject>() {
                    @Override
                    public void onResponse(@NonNull Call<MarkedObject> call, @NonNull Response<MarkedObject> response) {

                    }

                    @Override
                    public void onFailure(@NonNull Call<MarkedObject> call, @NonNull Throwable t) {

                    }
                });

    }
}