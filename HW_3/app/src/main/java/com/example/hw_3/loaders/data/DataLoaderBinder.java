package com.example.hw_3.loaders.data;

import android.os.Binder;
import android.os.Handler;
import android.os.Looper;

import com.example.hw_3.loaders.OnLoad;

public class DataLoaderBinder extends Binder {
    private final DataLoader loader;

    DataLoaderBinder(DataLoader loader) {
        this.loader = loader;
    }

    public void setCallback(final OnLoad callback) {
        new Handler(Looper.getMainLooper()).post(new Runnable() {
            @Override
            public void run() {
                loader.callBack = callback;
                if (DataLoader.JSON_DATA != null) {
                    callback.onLoad(DataLoader.JSON_DATA);
                }
            }
        });
    }
}