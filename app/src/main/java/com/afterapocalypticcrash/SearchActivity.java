package com.afterapocalypticcrash;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.EditText;

import com.afterapocalypticcrash.akaMasterDetail.ListActivity;

public class SearchActivity extends AppCompatActivity {
    static final String LOG_TAG = SearchActivity.class.getSimpleName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Log.w(LOG_TAG, "onCreate");

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);

        final Intent intent = new Intent(this, ListActivity.class);

        findViewById(R.id.starter).setOnClickListener(v -> {
            Log.w(LOG_TAG, "onClick (StartSearching)");

            String query = ((EditText) findViewById(R.id.query)).getText().toString();
            if (!query.isEmpty()) {
                intent.putExtra("QUERY", query);
                startActivity(intent);
            }
        });
    }

}