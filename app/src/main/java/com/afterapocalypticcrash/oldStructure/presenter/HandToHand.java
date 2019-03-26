package com.afterapocalypticcrash.oldStructure.presenter;

import android.content.Context;
import android.content.Intent;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.afterapocalypticcrash.oldStructure.data.AllData;
import com.afterapocalypticcrash.oldStructure.data.structures.PictureApiContent;
import com.afterapocalypticcrash.oldStructure.data.structures.PictureBD;
import com.afterapocalypticcrash.oldStructure.model.DataModifier;
import com.afterapocalypticcrash.oldStructure.model.Loader;
import com.afterapocalypticcrash.oldStructure.model.RecyclerLoader;
import com.afterapocalypticcrash.oldStructure.view.pictureDetails.DetailActivity;
import com.afterapocalypticcrash.oldStructure.view.recyclerAdapter.RecyclerViewAdapter;

import java.util.List;
import java.util.Map;

import static com.afterapocalypticcrash.oldStructure.model.DataModifier.getItemId;

public class HandToHand {
//use data

    public static void setKey(String query) {
        AllData.setCurKey(query);
    }

    public static String getKey() {
        return AllData.getCurKey();
    }

    public static void setId(String query) {
        AllData.setCurKey(query);
    }

    public static String getId() {
        return AllData.getCurKey();
    }


    public static Map<String, PictureApiContent.Results> getMap() {
        return AllData.getItemMap();
    }

    public static List<PictureApiContent.Results> getList() {
        return AllData.getItemsList();
    }

    public static PictureApiContent.Results getItem(String key) {
        return getMap().get(key);
    }

    public static boolean contains(String key) {
        return (getMap() != null) && getMap().containsKey(key);
    }

    public static String getUserInfo(String id){
        return getItem(getKey()).getUserInfo();
    }

//

//use data modification

    public static void updateData(List<PictureApiContent.Results> s) {
        DataModifier.setCurData(s);
    }

    public static void delData() {
        DataModifier.deleteData();
    }

    public static int getItemCount() {
        return DataModifier.getCountElements();
    }

    private static PictureBD returnElement(PictureApiContent.Results pic) {
        return DataModifier.fromApiToDb(pic);
    }

    public static PictureApiContent.Results getItem(int pos) {
        return DataModifier.getItem(pos);
    }

//   use loader

    public static void getFavourites(Fragment fr, RecyclerView list) {
        Loader.getFavData(fr, list);
    }

    public static void getCurData(Fragment fr, RecyclerView list){
        Loader.getData(fr, list);
    }

    public static void piturePrevLoad(ImageView im, String id) {
        Loader.load(im,Loader.getPrevLink(getItem(id)));
    }

    public static void pitureFullLoad(ImageView im, String id) {
        Loader.load(im,Loader.getFullLink(getItem(id)));
    }

    public static void userImageLoad(ImageView im, String id) {
        Loader.load(im,Loader.getUserLink(getItem(id)));
    }

    public static String getHtml(String id) {
        return Loader.getUserWebLink(getItem(getKey()));
    }

//


//use recycler loader

    public static void setRecycleView(Fragment fr, RecyclerView list) {
        list.setAdapter(new RecyclerViewAdapter());
        list.setLayoutManager(new LinearLayoutManager(fr.getActivity()));
    }

    public static final View.OnClickListener itemOnClickListener = (view -> {
        Context context = view.getContext();
        Intent intent = new Intent(context, DetailActivity.class);
        intent.putExtra("ITEM_ID", getItemId((PictureApiContent.Results) view.getTag()));
        context.startActivity(intent);
    });

    public static void doBinding(TextView descrView,
                                 ImageView imView, View itemView, int position){
        RecyclerLoader.bind(descrView, imView, itemView, position);
        itemView.setOnClickListener(itemOnClickListener);
    }

    public static void setData(Fragment fr, RecyclerView list){
        RecyclerLoader.setupData(fr, list);
    }

//




    public static void initDeleter(TextView deleter, TextView saver, String id) {
        deleter.setOnClickListener(v -> {
            Loader.deleteImage(returnElement(getItem(id)));
            deleter.setText("done");
            saver.setText("Save picture to favourites");
        });
    }

    public static void initSaver(TextView deleter, TextView saver, String id) {
        saver.setOnClickListener(v -> {
            Loader.saveImage(returnElement(getItem(id)));
            saver.setText("done");
            deleter.setText("Delete picture from favourites");
        });
    }
}


