package com.afterapocalypticcrash.oldStructure.model;

import com.afterapocalypticcrash.oldStructure.data.AllData;
import com.afterapocalypticcrash.oldStructure.data.AppDelegate;
import com.afterapocalypticcrash.oldStructure.data.structures.PictureApiContent;
import com.afterapocalypticcrash.oldStructure.data.structures.PictureBD;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.Flowable;

public class DataModifier {

    public static void deleteData(){
        AllData.getItemMap().clear();
        AllData.getItemsList().clear();
    }

    public static PictureApiContent.Results getItem(int pos) {
        return getCurList().get(pos);
    }

    public static String getItemId(PictureApiContent.Results it) {
        return it.getId();
    }

    public static List<PictureApiContent.Results> getCurList(){
        return AllData.getItemsList();
    }

    public static void setCurData(List<PictureApiContent.Results> cur) {
        addListApiToMap(cur);
        AllData.getItemsList().addAll(cur);
    }

    public static int getCountElements(){
        return AllData.getItemsList().size();
    }

    public static PictureApiContent.Results getMapItem(String id) {
        return AllData.getItemMap().get(id);
    }

    public static Flowable<List<PictureBD>> getDataFromDb() {
        return AppDelegate.getPicDataBase().pictureDao().getFavouriteList();
    }


    public static void addItemToMap(PictureApiContent.Results item) {
        AllData.getItemMap().put(item.getId(), item);
    }

    public static void addListApiToMap(List<PictureApiContent.Results> items){
        for (PictureApiContent.Results it : items) {
            addItemToMap(it);
        }
    }

    public static PictureApiContent.Results fromDbtoApi(PictureBD picDB){
        return new PictureApiContent.Results(picDB.getId(), picDB.getLikes(),
                picDB.getDescription(), picDB.getUsername(), picDB.getName(),
                picDB.getAuthorsProfileImage(), picDB.getUserHtml(),
                picDB.getThumb(), picDB.getRegular());
    }

    public static PictureBD fromApiToDb(PictureApiContent.Results picApi){
        return new PictureBD(picApi.getId(), picApi.getLikes(),
                picApi.getDescription(), picApi.getUser().getUsername(),
                picApi.getUser().getProfile_image().getLarge(),
                picApi.getUser().getLinks().getHtml(), picApi.getUrls().getThumb(),
                picApi.getUrls().getRegular(), picApi.getUser().getName());
    }

    public static List<PictureApiContent.Results> fromDbToApiList(List<PictureBD> items){
        List<PictureApiContent.Results> res = new ArrayList<>();
        for (PictureBD it : items) {
            res.add(fromDbtoApi(it));
        }
        return res;
    }



}
