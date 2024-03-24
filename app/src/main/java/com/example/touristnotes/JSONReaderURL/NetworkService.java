package com.example.touristnotes.JSONReaderURL;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class NetworkService {
    private static NetworkService mInstance;
    private static final String BASE_URL = "http://travelesnotes.ru";
    private final Retrofit mRetrofit;

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

    //Обявление Интерфейсов
    public InterUserLogin getJSONApiLogin() {return mRetrofit.create(InterUserLogin.class);}
    public InterUserRegistration getJSONApiRegistration() {return mRetrofit.create(InterUserRegistration.class);}
    public InterCountry getJSONApiSelectCountry() {return mRetrofit.create(InterCountry.class);}
    public InterRegions getJSONApiRegions() {return mRetrofit.create(InterRegions.class);}
    public InterUpRegion getJSONApiSelectRegion(){return mRetrofit.create(InterUpRegion.class);}
    public InterSubRegions getJSONApiSubRegions() {return mRetrofit.create(InterSubRegions.class);}
    public InterUpSubRegion getJSONApiSelectSubRegion(){return mRetrofit.create(InterUpSubRegion.class); }
    public InterCollections getJSONApiCollections(){return  mRetrofit.create(InterCollections.class);}
    public InterObjects getJSONApiObjects(){return mRetrofit.create(InterObjects.class);}
    public InterSelectedObject getJSONApiObjectInfo(){return mRetrofit.create(InterSelectedObject.class);}
    public InterMarkedObject getJSONApiMarkedObject(){return mRetrofit.create(InterMarkedObject.class);}
    public InterUpGradeObject getJSONApiUpGradeObject(){return mRetrofit.create(InterUpGradeObject.class);}
    public InterCommentsRead getJSONApiCommentsRead(){return mRetrofit.create(InterCommentsRead.class);}
    public InterCommentAdd getJSONApiCommentAdd(){return mRetrofit.create(InterCommentAdd.class);}
    public InterUpUserAvatar getJSONApiUpUserAvatar(){return mRetrofit.create(InterUpUserAvatar.class);}
    public InterLastPlaces getJSONApiLastPlaces(){return  mRetrofit.create(InterLastPlaces.class);}
    public InterRatingUsers getJSONApiRatingUsers(){return mRetrofit.create(InterRatingUsers.class);}
}
