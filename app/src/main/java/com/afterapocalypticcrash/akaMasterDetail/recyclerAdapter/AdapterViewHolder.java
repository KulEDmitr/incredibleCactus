package com.afterapocalypticcrash.akaMasterDetail.recyclerAdapter;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.afterapocalypticcrash.R;
import com.afterapocalypticcrash.api.PictureApiContent;
import com.squareup.picasso.Picasso;

class AdapterViewHolder extends RecyclerView.ViewHolder {
    private static final String LOG_TAG = AdapterViewHolder.class.getSimpleName();

    private ImageView imView;
    private TextView descrView;

    AdapterViewHolder(View itemView) {
        super(itemView);
        imView = itemView.findViewById(R.id.thumb);
        descrView = itemView.findViewById(R.id.description);
    }

    void bind(PictureApiContent.Results item) {
        descrView.setText(item.toString());
        Picasso.with(descrView.getContext())
                .load(item.getUrls().getThumb())
                .into(imView);
        itemView.setTag(item);
    }
}
