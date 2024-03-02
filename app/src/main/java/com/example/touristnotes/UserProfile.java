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
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.touristnotes.JSONReaderURL.NetworkService;
import com.example.touristnotes.pojo.users.AvatarUpload;
import com.example.touristnotes.pojo.users.LoginData;
import com.example.touristnotes.pojo.users.LoginResult;
import com.squareup.picasso.Picasso;

import java.io.ByteArrayOutputStream;
import java.io.IOException;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class UserProfile extends AppCompatActivity {
    // Информация о SharedPreferences
    public static final String APP_PREFERENCES = "UserLoginSP";
    public static final String APP_PREFERENCES_NAME = "Login";
    public static final String APP_PREFERENCES_PASSWORD = "Password";
    SharedPreferences UserSP;

    //Для работы с галлереей
    static final int GALLERY_REQUEST = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.user_profile);
        UserSP = getSharedPreferences(APP_PREFERENCES, Context.MODE_PRIVATE);

        //final Intent intent = new Intent();
        if (UserSP.contains(APP_PREFERENCES_NAME) & UserSP.contains(APP_PREFERENCES_PASSWORD)) {
            String u_login = UserSP.getString(APP_PREFERENCES_NAME, "");
            String u_password = UserSP.getString(APP_PREFERENCES_PASSWORD, "");

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

                                        }

                                        @Override
                                        public void onFailure(Call<AvatarUpload> call, Throwable t) {

                                        }
                                    });
                        }
                    }).start();
                    recreate();
                    //Дописать ограничения на подгружаемые изображения, или добавить обработчик на сжатие изображений.

                }
        }
    }
}