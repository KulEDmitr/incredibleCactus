package com.example.hw_3.masterDetail;

import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.content.res.Configuration;
import android.os.Bundle;
import android.os.IBinder;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;

import com.example.hw_3.R;
import com.example.hw_3.loaders.OnLoad;
import com.example.hw_3.loaders.data.*;
import com.example.hw_3.pictureItems.PictureContent;
import com.example.hw_3.masterDetail.recyclerAdapter.RecyclerViewAdapter;


public class ItemListActivity extends AppCompatActivity {

    String MAIN_URL = "https://api.unsplash.com/search/photos/?query=monochrome&per_page=35&client_id=bdd50ffe92fce77f51ece58c469f6a7867781199430eb9d13a1c90ce66a87113";

    RecyclerView recyclerView;
    private RecyclerViewAdapter adapter;

    ServiceConnection serviceConnection = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            ((DataLoaderBinder) service).setCallback(new OnLoad() {
                @Override
                public void onLoad(String result) {
                    adapter.parseJson(result);
                }
            });
        }

        @Override
        public void onServiceDisconnected(ComponentName name) {
        }
    };

    private boolean mTwoPane;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_item_list);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        toolbar.setTitle(getTitle());

        if (getResources().getConfiguration().orientation == Configuration.ORIENTATION_LANDSCAPE) {
            mTwoPane = true;
        }

        recyclerView = findViewById(R.id.item_list);
        setupRecyclerView(recyclerView);
        DataLoader.start(this, MAIN_URL);
        bindService(new Intent(this, DataLoader.class), serviceConnection, 0);

    }

    private void setupRecyclerView(@NonNull RecyclerView recyclerView) {
        adapter = new RecyclerViewAdapter(this, mTwoPane);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
    }

    public void onDestroy() {
        unbindService(serviceConnection);
        super.onDestroy();
    }
}