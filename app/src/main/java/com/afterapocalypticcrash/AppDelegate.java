package com.afterapocalypticcrash;

import android.app.Application;
import android.arch.persistence.room.Room;

import com.afterapocalypticcrash.dataBase.PicturesDataBase;

public class AppDelegate extends Application {

    private PicturesDataBase picDataBase;

    @Override
    public void onCreate() {
        super.onCreate();

        picDataBase = Room.databaseBuilder(this, PicturesDataBase.class,
                "picture_database").build();
    }

    public PicturesDataBase getPicDataBase() {
        return picDataBase;
    }
}
