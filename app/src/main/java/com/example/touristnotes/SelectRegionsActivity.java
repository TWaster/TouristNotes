package com.example.touristnotes;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.touristnotes.JSONReaderURL.NetworkService;
import com.example.touristnotes.JSONReaderURL.RegionsRead;
import com.example.touristnotes.pojo.ItemSelect;
import com.example.touristnotes.pojo.RegionsResult;

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
    ListView listView;
    // Информация о SharedPreferences
    public static final String APP_PREFERENCES = "UserLoginSP";
    public static final String APP_PREFERENCES_NAME = "Login";
    SharedPreferences UserSP;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.select_regions); //Выбор Layout отображения
        UserSP = getSharedPreferences(APP_PREFERENCES, Context.MODE_PRIVATE);
        String u_login = UserSP.getString(APP_PREFERENCES_NAME, "");
        listView = (ListView) findViewById(R.id.listView_Regions); //Выбор нужного ID ListView
        //loadJSONFromURL();
        NetworkService.getInstance()
                .getJSONApiRegions()
                .getStringScalarRegions(new RegionsResult(u_login))
                .enqueue(new Callback<RegionsResult>() {
                    @Override
                    public void onResponse(@NonNull Call<RegionsResult> call, @NonNull Response<RegionsResult> response) {
                        //Описание в случае успешного запроса
                        Toast.makeText(SelectRegionsActivity.this, "Запрос выполнен успешно!", Toast.LENGTH_SHORT).show();

                    }

                    @Override
                    public void onFailure(@NonNull Call<RegionsResult> call, @NonNull Throwable t) {
                        //Описание в случае ошибки запроса
                        Toast.makeText(SelectRegionsActivity.this, "Ошибка запроса", Toast.LENGTH_SHORT).show();

                    }
                });
    }

    private void loadJSONFromURL() {
        StringRequest stringRequest = new StringRequest(Request.Method.GET, SelectRegionsActivity.JSON_URL,
                response -> {
                    try {
                        JSONObject object = new JSONObject(response);
                        JSONArray jsonArray = object.getJSONArray("regions"); //Название подгружаемого объекта JSON
                        ArrayList<JSONObject> listItems = getArrayListFromJSONArray(jsonArray);

                        ListAdapter adapter = new RegionsRead(getApplicationContext(), R.layout.list_item, R.id.li_name, listItems);
                        listView.setAdapter(adapter);
                        //Обработчик событий Click по элементам списка
                        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                                //Отправка UPDATE Country в БД под текущим именем пользователя
                                String u_login = UserSP.getString(APP_PREFERENCES_NAME, "");
                                Toast.makeText(SelectRegionsActivity.this, "Позиция: " + position, Toast.LENGTH_SHORT).show();
                                //NetworkService.getInstance()
                                //        .getJSONApiSelectCountry()
                                //        .getStringScalarItem(new ItemSelect(position,u_login))
                                //        .enqueue(new Callback<ItemSelect>() {
                                //            @Override
                                //            public void onResponse(@NonNull Call<ItemSelect> call, @NonNull retrofit2.Response<ItemSelect> response) {
                                //            }
                                //            @Override
                                //            public void onFailure(@NonNull Call<ItemSelect> call, @NonNull Throwable t) {
                                //            }
                                //        });
                                finish();
                            }
                        });
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                },
                error -> {
                    Toast.makeText(getApplicationContext(), error.getMessage(), Toast.LENGTH_SHORT).show();
                })
        {
            @Override
            protected Map<String, String> getParams () {
                Map<String, String> params = new HashMap<>();
                params.put("user_countrySelect", "4");
                return params;

            }
        };
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);
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