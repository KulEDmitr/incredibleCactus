package com.afterapocalypticcrash.dataBase;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;

import java.util.List;

@Dao
public interface PictureDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertLPic(PictureBD pic);

    @Query("SELECT * FROM favourites") //получаем лист по запросу
    List<PictureBD> getFavouriteList ();

    @Delete
    void deletePicture (PictureBD pic);//удаляем одну картинку...удаление из избранного

}