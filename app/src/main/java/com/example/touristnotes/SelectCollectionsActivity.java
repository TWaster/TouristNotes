package com.example.touristnotes;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.example.touristnotes.JSONReaderURL.NetworkService;
import com.google.android.material.snackbar.Snackbar;
import com.example.touristnotes.pojo.adapters.CollectionsAdapter;
import com.example.touristnotes.pojo.collections.Collection;
import com.example.touristnotes.pojo.collections.CollectionsResult;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SelectCollectionsActivity extends AppCompatActivity {
    // Информация о SharedPreferences
    public static final String APP_PREFERENCES = "UserLoginSP";
    public static final String APP_PREFERENCES_NAME = "Login";
    SharedPreferences UserSP;

    private ListView listView;
    private View parentView;
    private ArrayList<Collection> CollectionList;
    private CollectionsAdapter adapter;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.select_collections);

        CollectionList = new ArrayList<>();
        parentView = findViewById(R.id.parentLayout);

        //Получаем информацию из SharedPreference
        UserSP = getSharedPreferences(APP_PREFERENCES, Context.MODE_PRIVATE);
        String u_login = UserSP.getString(APP_PREFERENCES_NAME, "");

        //Объявление листа для отображения выгрузки JSON
        listView = (ListView) findViewById(R.id.listView_collections); //Выбор нужного ID ListView

        NetworkService.getInstance()
                .getJSONApiCollections()
                .getStringScalarCollections(new CollectionsResult(u_login))
                .enqueue(new Callback<CollectionsResult>() {
                    @Override
                    public void onResponse(@NonNull Call<CollectionsResult> call, @NonNull Response<CollectionsResult> response) {
                        CollectionList = (ArrayList<Collection>) response.body().getCollections();

                        adapter = new CollectionsAdapter(SelectCollectionsActivity.this, CollectionList);
                        listView.setAdapter(adapter);
                    }

                    @Override
                    public void onFailure(@NonNull Call<CollectionsResult> call, @NonNull Throwable t) {
                        //Описание в случае ошибки запроса
                        Toast.makeText(SelectCollectionsActivity.this, "Ошибка запроса", Toast.LENGTH_SHORT).show();

                    }
                });
    }
}