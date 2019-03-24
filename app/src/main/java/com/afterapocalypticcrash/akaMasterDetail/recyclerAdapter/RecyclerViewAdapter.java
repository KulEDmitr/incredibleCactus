package com.afterapocalypticcrash.akaMasterDetail.recyclerAdapter;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.afterapocalypticcrash.R;
import com.afterapocalypticcrash.akaMasterDetail.DetailActivity;
import com.afterapocalypticcrash.akaMasterDetail.DetailFragment;
import com.afterapocalypticcrash.akaMasterDetail.ListActivity;
import com.afterapocalypticcrash.api.PictureApiContent;

import java.util.List;

public class RecyclerViewAdapter extends RecyclerView.Adapter<AdapterViewHolder> {
    private static final String LOG_TAG = RecyclerViewAdapter.class.getSimpleName();


    private ListActivity parentActivity;
    private List<PictureApiContent.Results> results;

    public RecyclerViewAdapter(List<PictureApiContent.Results> results) {
        this.results = results;
    }

    @NonNull
    @Override
    public AdapterViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int i) {
        View view = LayoutInflater
                .from(parent.getContext())
                .inflate(R.layout.picture_list_item, parent, false);
        return new AdapterViewHolder(view);
    }

    private final View.OnClickListener itemOnClickListener = view -> {
        PictureApiContent.Results item = (PictureApiContent.Results) view.getTag();
//        DetailFragment fragment = new DetailFragment();
//        Bundle bundle = new Bundle();
//        bundle.putString("ARG_ITEM_ID", item.getId());
//        fragment.setArguments(bundle);
//        View land = view.findViewById(R.id.item_detail_container2);
//        if (land != null) {
//            land.setVisibility(View.VISIBLE);
//            parentActivity.getSupportFragmentManager().beginTransaction()
//                    .replace(R.id.item_detail_container2, fragment)
//                    .commitAllowingStateLoss();
//        } else {
            Context context = view.getContext();
            Intent intent = new Intent(context, DetailActivity.class);
            intent.putExtra("ITEM_ID", item.getId());
            context.startActivity(intent);
//        }
    };

    @Override
    public void onBindViewHolder(@NonNull AdapterViewHolder viewHolder, int position) {
        viewHolder.bind(results.get(position));
        viewHolder.itemView.setOnClickListener(itemOnClickListener);
    }

    @Override
    public int getItemCount() {
        return results.size();
    }
}