package com.afterapocalypticcrash.oldStructure.model;

import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.afterapocalypticcrash.oldStructure.data.structures.PictureApiContent;
import com.afterapocalypticcrash.oldStructure.presenter.HandToHand;



public class RecyclerLoader {

    public static void bind(TextView descrView,
                            ImageView imView, View itemView, int position) {
        PictureApiContent.Results cur = HandToHand.getItem(position);
        descrView.setText(cur.toString());
        HandToHand.piturePrevLoad(imView, cur.getId());
        itemView.setTag(cur);
    }

    public static void getQuery(Fragment fr, RecyclerView list) {
        if (HandToHand.getItemCount() > 0) {
            HandToHand.setRecycleView(fr, list);
        } else {
            HandToHand.getCurData(fr, list);
        }
    }

    public static void setupData(Fragment fr, RecyclerView list) {
        if (HandToHand.getItemCount() > 0) {
            HandToHand.setRecycleView(fr, list);
        } else {
            HandToHand.getFavourites(fr, list);
        }
    }
}
