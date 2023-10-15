package com.example.touristnotes.JSONReaderURL;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class NetworkService {
    private static NetworkService mInstance;
    private static final String BASE_URL = "http://travelesnotes.ru";
    private Retrofit mRetrofit;

    private NetworkService() {
        HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        OkHttpClient.Builder client = new OkHttpClient.Builder()
                .addInterceptor(interceptor);
        mRetrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .client(client.build())
                .build();
    }
    public static NetworkService getInstance() {
        if (mInstance == null) {
            mInstance = new NetworkService();
        }
        return mInstance;
    }

    //Обявление Интерфейсов Логин/Регистрация
    public InterUserLogin getJSONApiLogin() {
        return mRetrofit.create(InterUserLogin.class);
    }
    public InterUserRegistration getJSONApiRegistration() {return mRetrofit.create(InterUserRegistration.class);}
    public InterCountry getJSONApiSelectCountry() {
        return mRetrofit.create(InterCountry.class);
    }
}
