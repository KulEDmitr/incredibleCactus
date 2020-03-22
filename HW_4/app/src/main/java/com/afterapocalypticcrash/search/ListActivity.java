package com.afterapocalypticcrash.search;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import com.afterapocalypticcrash.R;


public class ListActivity extends AppCompatActivity {
    static final String LOG_TAG = ListActivity.class.getSimpleName();

    static final String QUERY = "QUERY";

    @Override
    public void onCreate(Bundle savedInstanceState) {
        Log.d(LOG_TAG, "onCreate");

        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_list);

        if (savedInstanceState == null) {
            ListFragment fr = new ListFragment();
            Bundle bundle = new Bundle();
            bundle.putString(QUERY, getIntent().getStringExtra(QUERY));
            fr.setArguments(bundle);
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.res_list_fragment, fr)
                    .commitAllowingStateLoss();
        } else {
            getIntent().putExtra(QUERY, savedInstanceState.getString(QUERY));

        }
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        Log.d(LOG_TAG, "onSaveInstanceState");
        outState.putString(QUERY, getIntent().getStringExtra(QUERY));
        super.onSaveInstanceState(outState);
    }
}
