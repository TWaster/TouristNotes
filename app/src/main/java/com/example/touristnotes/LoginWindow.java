package com.example.touristnotes;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;
import com.example.touristnotes.JSONReaderURL.NetworkService;
import com.example.touristnotes.pojo.LoginData;
import com.example.touristnotes.pojo.LoginResult;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

 public class LoginWindow extends AppCompatActivity {
     // Информация о SharedPreferences
     public static final String APP_PREFERENCES = "UserLoginSP";
     public static final String APP_PREFERENCES_NAME = "Login";
     public static final String APP_PREFERENCES_PASSWORD = "Password";
     SharedPreferences UserSP;
     Intent goto_home = new Intent();

     @Override
     protected void onCreate(Bundle savedInstanceState) {
         super.onCreate(savedInstanceState);
         setContentView(R.layout.login_window);
         UserSP = getSharedPreferences(APP_PREFERENCES, Context.MODE_PRIVATE);
         if (UserSP.contains(APP_PREFERENCES_NAME) & UserSP.contains(APP_PREFERENCES_PASSWORD)) {
             String SPLogin = UserSP.getString(APP_PREFERENCES_NAME, "");
             String SPPass = UserSP.getString(APP_PREFERENCES_PASSWORD, "");
             goto_home.setClass(this, MainActivity.class);
             startActivity(goto_home);
             finish();
         }
     }

     public void LoginClick(View view) {
         final EditText u_login = (EditText) findViewById(R.id.u_login); //Получем логин
         final EditText u_pass = (EditText) findViewById(R.id.u_password); //Получаем пароль
         //Переход

         goto_home.setClass(this, MainActivity.class);
         // Отправка лог/пасс в БД с проверкой
         NetworkService.getInstance()
                 .getJSONApi()
                 .getStringScalar(new LoginData(u_login.getText().toString(), u_pass.getText().toString()))
                 .enqueue(new Callback<LoginResult>() {
                     @Override
                     public void onResponse(Call<LoginResult> call, Response<LoginResult> response) {
                         LoginResult loginResult = response.body();
                         if (loginResult.getUnique_key() != null) {
                             SharedPreferences.Editor editor = UserSP.edit();
                             editor.putString(APP_PREFERENCES_NAME, u_login.getText().toString());
                             editor.putString(APP_PREFERENCES_PASSWORD, u_pass.getText().toString());
                             editor.apply();
                             startActivity(goto_home);
                             finish();
                             Toast.makeText(LoginWindow.this, "Успешно!", Toast.LENGTH_SHORT).show();
                         } else {
                             Toast.makeText(LoginWindow.this, "Ошибка!", Toast.LENGTH_SHORT).show();
                         }
                     }
                     @Override
                     public void onFailure(Call<LoginResult> call, Throwable t) {
                         Toast.makeText(LoginWindow.this, "Ошибка!", Toast.LENGTH_SHORT).show();
                     }
                 });


     }
}