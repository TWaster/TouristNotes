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
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.touristnotes.JSONReaderURL.CountriesRead;
import com.example.touristnotes.JSONReaderURL.NetworkService;
import com.example.touristnotes.pojo.ItemSelect;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;

public class SelectCountryActivity extends AppCompatActivity {
    private static final String JSON_URL = "http://travelesnotes.ru/api/readCountries.php";
    ListView listView;
    // Информация о SharedPreferences
    public static final String APP_PREFERENCES = "UserLoginSP";
    public static final String APP_PREFERENCES_NAME = "Login";
    SharedPreferences UserSP;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.select_country); //Выбор Layout отображения
        UserSP = getSharedPreferences(APP_PREFERENCES, Context.MODE_PRIVATE);
        listView = (ListView) findViewById(R.id.listView_country); //Выбор нужного ID ListView
        loadJSONFromURL();
    }

    private void loadJSONFromURL() {
        StringRequest stringRequest = new StringRequest(Request.Method.GET, SelectCountryActivity.JSON_URL,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            JSONObject object = new JSONObject(response);
                            JSONArray jsonArray = object.getJSONArray("countries"); //Название подгружаемого объекта JSON
                            ArrayList<JSONObject> listItems = getArrayListFromJSONArray(jsonArray);

                            ListAdapter adapter = new CountriesRead(getApplicationContext(), R.layout.list_item, R.id.li_name, listItems);
                            listView.setAdapter(adapter);
                            //Обработчик событий Click по элементам списка
                            listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                                    //Отправка UPDATE Country в БД под текущим именем пользователя
                                    String u_login = UserSP.getString(APP_PREFERENCES_NAME, "");
                                    NetworkService.getInstance()
                                            .getJSONApiSelectCountry()
                                            .getStringScalarItem(new ItemSelect("0",position,u_login,"0"))
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
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(getApplicationContext(), error.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
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