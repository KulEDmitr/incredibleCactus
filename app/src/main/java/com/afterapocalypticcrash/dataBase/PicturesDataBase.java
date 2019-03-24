package com.afterapocalypticcrash.dataBase;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.RoomDatabase;

@Database(entities = {PictureBD.class}, version = 1, exportSchema = false)

public abstract class PicturesDataBase extends RoomDatabase {
    public abstract PictureDao pictureDao() ;
}
