package com.example.touristnotes;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;
//Самописные классы для процесса логирования
import com.example.touristnotes.JSONReaderURL.NetworkService;
import com.example.touristnotes.pojo.LoginData;
import com.example.touristnotes.pojo.LoginResult;
import com.example.touristnotes.pojo.RegistrationData;
import com.example.touristnotes.pojo.RegistrationResult;

//Библиотеки для работы с API
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

 public class LoginWindow extends AppCompatActivity {
     // Информация о SharedPreferences
     public static final String APP_PREFERENCES = "UserLoginSP";
     public static final String APP_PREFERENCES_NAME = "Login";
     public static final String APP_PREFERENCES_PASSWORD = "Password";
     SharedPreferences UserSP;
     boolean doubleBackToExitPressedOnce = false;

     //Действие "Назад"
     @Override
     public void onBackPressed() {
         if (doubleBackToExitPressedOnce) {
             Intent startMain = new Intent(Intent.ACTION_MAIN);
             startMain.addCategory(Intent.CATEGORY_HOME);
             startMain.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
             startActivity(startMain);
             return;
         }
         this.doubleBackToExitPressedOnce = true;
         Toast.makeText(this, "Нажмите ещё раз чтобы выйти", Toast.LENGTH_SHORT).show();
         new Handler().postDelayed(new Runnable() {
             @Override
             public void run() {
                 doubleBackToExitPressedOnce = false;
             }
         }, 2000);
     }


     //Проверка при запуске приложения на предыдущий вход пользователя
     @Override
     protected void onCreate(Bundle savedInstanceState) {
         super.onCreate(savedInstanceState);
         final Intent intent = new Intent(this, MainActivity.class);
         setContentView(R.layout.login_window);
         UserSP = getSharedPreferences(APP_PREFERENCES, Context.MODE_PRIVATE);
         if (UserSP.contains(APP_PREFERENCES_NAME) & UserSP.contains(APP_PREFERENCES_PASSWORD)) {
             UserSP.getString(APP_PREFERENCES_NAME, "");
             UserSP.getString(APP_PREFERENCES_PASSWORD, "");

             //intent.putExtra("level", "99+ lvl");
             startActivity(intent);
             finish();
         }
     }
     //Кнопка "Войти"

     public void LoginClick(View view) {
         final EditText u_login = (EditText) findViewById(R.id.u_login); //Получем логин
         final EditText u_pass = (EditText) findViewById(R.id.u_password); //Получаем пароль
         // Отправка лог/пасс в БД с проверкой
         final Intent intent = new Intent(this, MainActivity.class);
         NetworkService.getInstance()
                 .getJSONApiLogin()
                 .getStringScalarLogin(new LoginData(u_login.getText().toString(), u_pass.getText().toString()))
                 .enqueue(new Callback<LoginResult>() {
                     @Override
                     public void onResponse(Call<LoginResult> call, Response<LoginResult> response) {
                         LoginResult loginResult = response.body();
                         if (loginResult.getUnique_key() != null) {
                             SharedPreferences.Editor editor = UserSP.edit();
                             editor.putString(APP_PREFERENCES_NAME, u_login.getText().toString());
                             editor.putString(APP_PREFERENCES_PASSWORD, u_pass.getText().toString());
                             editor.apply();

                             //Описание передачи данных между Activity
                             intent.putExtra("u_name", loginResult.getNickname());
                             intent.putExtra("u_avatar", loginResult.getAvatar());
                             intent.putExtra("u_s_country", loginResult.getS_country());
                             intent.putExtra("u_region", loginResult.getS_region());
                             intent.putExtra("u_s_region", loginResult.getS_sub_region());
                             intent.putExtra("u_rank", loginResult.getRank());
                             intent.putExtra("u_level", loginResult.getLevel());
                             intent.putExtra("u_unique_key", loginResult.getUnique_key());
                             intent.putExtra("u_message", loginResult.getMessage());

                             //Разобраться  с получнием пустых значений!



                             startActivity(intent);
                             finish();
                             Toast.makeText(LoginWindow.this, loginResult.getMessage(), Toast.LENGTH_SHORT).show();
                         } else {
                             Toast.makeText(LoginWindow.this, loginResult.getMessage(), Toast.LENGTH_SHORT).show();
                         }
                     }

                     @Override
                     public void onFailure(Call<LoginResult> call, Throwable t) {
                         Toast.makeText(LoginWindow.this, "Ошибка!", Toast.LENGTH_SHORT).show();
                     }
                 });
     }
     //Кнопка "Регистрация"
     public void RegistrationClick(View view) {
         //******************************************************************************
         //Надо дописывать функционал с проверкой существования пользователя в приложении
         //******************************************************************************
         final EditText u_login = (EditText) findViewById(R.id.u_login); //Получем логин
         final EditText u_pass = (EditText) findViewById(R.id.u_password); //Получаем пароль
         final Intent intent = new Intent(this, MainActivity.class);
         //Отправка лог/пасс для регистрации нового пользователя
         Toast.makeText(LoginWindow.this, "Отправка данных для регистрации пользователя!", Toast.LENGTH_SHORT).show();
         NetworkService.getInstance()
                 .getJSONApiRegistration()
                 .getStringScalarRegistration(new RegistrationData(u_login.getText().toString(), u_pass.getText().toString()))
                 .enqueue(new Callback<RegistrationResult>() {
                     @Override
                     public void onResponse(Call<RegistrationResult> call, Response<RegistrationResult> response) {
                         RegistrationResult registrationResult = response.body();
                         //Регистрация работает, надо дописать логику после успешнйо регистрации пользователя
                         //Определиться с логикой и передаваемыми параметрами
                         if (registrationResult.getUnique_key() != null) {
                             SharedPreferences.Editor editor = UserSP.edit();
                             editor.putString(APP_PREFERENCES_NAME, u_login.getText().toString());
                             editor.putString(APP_PREFERENCES_PASSWORD, u_pass.getText().toString());

                             editor.apply();
                             //intent.putExtra("level", "99+ lvl");
                             startActivity(intent);
                             Toast.makeText(LoginWindow.this, registrationResult.getMessage(), Toast.LENGTH_SHORT).show();
                         } else {
                             Toast.makeText(LoginWindow.this, registrationResult.getMessage(), Toast.LENGTH_SHORT).show();
                         }
                     }

                     @Override
                     public void onFailure(Call<RegistrationResult> call, Throwable t) {
                         Toast.makeText(LoginWindow.this, "Ошибка!", Toast.LENGTH_SHORT).show();
                     }
                 });
     }
     //Кнопка "Забыл пароль"
     public void ForgotPasswordClick(View view) {
         //******************************************************************************
         //Реализация сброса пароля пользователя по определённым условиям
         //******************************************************************************
     }

 }