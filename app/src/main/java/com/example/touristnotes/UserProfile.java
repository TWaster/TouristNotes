package com.example.touristnotes;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Base64;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.touristnotes.JSONReaderURL.NetworkService;
import com.example.touristnotes.pojo.adapters.LastPlacesAdapter;
import com.example.touristnotes.pojo.adapters.RegionsAdapter;
import com.example.touristnotes.pojo.regions.Region;
import com.example.touristnotes.pojo.regions.RegionsResult;
import com.example.touristnotes.pojo.users.AvatarUpload;
import com.example.touristnotes.pojo.users.LastPlace;
import com.example.touristnotes.pojo.users.LastPlaces;
import com.example.touristnotes.pojo.users.LoginData;
import com.example.touristnotes.pojo.users.LoginResult;
import com.squareup.picasso.Picasso;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class UserProfile extends AppCompatActivity {
    // Информация о SharedPreferences
    public static final String APP_PREFERENCES = "UserLoginSP";
    public static final String APP_PREFERENCES_NAME = "Login";
    public static final String APP_PREFERENCES_PASSWORD = "Password";
    SharedPreferences UserSP;

    private ListView listView;
    private ArrayList<LastPlace> LastPlaces;
    private LastPlacesAdapter adapter;

    //Для работы с галлереей
    static final int GALLERY_REQUEST = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.user_profile);
        final Intent intent = new Intent(this, ObjectActivity.class);
        UserSP = getSharedPreferences(APP_PREFERENCES, Context.MODE_PRIVATE);
        LastPlaces = new ArrayList<>();
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
        if (UserSP.contains(APP_PREFERENCES_NAME) & UserSP.contains(APP_PREFERENCES_PASSWORD)) {
            String u_login = UserSP.getString(APP_PREFERENCES_NAME, "");
            String u_password = UserSP.getString(APP_PREFERENCES_PASSWORD, "");
            //Загрузка информации профиля
            NetworkService.getInstance()
                    .getJSONApiLogin()
                    .getStringScalarLogin(new LoginData(u_login, u_password))
                    .enqueue(new Callback<LoginResult>() {
                        @SuppressLint("SetTextI18n")
                        @Override
                        public void onResponse(@NonNull Call<LoginResult> call, @NonNull Response<LoginResult> response) {
                            LoginResult loginResult = response.body();
                            assert loginResult != null;
                            if (loginResult.getUnique_key() != null) {
                                ImageView userImageView = findViewById(R.id.user_image);
                                Picasso.get().load(loginResult.getAvatar()).into(userImageView);
                                TextView user_name = findViewById(R.id.name_profile);
                                user_name.setText(loginResult.getNickname());
                                TextView user_level = findViewById(R.id.level_profile);
                                user_level.setText(loginResult.getLevel() + " Уровень");
                            } else {
                                Toast.makeText(UserProfile.this, loginResult.getMessage(), Toast.LENGTH_SHORT).show();
                            }
                        }

                        @Override
                        public void onFailure(@NonNull Call<LoginResult> call, @NonNull Throwable t) {
                            Toast.makeText(UserProfile.this, "Ошибка!", Toast.LENGTH_SHORT).show();
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

                            adapter = new LastPlacesAdapter(UserProfile.this, LastPlaces);
                            listView.setAdapter(adapter);
                        }

                        @Override
                        public void onFailure(Call<LastPlaces> call, Throwable t) {

                        }
                    });
        }
    }

    public void onClick(View view) {
        Intent back_profile;
        back_profile = new Intent(this, MainActivity.class);
        startActivity(back_profile);
    }
    public void ImageUpload (View view){
        Intent photoPickerIntent = new Intent(Intent.ACTION_PICK);
        photoPickerIntent.setType("image/*");
        startActivityForResult(photoPickerIntent, GALLERY_REQUEST);
        //Toast.makeText(UserProfile.this, "Выгрузка изображения...", Toast.LENGTH_SHORT).show();
    }
    //Конверт Bitmap в Base64
    public String convert(Bitmap bitmap) {
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.JPEG, 70, outputStream);
        return Base64.encodeToString(outputStream.toByteArray(), Base64.DEFAULT);
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent imageReturnedIntent) {
        super.onActivityResult(requestCode, resultCode, imageReturnedIntent);

        Bitmap bitmap = null;
        ImageView imageView = (ImageView) findViewById(R.id.user_image);

        switch(requestCode) {
            case GALLERY_REQUEST:
                if(resultCode == RESULT_OK){
                    Uri selectedImage = imageReturnedIntent.getData();
                    try {
                        bitmap = MediaStore.Images.Media.getBitmap(getContentResolver(), selectedImage);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    imageView.setImageBitmap(bitmap);
                    Bitmap finalBitmap = bitmap;
                    //Thread используем для полной отправки изображения не сервер.
                    new Thread(new Runnable() {
                        @Override
                        public void run() {
                            NetworkService.getInstance()
                                    .getJSONApiUpUserAvatar()
                                    .getStringScalarUserImageAdd(new AvatarUpload("1", convert(finalBitmap)))
                                    .enqueue(new Callback<AvatarUpload>() {
                                        @Override
                                        public void onResponse(Call<AvatarUpload> call, Response<AvatarUpload> response) {
                                            recreate();
                                        }

                                        @Override
                                        public void onFailure(Call<AvatarUpload> call, Throwable t) {

                                        }
                                    });
                        }
                    }).start();

                    //Дописать ограничения на подгружаемые изображения, или добавить обработчик на сжатие изображений.

                }
        }
    }
}