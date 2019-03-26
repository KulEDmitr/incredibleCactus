package com.afterapocalypticcrash.oldStructure.view.pictureDetails;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import com.afterapocalypticcrash.R;
import com.afterapocalypticcrash.oldStructure.presenter.HandToHand;

public class DetailActivity extends AppCompatActivity {
    private static final String LOG_TAG = DetailActivity.class.getSimpleName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Log.d(LOG_TAG, "onCreate");

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        if (HandToHand.getKey() != null) {
            DetailFragment fragment = new DetailFragment();
            fragment.setArguments(new Bundle());
            getSupportFragmentManager().beginTransaction()
                    .add(R.id.detail_container, fragment)
                    .commitAllowingStateLoss();
        }
    }
}
