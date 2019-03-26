package com.afterapocalypticcrash.oldStructure.model.dao;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.RoomDatabase;

import com.afterapocalypticcrash.oldStructure.data.structures.PictureBD;

@Database(entities = {PictureBD.class}, version = 1, exportSchema = false)

public abstract class PicturesDataBase extends RoomDatabase {
    public abstract PictureDao pictureDao() ;
}
