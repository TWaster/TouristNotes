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
import com.example.touristnotes.pojo.adapters.SubRegionsAdapter;
import com.example.touristnotes.pojo.subregions.SubRegion;
import com.example.touristnotes.pojo.subregions.SubRegionsResult;
import com.google.android.material.snackbar.Snackbar;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SelectSubRegionsActivity extends AppCompatActivity {
    private static final String JSON_URL = "http://travelesnotes.ru/api/readSubRegions.php";
    // Информация о SharedPreferences
    public static final String APP_PREFERENCES = "UserLoginSP";
    public static final String APP_PREFERENCES_NAME = "Login";
    SharedPreferences UserSP;

    private ListView listView;
    private View parentView;
    private ArrayList<SubRegion> SubRegionList;
    private SubRegionsAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.select_sub_regions); //Выбор Layout отображения

        SubRegionList = new ArrayList<>();
        parentView = findViewById(R.id.parentLayout);

        //Получаем информацию из SharedPreference
        UserSP = getSharedPreferences(APP_PREFERENCES, Context.MODE_PRIVATE);
        String u_login = UserSP.getString(APP_PREFERENCES_NAME, "");

        //Объявление листа для отображения выгрузки JSON
        listView = (ListView) findViewById(R.id.listView_subRegions); //Выбор нужного ID ListView
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Snackbar.make(parentView, SubRegionList.get(position).getName() + "=>" + SubRegionList.get(position).getId(), Snackbar.LENGTH_LONG).show();

                String u_login = UserSP.getString(APP_PREFERENCES_NAME, "");
                NetworkService.getInstance()
                        .getJSONApiSelectSubRegion()
                        .getStringScalarItem(new ItemSelect("0", 0, u_login, SubRegionList.get(position).getId()))
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
                .getJSONApiSubRegions()
                .getStringScalarSubRegions(new SubRegionsResult(u_login))
                .enqueue(new Callback<SubRegionsResult>() {
                    @Override
                    public void onResponse(@NonNull Call<SubRegionsResult> call, @NonNull Response<SubRegionsResult> response) {
                        SubRegionList = (ArrayList<SubRegion>) response.body().getSubRegions();

                        adapter = new SubRegionsAdapter(SelectSubRegionsActivity.this, SubRegionList);
                        listView.setAdapter(adapter);
                    }

                    @Override
                    public void onFailure(@NonNull Call<SubRegionsResult> call, @NonNull Throwable t) {
                        //Описание в случае ошибки запроса
                        Toast.makeText(SelectSubRegionsActivity.this, "Ошибка запроса", Toast.LENGTH_SHORT).show();

                    }
                });
    }
}