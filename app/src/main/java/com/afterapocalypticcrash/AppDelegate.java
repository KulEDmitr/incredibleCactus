package com.afterapocalypticcrash;

import android.app.Application;
import android.arch.persistence.room.Room;
import android.util.Log;

import com.afterapocalypticcrash.favourites.dataBase.PicturesDataBase;


public class AppDelegate extends Application {

    private PicturesDataBase picDataBase;

    @Override
    public void onCreate() {
        Log.d("APP", "onCreate (bd create too)");

        super.onCreate();
        picDataBase = Room.databaseBuilder(this, PicturesDataBase.class,
                "picture_database")/*.allowMainThreadQueries()*/.build();
    }

    public PicturesDataBase getPicDataBase() {
        Log.d("APP", "getPicDataBase");

        return picDataBase;
    }
}
