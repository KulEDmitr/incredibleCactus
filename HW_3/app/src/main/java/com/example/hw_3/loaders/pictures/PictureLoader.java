package com.example.hw_3.loaders.pictures;

import android.app.IntentService;
import android.content.Context;
import android.content.Intent;
import android.os.Handler;
import android.os.IBinder;
import android.os.Looper;
import android.util.Log;

import com.example.hw_3.loaders.OnLoad;
import com.example.hw_3.loaders.UniException;
import com.example.hw_3.pictureItems.PictureContent;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URL;

public class PictureLoader extends IntentService {

    String CASH_PATH;

    public static PictureContent.PictureItem ITEM;
    protected OnLoad callBack;

    public PictureLoader() {
        super("PictureLoader");
    }

    public PictureLoader(String name) {
        super(name);
    }

    @Override
    protected void onHandleIntent(Intent intent) {
        try {
            getPhoto(ITEM.id, ITEM.preview);
        } catch (UniException e) {
            e.getMessage();
        }

        new Handler(Looper.getMainLooper()).post(new Runnable() {
            @Override
            public void run() {
                if (callBack != null) {
                    callBack.onLoad(CASH_PATH);
                }
            }
        });
    }

    public static void start(Context context, PictureContent.PictureItem item) {
        ITEM = item;
        context.startService(new Intent(context, PictureLoader.class));
    }

    @Override
    public IBinder onBind(Intent intent) {
        return new PictureLoaderBinder(this);
    }

    @Override
    public boolean onUnbind(Intent intent) {
        callBack = null;
        return super.onUnbind(intent);
    }


    private void getPhoto(String path, String download) throws UniException {
        String cashPath = getCacheDir()
                .getAbsolutePath()
                .concat("/").concat(path).concat("_mono.jpg");
        File file = new File(cashPath);
        if (!file.exists()) {
            if (!file.getParentFile().exists()) {
                boolean SuccessCreatedDir = file.getParentFile().mkdirs();
            }
            int BUFFER_SIZE = 1024;
            byte[] buffer = new byte[BUFFER_SIZE];
            int len;
            InputStream is;
            OutputStream cachedFile;
            try {
                is = new URL(download).openStream();
                try {
                    cachedFile = new BufferedOutputStream(new FileOutputStream(cashPath));
                } catch (FileNotFoundException e) {
                    throw new UniException("it's impossible");
                }
                while ((len = is.read(buffer)) > 0) {
                    cachedFile.write(buffer, 0, len);
                }
                cachedFile.close();
                is.close();
            } catch (IOException e) {
                throw new UniException(e.getMessage());
            }
        }
        CASH_PATH = cashPath;
    }

    public int onStartCommand(Intent intent, int flags, int startId) {
        return super.onStartCommand(intent, flags, startId);
    }

    public void onStart(Intent intent, int startId) {
        super.onStart(intent, startId);
    }
}