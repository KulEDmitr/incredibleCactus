package com.afterapocalypticcrash.oldStructure.view.favourites;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import com.afterapocalypticcrash.R;
import com.afterapocalypticcrash.oldStructure.presenter.HandToHand;

public class FavouriteActivity extends AppCompatActivity{
    private static final String LOG_TAG = FavouriteActivity.class.getSimpleName();

    @Override
    public void onCreate(Bundle savedInstanceState) {
        Log.d(LOG_TAG, "onCreate");

        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_list);

        if (HandToHand.getList() == null) {
            FavouriteFragment fr = new FavouriteFragment();
            fr.setArguments(new Bundle());
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.res_list_fragment, fr)
                    .commitAllowingStateLoss();
        }
    }
}
