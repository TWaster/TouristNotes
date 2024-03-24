package com.example.touristnotes;

import android.os.Bundle;
import android.view.View;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.touristnotes.JSONReaderURL.NetworkService;
import com.example.touristnotes.pojo.adapters.RatingAdapter;
import com.example.touristnotes.pojo.rating.RatingUsers;
import com.example.touristnotes.pojo.rating.User;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;



public class UsersRating extends AppCompatActivity {

    private ListView listView;
    private View parentView;
    private ArrayList<User> RatingList;
    private RatingAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.users_rating);

        RatingList = new ArrayList<>();
        //parentView = findViewById(R.id.parentLayout);

        //Объявление листа для отображения выгрузки JSON
        listView = (ListView) findViewById(R.id.listView_rating);


        NetworkService.getInstance()
                .getJSONApiRatingUsers()
                .getStringScalarRatingUsers(new RatingUsers())
                .enqueue(new Callback<RatingUsers>() {
                    @Override
                    public void onResponse(Call<RatingUsers> call, Response<RatingUsers> response) {
                        RatingList = (ArrayList<User>) response.body().getUser();

                        adapter = new RatingAdapter(UsersRating.this, RatingList);
                        listView.setAdapter(adapter);
                    }

                    @Override
                    public void onFailure(Call<RatingUsers> call, Throwable t) {

                    }
                });
    }
}
