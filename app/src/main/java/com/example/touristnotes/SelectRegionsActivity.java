package com.example.touristnotes;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.touristnotes.JSONReaderURL.NetworkService;
import com.example.touristnotes.pojo.adapters.RegionsAdapter;
import com.example.touristnotes.pojo.regions.Region;
import com.example.touristnotes.pojo.regions.RegionsResult;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SelectRegionsActivity extends AppCompatActivity {
    private static final String JSON_URL = "http://travelesnotes.ru/api/readRegions.php";
    //ListView listView;
    // Информация о SharedPreferences
    public static final String APP_PREFERENCES = "UserLoginSP";
    public static final String APP_PREFERENCES_NAME = "Login";
    SharedPreferences UserSP;

    // Тестовая выгрузка регионов из POJO
    private ListView listView;
    private View parentView;
    private ArrayList<Region> RegionList;
    private RegionsAdapter adapter;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.select_regions); //Выбор Layout отображения

        // Тестовая выгрузка регионов из POJO
        RegionList = new ArrayList<>();
        parentView = findViewById(R.id.parentLayout);

        //Получаем информацию из SharedPreference
        UserSP = getSharedPreferences(APP_PREFERENCES, Context.MODE_PRIVATE);
        String u_login = UserSP.getString(APP_PREFERENCES_NAME, "");

        //Объявление листа для отображения выгрузки JSON
        listView = (ListView) findViewById(R.id.listView_Regions); //Выбор нужного ID ListView

        //loadJSONFromURL();
        NetworkService.getInstance()
                .getJSONApiRegions()
                .getStringScalarRegions(new RegionsResult(u_login))
                .enqueue(new Callback<RegionsResult>() {
                    @Override
                    public void onResponse(@NonNull Call<RegionsResult> call, @NonNull Response<RegionsResult> response) {

                        RegionList = (ArrayList<Region>) response.body().getRegions();

                        adapter = new RegionsAdapter(SelectRegionsActivity.this, RegionList);
                        listView.setAdapter(adapter);

                        //Описание в случае успешного запроса
                        //Toast.makeText(SelectRegionsActivity.this, "Успех!", Toast.LENGTH_SHORT).show();

                        //JSONObject object = new Gson().fromJson(RegionsResult.Root, ArrayList<>);

                        //JSONArray jsonArray = object.getJSONArray("regions"); //Название подгружаемого объекта JSON
                        //ArrayList<JSONObject> listItems = getArrayListFromJSONArray(jsonArray);

                        //ListAdapter adapter = new RegionsRead(getApplicationContext(), R.layout.list_item, R.id.li_name, listItems);
                        //listView.setAdapter(adapter);
                    }

                    @Override
                    public void onFailure(@NonNull Call<RegionsResult> call, @NonNull Throwable t) {
                        //Описание в случае ошибки запроса
                        Toast.makeText(SelectRegionsActivity.this, "Ошибка запроса", Toast.LENGTH_SHORT).show();

                    }
                });
    }



    private ArrayList<JSONObject> getArrayListFromJSONArray(JSONArray jsonArray) {
        ArrayList<JSONObject> aList = new ArrayList<JSONObject>();
        try {
            if (jsonArray != null) {
                for (int i = 0; i < jsonArray.length(); i++) {
                    aList.add(jsonArray.getJSONObject(i));
                }
            }
        } catch (JSONException js) {
            js.printStackTrace();
        }
        return aList;
    }
}