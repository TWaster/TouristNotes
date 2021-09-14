package com.example.touristnotes;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.example.touristnotes.JSONReaderURL.NetworkService;
import com.example.touristnotes.pojo.LoginData;
import com.example.touristnotes.pojo.LoginResult;
import com.example.touristnotes.pojo.UserLogin;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

 public class LoginWindow extends AppCompatActivity {
     @Override
     protected void onCreate(Bundle savedInstanceState) {
         super.onCreate(savedInstanceState);
         setContentView(R.layout.login_window);
     }

     public void onClick(View view) {
         EditText u_login = (EditText) findViewById(R.id.u_login); //Получем логин
         EditText u_pass = (EditText) findViewById(R.id.u_password); //Получаем пароль
         //Переход
         final Intent goto_home = new Intent();
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