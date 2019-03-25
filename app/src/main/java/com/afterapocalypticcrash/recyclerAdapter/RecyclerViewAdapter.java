package com.afterapocalypticcrash.recyclerAdapter;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.afterapocalypticcrash.R;
import com.afterapocalypticcrash.pictureDetails.DetailActivity;
import com.afterapocalypticcrash.search.api.PictureApiContent;

import java.util.List;

public class RecyclerViewAdapter extends RecyclerView.Adapter<AdapterViewHolder> {
    private static final String LOG_TAG = RecyclerViewAdapter.class.getSimpleName();

    private List<PictureApiContent.Results> results;

    public RecyclerViewAdapter(List<PictureApiContent.Results> results) {
        Log.d(LOG_TAG, "RecyclerViewAdapter");
        this.results = results;
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

    private final View.OnClickListener itemOnClickListener = view -> {
        PictureApiContent.Results item = (PictureApiContent.Results) view.getTag();
        Context context = view.getContext();
        Intent intent = new Intent(context, DetailActivity.class);
        intent.putExtra("ITEM_ID", item.getId());
        context.startActivity(intent);

    };

    @Override
    public void onBindViewHolder(@NonNull AdapterViewHolder viewHolder, int position) {
        Log.d(LOG_TAG, "onBindViewHolder");

        viewHolder.bind(results.get(position));
        viewHolder.itemView.setOnClickListener(itemOnClickListener);
    }

    @Override
    public int getItemCount() {
        Log.d(LOG_TAG, "getItemCount");

        return results.size();
    }
}