 package com.example.touristnotes;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.touristnotes.pojo.User;

import org.w3c.dom.Text;

 public class LoginWindow extends AppCompatActivity {
     User user;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login_window);


    }

     public void onClick(View view) {

         EditText u_login = (EditText) findViewById(R.id.u_login); //Получем логин
         EditText u_pass = (EditText) findViewById(R.id.u_password); //Получаем пароль
         //Отправка лог/пасс в БД с проверкой
         //функция

         //Заполнение объекта user из БД
         //функция

         //Временный переход
         Intent intent = new Intent();
         intent.setClass(this, MainActivity.class);

         startActivity(intent);
         finish();
         ////////////////////


         Toast.makeText(LoginWindow.this, "Успешный вход!", Toast.LENGTH_SHORT).show();

     }

     private User getUser() {
         return new User(1,
                 null,
                 "Administrator",
                 "Админ",
                 "Админович",
                 "Городской гуляка",
                 99,
                 0,
                 "0001022120231456325");
     }
}