package com.afterapocalypticcrash.oldStructure.view.search;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.EditText;

import com.afterapocalypticcrash.R;
import com.afterapocalypticcrash.oldStructure.presenter.HandToHand;

public class SearchActivity extends AppCompatActivity {
    static final String LOG_TAG = SearchActivity.class.getSimpleName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Log.d(LOG_TAG, "onCreate");

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);

        final Intent intent = new Intent(this, ListActivity.class);

        findViewById(R.id.starter).setOnClickListener(v -> {
            Log.d(LOG_TAG, "onClick (StartSearching)");
            HandToHand.getList().clear();
            String query = ((EditText) findViewById(R.id.query)).getText().toString();
            HandToHand.setKey((!query.isEmpty()) ? (query) : ("monochrome"));
            startActivity(intent);
        });
    }
}
