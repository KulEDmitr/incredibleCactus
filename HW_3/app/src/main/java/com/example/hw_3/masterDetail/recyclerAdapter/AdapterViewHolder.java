package com.example.hw_3.masterDetail.recyclerAdapter;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.example.hw_3.R;

class AdapterViewHolder extends RecyclerView.ViewHolder {
    TextView iView;
    TextView contentView;

    AdapterViewHolder(@NonNull View itemView) {
        super(itemView);
        iView = itemView.findViewById(R.id.view);
        contentView = itemView.findViewById(R.id.content);
    }
}