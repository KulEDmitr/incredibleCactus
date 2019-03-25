package com.afterapocalypticcrash.favourites;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import com.afterapocalypticcrash.R;

public class FavouriteActivity extends AppCompatActivity {
    private static final String LOG_TAG = FavouriteActivity.class.getSimpleName();
    private static String FAVOURITE_LIST = "FAVOURITE_LIST";

    @Override
    public void onCreate(Bundle savedInstanceState) {
        Log.d(LOG_TAG, "onCreate");

        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_list);

        if (savedInstanceState == null) {
            FavouriteFragment fr = new FavouriteFragment();
            Bundle bundle = new Bundle();
            bundle.putParcelableArrayList(FAVOURITE_LIST,
                    getIntent().getParcelableArrayListExtra(FAVOURITE_LIST));
            fr.setArguments(bundle);
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.res_list_fragment, fr)
                    .commitAllowingStateLoss();
        } else {
            getIntent().putParcelableArrayListExtra(FAVOURITE_LIST,
                    savedInstanceState.getParcelableArrayList(FAVOURITE_LIST));
        }
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        Log.d(LOG_TAG, "onSaveInstanceState");
        super.onSaveInstanceState(outState);
        outState.putParcelableArrayList(FAVOURITE_LIST, getIntent()
                .getParcelableArrayListExtra(FAVOURITE_LIST));
    }
}
