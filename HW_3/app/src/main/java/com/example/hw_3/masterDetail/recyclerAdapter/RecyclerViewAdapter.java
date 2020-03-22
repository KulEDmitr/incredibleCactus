package com.example.hw_3.masterDetail.recyclerAdapter;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.hw_3.loaders.UniException;
import com.example.hw_3.masterDetail.*;
import com.example.hw_3.R;
import com.example.hw_3.pictureItems.PictureContent;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import static com.example.hw_3.pictureItems.PictureContent.ITEMS;
import static com.example.hw_3.pictureItems.PictureContent.addItem;

public class RecyclerViewAdapter extends RecyclerView.Adapter<AdapterViewHolder> {

    private final ItemListActivity parentActivity;
    private List<PictureContent.PictureItem> items = new ArrayList<>();
    private final boolean twoPane;

    public RecyclerViewAdapter(ItemListActivity parentActivity, boolean twoPane) {
        this.parentActivity = parentActivity;
        this.twoPane = twoPane;
    }

    private final View.OnClickListener itemOnClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {

            PictureContent.PictureItem item = (PictureContent.PictureItem) view.getTag();
            if (twoPane) {
                Bundle arguments = new Bundle();
                arguments.putString(ItemDetailFragment.ARG_ITEM_ID, item.id);
                ItemDetailFragment fragment = new ItemDetailFragment();
                fragment.setArguments(arguments);

                parentActivity.getSupportFragmentManager()
                        .beginTransaction()
                        .replace(R.id.item_detail_container, fragment)
                        .commit();
            } else {
                Context context = view.getContext();
                Intent intent = new Intent(context, ItemDetailActivity.class);
                intent.putExtra(ItemDetailFragment.ARG_ITEM_ID, item.id);

                context.startActivity(intent);
            }
        }
    };

    public void parseJson(String jsonText) {
        String preview;
        String description;
        try {
            if (jsonText == null) {
                throw new UniException("nothing to parse");
            }
            JSONObject jsonObj = new JSONObject(jsonText);
            JSONArray jsonArr = jsonObj.getJSONArray("results");
            for (int i = 0; i < Math.min(40, jsonArr.length()) - 1; ++i) {
                try {
                    jsonObj = jsonArr.getJSONObject(i);
                    description = jsonObj.getString("description");
                    preview = jsonObj.getJSONObject("urls").getString("thumb");
                } catch (UnsupportedOperationException e) {
                    throw new UniException("Missing json option: " + e.getMessage());
                }
                description = (description.equals("null")) ? "This photo has no description.\n just enjoy the picture" : description;
                addItem(PictureContent.createPictureItem(i, description, preview));
            }
        } catch (JSONException e) {
            e.getMessage();
        } catch (UniException e) {
            e.printStackTrace();
        }
        if (items.isEmpty()) {
            items.addAll(ITEMS);
        }
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public AdapterViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater
                .from(parent.getContext())
                .inflate(R.layout.item_list_content, parent, false);
        return new AdapterViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final AdapterViewHolder viewHolder, int position) {
        viewHolder.iView.setText(items.get(position).id);
        viewHolder.contentView.setText(items.get(position).toString());
        viewHolder.itemView.setTag(items.get(position));
        viewHolder.itemView.setOnClickListener(itemOnClickListener);
    }

    @Override
    public int getItemCount() {
        if (items != null) {
            return items.size();
        }
        return 0;
    }
}