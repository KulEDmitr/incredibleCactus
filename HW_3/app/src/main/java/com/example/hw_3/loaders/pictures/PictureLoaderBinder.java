package com.example.hw_3.loaders.pictures;

import android.os.Binder;
import android.os.Handler;
import android.os.Looper;

import com.example.hw_3.loaders.OnLoad;


public class PictureLoaderBinder extends Binder {
    private final PictureLoader loader;

    PictureLoaderBinder(PictureLoader loader) {
        this.loader = loader;
    }

    public void setCallBack(final OnLoad callback) {
        new Handler(Looper.getMainLooper()).post(new Runnable() {
            @Override
            public void run() {
                loader.callBack = callback;
                if (loader.CASH_PATH != null) {
                    callback.onLoad(loader.CASH_PATH);
                }
            }
        });
    }
}