package com.afterapocalypticcrash.api;

import android.util.Log;

import com.squareup.moshi.Moshi;

import io.reactivex.schedulers.Schedulers;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.moshi.MoshiConverterFactory;

public class RetrofitModule {
    static final String LOG_TAG = RetrofitModule.class.getSimpleName();

    private static Moshi moshi;
    private static Retrofit retrofit;
    private static PictureApi api;
    private static String BASE_URL = "https://api.unsplash.com/";
    private static OkHttpClient client;

    private static void getRetrofit() {
        Log.d(LOG_TAG, "getRetrofit() called");
        if (client == null) {
            Log.d(LOG_TAG, "client created");
            client = new OkHttpClient.Builder().build();
        }
        if (moshi == null) {
            Log.d(LOG_TAG, "moshi created");
            moshi = new Moshi.Builder().build();
        }
        if (retrofit == null) {
            Log.d(LOG_TAG, "retrofit created");
            retrofit = new Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .callFactory(client)
                    .addConverterFactory(MoshiConverterFactory.create(moshi))
                    .addCallAdapterFactory(RxJava2CallAdapterFactory.createWithScheduler(Schedulers.io()))
                    .build();
            api = retrofit.create(PictureApi.class);
        }
        Log.e(LOG_TAG, "all ok with retrofit");
    }

    public static PictureApi getApiInstance() {
        Log.e(LOG_TAG, "getApiInstance");
        if (api == null) {
            getRetrofit();
        }
        return api;
    }
}
