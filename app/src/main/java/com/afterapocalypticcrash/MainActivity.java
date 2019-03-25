package com.afterapocalypticcrash;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import com.afterapocalypticcrash.favourites.FavouriteActivity;
import com.afterapocalypticcrash.pictureDetails.AllData;
import com.afterapocalypticcrash.search.SearchActivity;

public class MainActivity extends AppCompatActivity {
    static final String LOG_TAG = MainActivity.class.getSimpleName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Log.d(LOG_TAG, "onCreate");

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        findViewById(R.id.starter_s).setOnClickListener(v -> {
            Log.d(LOG_TAG, "onClick (StartSearching)");
            startActivity(new Intent(this, SearchActivity.class));
        });

        findViewById(R.id.starter_f).setOnClickListener(v -> {
            Log.d(LOG_TAG, "onClick (favourites)");
            startActivity(new Intent(this, FavouriteActivity.class));
        });
    }
}
