package com.afterapocalypticcrash;

import android.arch.persistence.room.Room;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import com.afterapocalypticcrash.dataBase.PicturesDataBase;

public class MainActivity extends AppCompatActivity {
    static final String LOG_TAG = MainActivity.class.getSimpleName();

    private static final String DATABASE_NAME = "pictures_db";
    static PicturesDataBase picturesDataBase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Log.w(LOG_TAG, "onCreate");


        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        picturesDataBase = Room.databaseBuilder(getApplicationContext(),
                PicturesDataBase.class, DATABASE_NAME)
                .build();

        findViewById(R.id.starter_s).setOnClickListener(v -> {
            Log.w(LOG_TAG, "onClick (StartSearching)");
            startActivity(new Intent(this, SearchActivity.class));
        });

        findViewById(R.id.starter_f).setOnClickListener(v -> {
            Log.w(LOG_TAG, "onClick (favourites)");
            startActivity(new Intent(this, SearchActivity.class));
        });
    }

//    picDao = ((AppDelegate) Objects.requireNonNull(getActivity()).getApplication())
//            .getPicDataBase().pictureDao();

}
