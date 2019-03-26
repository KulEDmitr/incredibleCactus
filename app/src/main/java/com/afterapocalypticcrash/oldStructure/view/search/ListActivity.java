package com.afterapocalypticcrash.oldStructure.view.search;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import com.afterapocalypticcrash.R;
import com.afterapocalypticcrash.oldStructure.presenter.HandToHand;


public class ListActivity extends AppCompatActivity {
    static final String LOG_TAG = ListActivity.class.getSimpleName();

    static final String QUERY = "QUERY";

    @Override
    public void onCreate(Bundle savedInstanceState) {
        Log.d(LOG_TAG, "onCreate");

        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_list);

        if (HandToHand.getItemCount() == 0) {
            ListFragment fr = new ListFragment();
            fr.setArguments(new Bundle());
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.res_list_fragment, fr)
                    .commitAllowingStateLoss();
        }
    }
}
