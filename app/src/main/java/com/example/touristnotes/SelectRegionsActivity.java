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
import com.example.touristnotes.pojo.ItemSelect;
import com.example.touristnotes.pojo.adapters.RegionsAdapter;
import com.example.touristnotes.pojo.regions.Region;
import com.example.touristnotes.pojo.regions.RegionsResult;
import com.google.android.material.snackbar.Snackbar;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SelectRegionsActivity extends AppCompatActivity {
    // Информация о SharedPreferences
    public static final String APP_PREFERENCES = "UserLoginSP";
    public static final String APP_PREFERENCES_NAME = "Login";
    SharedPreferences UserSP;

    private ListView listView;
    private View parentView;
    private ArrayList<Region> RegionList;
    private RegionsAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.select_regions); //Выбор Layout отображения

        RegionList = new ArrayList<>();
        parentView = findViewById(R.id.parentLayout);

        //Получаем информацию из SharedPreference
        UserSP = getSharedPreferences(APP_PREFERENCES, Context.MODE_PRIVATE);
        String u_login = UserSP.getString(APP_PREFERENCES_NAME, "");

        //Объявление листа для отображения выгрузки JSON
        listView = (ListView) findViewById(R.id.listView_Regions); //Выбор нужного ID ListView
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Snackbar.make(parentView, RegionList.get(position).getName() + "=>" + RegionList.get(position).getId(), Snackbar.LENGTH_LONG).show();

                String u_login = UserSP.getString(APP_PREFERENCES_NAME, "");
                NetworkService.getInstance()
                        .getJSONApiSelectRegion()
                        .getStringScalarItem(new ItemSelect(RegionList.get(position).getId(),0,u_login,"0"))
                        .enqueue(new Callback<ItemSelect>() {
                            @Override
                            public void onResponse(@NonNull Call<ItemSelect> call, @NonNull retrofit2.Response<ItemSelect> response) {

                            }

                            @Override
                            public void onFailure(@NonNull Call<ItemSelect> call, @NonNull Throwable t) {

                            }
                        });
                finish();
            }
        });


        NetworkService.getInstance()
                .getJSONApiRegions()
                .getStringScalarRegions(new RegionsResult(u_login))
                .enqueue(new Callback<RegionsResult>() {
                    @Override
                    public void onResponse(@NonNull Call<RegionsResult> call, @NonNull Response<RegionsResult> response) {
                        RegionList = (ArrayList<Region>) response.body().getRegions();

                        adapter = new RegionsAdapter(SelectRegionsActivity.this, RegionList);
                        listView.setAdapter(adapter);
                    }

                    @Override
                    public void onFailure(@NonNull Call<RegionsResult> call, @NonNull Throwable t) {
                        //Описание в случае ошибки запроса
                        Toast.makeText(SelectRegionsActivity.this, "Ошибка запроса", Toast.LENGTH_SHORT).show();

                    }
                });
    }
}