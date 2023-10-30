package com.example.touristnotes;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.touristnotes.JSONReaderURL.NetworkService;
import com.example.touristnotes.pojo.ItemSelect;
import com.example.touristnotes.pojo.adapters.ObjectsAdapter;
import com.example.touristnotes.pojo.objects.Object;
import com.example.touristnotes.pojo.objects.ObjectsResult;
import com.google.android.material.snackbar.Snackbar;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SelectObjectActivity extends AppCompatActivity {
    // Информация о SharedPreferences
    public static final String APP_PREFERENCES = "UserLoginSP";
    public static final String APP_PREFERENCES_NAME = "Login";
    SharedPreferences UserSP;

    private ListView listView;
    private View parentView;
    private ArrayList<Object> ObjectList;
    private ObjectsAdapter adapter;
    private String object_type;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.select_object);
        final Intent intent = new Intent(this, ObjectActivity.class);
        //Вывод выбранного типа объектов в заголовок
        TextView HeadObj = findViewById(R.id.ObjTypeName);
        HeadObj.setText(getIntent().getStringExtra("SelectedTypeName"));
        //Toast.makeText(SelectObjectActivity.this, getIntent().getStringExtra("SelectedTypeID"), Toast.LENGTH_LONG).show();
        object_type = getIntent().getStringExtra("SelectedTypeID");
        ObjectList = new ArrayList<>();
        parentView = findViewById(R.id.parentLayout);

        //Получаем информацию из SharedPreference
        UserSP = getSharedPreferences(APP_PREFERENCES, Context.MODE_PRIVATE);
        String u_login = UserSP.getString(APP_PREFERENCES_NAME, "");

        //Объявление листа для отображения выгрузки JSON
        listView = (ListView) findViewById(R.id.listView_Objects); //Выбор нужного ID ListView
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Snackbar.make(parentView, ObjectList.get(position).getName() + "=>" + ObjectList.get(position).getId(), Snackbar.LENGTH_LONG).show();
                intent.putExtra("SelectedObjectID", ObjectList.get(position).getId());
                startActivity(intent);
                //finish();
            }
        });

        NetworkService.getInstance()
                .getJSONApiObjects()
                .getStringScalarObjects(new ObjectsResult(object_type))
                .enqueue(new Callback<ObjectsResult>() {
                    @Override
                    public void onResponse(@NonNull Call<ObjectsResult> call, @NonNull Response<ObjectsResult> response) {
                        ObjectList = (ArrayList<Object>) response.body().getObjects();

                        adapter = new ObjectsAdapter(SelectObjectActivity.this, ObjectList);
                        listView.setAdapter(adapter);
                    }

                    @Override
                    public void onFailure(@NonNull Call<ObjectsResult> call, @NonNull Throwable t) {
                        //Описание в случае ошибки запроса
                        Toast.makeText(SelectObjectActivity.this, "Ошибка запроса", Toast.LENGTH_SHORT).show();

                    }
                });
    }


}