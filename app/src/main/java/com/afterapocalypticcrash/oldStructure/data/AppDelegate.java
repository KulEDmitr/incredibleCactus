package com.afterapocalypticcrash.oldStructure.data;

import android.app.Application;
import android.arch.persistence.room.Room;
import android.util.Log;

import com.afterapocalypticcrash.oldStructure.model.dao.PicturesDataBase;


public class AppDelegate extends Application {

    private static PicturesDataBase picDataBase;

    @Override
    public void onCreate() {
        Log.d("APP", "onCreate (bd create too)");

        super.onCreate();
        picDataBase = Room.databaseBuilder(this, PicturesDataBase.class,
                "picture_database").build();
    }

    public static PicturesDataBase getPicDataBase() {
        Log.d("APP", "getPicDataBase");
        return picDataBase;
    }
}
