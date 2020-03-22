package com.afterapocalypticcrash.favourites;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import com.afterapocalypticcrash.R;

public class FavouriteActivity extends AppCompatActivity {
    private static final String LOG_TAG = FavouriteActivity.class.getSimpleName();

    @Override
    public void onCreate(Bundle savedInstanceState) {
        Log.d(LOG_TAG, "onCreate");

        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_list);

        if (savedInstanceState == null) {
            FavouriteFragment fr = new FavouriteFragment();
            Bundle bundle = new Bundle();
            fr.setArguments(bundle);
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.res_list_fragment, fr)
                    .commitAllowingStateLoss();
        }
    }
}
