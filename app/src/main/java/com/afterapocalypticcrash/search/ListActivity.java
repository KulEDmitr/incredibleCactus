package com.afterapocalypticcrash.search;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import com.afterapocalypticcrash.R;


public class ListActivity extends AppCompatActivity {
    static final String LOG_TAG = ListActivity.class.getSimpleName();

    static final String QUERY = "QUERY";
    static final String RESULT_LIST = "RESULT_LIST";

    @Override
    public void onCreate(Bundle savedInstanceState) {
        Log.d(LOG_TAG, "onCreate");

        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_list);

        if (savedInstanceState == null) {
            ListFragment fr = new ListFragment();
            Bundle bundle = new Bundle();
            bundle.putParcelableArrayList(RESULT_LIST,
                    getIntent().getParcelableArrayListExtra(RESULT_LIST));
            bundle.putString(QUERY, getIntent().getStringExtra(QUERY));
            fr.setArguments(bundle);
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.res_list_fragment, fr)
                    .commitAllowingStateLoss();
        } else {
            getIntent().putExtra(QUERY, savedInstanceState.getString(QUERY));
            getIntent().putParcelableArrayListExtra(RESULT_LIST,
                    savedInstanceState.getParcelableArrayList(RESULT_LIST));

        }
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        Log.d(LOG_TAG, "onSaveInstanceState");
        super.onSaveInstanceState(outState);
        outState.putString(QUERY, getIntent().getStringExtra(QUERY));
        outState.putParcelableArrayList(RESULT_LIST, getIntent()
                .getParcelableArrayListExtra(RESULT_LIST));
    }
}
