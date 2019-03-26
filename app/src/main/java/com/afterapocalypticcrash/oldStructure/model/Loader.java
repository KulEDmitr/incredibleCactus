package com.afterapocalypticcrash.oldStructure.model;

import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.widget.ImageView;

import com.afterapocalypticcrash.oldStructure.data.AllData;
import com.afterapocalypticcrash.oldStructure.data.AppDelegate;
import com.afterapocalypticcrash.oldStructure.data.structures.PictureApiContent;
import com.afterapocalypticcrash.oldStructure.data.structures.PictureBD;
import com.afterapocalypticcrash.oldStructure.model.dao.PictureDao;
import com.afterapocalypticcrash.oldStructure.presenter.HandToHand;
import com.squareup.picasso.Picasso;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;

import static com.afterapocalypticcrash.oldStructure.model.DataModifier.fromDbToApiList;
import static com.afterapocalypticcrash.oldStructure.model.api.RetrofitModule.getApiInstance;

public class Loader {

    public static String getPrevLink(PictureApiContent.Results item) {
        return item.getUrls().getThumb();
    }

    public static String getFullLink(PictureApiContent.Results item) {
        return item.getUrls().getRegular();
    }

    public static String getUserLink(PictureApiContent.Results item) {
        return item.getUser().getProfile_image().getLarge();
    }

    public static String getUserWebLink(PictureApiContent.Results item) {
        return item.getUser().getLinks().getHtml();
    }

    public static void load(ImageView imView, String link) {
        Picasso.with(imView.getContext())
                .load(link)
                .into(imView);
    }

    public static void getFavData(Fragment fr, RecyclerView list) {
        Disposable subscribe = DataModifier.getDataFromDb()
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(pictureBDS -> {
                    if (pictureBDS.size() > 0) {
                        HandToHand.updateData(fromDbToApiList(pictureBDS));
                        HandToHand.setRecycleView(fr, list);
                    } else {
                        HandToHand.delData();
                    }
                }, Throwable::printStackTrace);
        subscribe.dispose();
    }

    public static void getData(Fragment fr, RecyclerView list) {
        Disposable subscribe = getApiInstance()
                .getResults(AllData.getCurKey())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(pictures -> {
                            if (pictures.getResults().size() > 0) {
                                HandToHand.updateData(pictures.getResults());
                                HandToHand.setRecycleView(fr, list);
                            } else {
                                HandToHand.delData();
                            }
                        }, Throwable::printStackTrace
                );
        subscribe.dispose();
    }

    public static void deleteImage(PictureBD favPic) {
        PictureDao favPicDao = AppDelegate.getPicDataBase().pictureDao();
        (new Thread(() -> favPicDao.deletePic(favPic))).start();
    }

    public static void saveImage(PictureBD favPic) {
        PictureDao favPicDao = AppDelegate.getPicDataBase().pictureDao();
        (new Thread(() -> favPicDao.insert(favPic))).start();
    }
}
