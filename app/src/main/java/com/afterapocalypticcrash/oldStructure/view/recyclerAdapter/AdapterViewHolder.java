package com.afterapocalypticcrash.oldStructure.view.recyclerAdapter;

import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.afterapocalypticcrash.R;
import com.afterapocalypticcrash.oldStructure.presenter.HandToHand;

public class AdapterViewHolder extends RecyclerView.ViewHolder {
    private static final String LOG_TAG = AdapterViewHolder.class.getSimpleName();

    private ImageView imView;
    private TextView descrView;

    AdapterViewHolder(View itemView) {
        super(itemView);
        Log.d(LOG_TAG, "AdapterViewHolder");

        imView = itemView.findViewById(R.id.thumb);
        descrView = itemView.findViewById(R.id.description);
    }

    void bind(int position) {
        Log.d(LOG_TAG, "bind");
        HandToHand.doBinding( descrView, imView, itemView, position);
    }
}
