package com.example.touristnotes;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.touristnotes.JSONReaderURL.NetworkService;
import com.example.touristnotes.pojo.adapters.LastPlacesAdapter;
import com.example.touristnotes.pojo.users.LastPlace;
import com.example.touristnotes.pojo.users.LastPlaces;
import com.example.touristnotes.pojo.users.ProfileView;
import com.squareup.picasso.Picasso;
import java.util.ArrayList;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class UserProfileView extends AppCompatActivity {
    // Информация о SharedPreferences
    public static final String APP_PREFERENCES = "UserLoginSP";
    SharedPreferences UserSP;

    private ListView listView;
    private ArrayList<LastPlace> LastPlaces;
    private LastPlacesAdapter adapter;
    //Для работы ProgressBar
    private ProgressBar level_progress;
    //Для работы с галлереей
    static final int GALLERY_REQUEST = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.profile_view);
        final Intent intent = new Intent(this, ObjectActivity.class);
        UserSP = getSharedPreferences(APP_PREFERENCES, Context.MODE_PRIVATE);
        LastPlaces = new ArrayList<>();
        //Обьявление ProgressBar уровня пользователя
        level_progress = (ProgressBar) findViewById(R.id.level_progress);
        //Объявление листа для отображения выгрузки JSON
        listView = (ListView) findViewById(R.id.list_LastPlaces);
        //Обработка клика по объектам
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                //Snackbar.make(parentView, ObjectList.get(position).getName() + "=>" + ObjectList.get(position).getId(), Snackbar.LENGTH_LONG).show();
                intent.putExtra("SelectedObjectID", LastPlaces.get(position).getId());
                //Log.i("LOG", ObjectList.get(position).getId());
                startActivity(intent);
                //finish();
            }
        });
        String u_login = getIntent().getStringExtra("SelectedUserName");

        //Загрузка информации профиля
        NetworkService.getInstance()
                .getJSONApiProfileView()
                .getStringScalarProfileView(new ProfileView(u_login))
                .enqueue(new Callback<ProfileView>() {
                    @Override
                    public void onResponse(Call<ProfileView> call, Response<ProfileView> response) {
                        ProfileView profileResult = response.body();
                        ImageView userImageView = findViewById(R.id.user_image);
                        Picasso.get().load(profileResult.getAvatar()).into(userImageView);
                        TextView user_name = findViewById(R.id.name_profile);
                        user_name.setText(profileResult.getNickname());
                        TextView user_level = findViewById(R.id.level_profile);
                        user_level.setText(profileResult.getLevel() + " Уровень");
                        //Отображения прогресса уровня пользователя
                        Integer need_exp = profileResult.getNeedExp(); //Получаем с сервера
                        Integer current_exp = profileResult.getCurrentExp(); //Получаем с сервера
                        level_progress.setProgress((current_exp * 100) / need_exp);
                    }
                    @Override
                    public void onFailure(Call<ProfileView> call, Throwable t) {
                    }
                });
        //Получаем информацию о последних посещённых местах
        NetworkService.getInstance()
                .getJSONApiLastPlaces()
                .getStringScalarLastPlaces(new LastPlaces(u_login))
                .enqueue(new Callback<LastPlaces>() {
                    @Override
                    public void onResponse(@NonNull Call<LastPlaces> call, @NonNull Response<LastPlaces> response) {
                        //assert response.body() != null;
                        LastPlaces = (ArrayList<LastPlace>) response.body().getLastPlaces();

                        if (LastPlaces!=null){
                            adapter = new LastPlacesAdapter(UserProfileView.this, LastPlaces);
                            listView.setAdapter(adapter);}
                        else {
                            adapter = new LastPlacesAdapter(UserProfileView.this, LastPlaces);
                            listView.setAdapter(adapter);
                            //Задать текст отсутсвия объектов
                        }
                    }

                    @Override
                    public void onFailure(Call<LastPlaces> call, Throwable t) {

                    }
                });
    }
}
