package com.afterapocalypticcrash.oldStructure.view.recyclerAdapter;

import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.afterapocalypticcrash.R;
import com.afterapocalypticcrash.oldStructure.data.structures.PictureApiContent;
import com.afterapocalypticcrash.oldStructure.presenter.HandToHand;

import java.util.List;

public class RecyclerViewAdapter extends RecyclerView.Adapter<AdapterViewHolder> {
    private static final String LOG_TAG = RecyclerViewAdapter.class.getSimpleName();

    public RecyclerViewAdapter() {
        Log.d(LOG_TAG, "RecyclerViewAdapter");
    }

    @NonNull
    @Override
    public AdapterViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int i) {
        Log.d(LOG_TAG, "onCreateViewHolder");

        View view = LayoutInflater
                .from(parent.getContext())
                .inflate(R.layout.picture_list_item, parent, false);
        return new AdapterViewHolder(view);
    }


    @Override
    public void onBindViewHolder(@NonNull AdapterViewHolder viewHolder, int position) {
        Log.d(LOG_TAG, "onBindViewHolder");

        viewHolder.bind(position);
    }

    @Override
    public int getItemCount() {
        return HandToHand.getItemCount();
    }

}