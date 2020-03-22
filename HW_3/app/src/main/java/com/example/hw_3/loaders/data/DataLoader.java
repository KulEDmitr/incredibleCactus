package com.example.hw_3.loaders.data;

import android.app.IntentService;
import android.content.Context;
import android.content.Intent;
import android.os.Handler;
import android.os.IBinder;
import android.os.Looper;
import android.util.Log;

import com.example.hw_3.loaders.OnLoad;
import com.example.hw_3.loaders.UniException;

import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Objects;

public class DataLoader extends IntentService {

    static String LOG_TAG = "DataLoaderLogs";
    static String CURRENT_URL_STR;
    static String JSON_DATA;

    protected OnLoad callBack;

    public DataLoader() {
        super("DataLoader");
    }

    public DataLoader(String name) {
        super(name);
    }

    public static void start(Context context, String urlStr) {
        Intent intent = new Intent(context, DataLoader.class);
        CURRENT_URL_STR = urlStr;
        context.startService(intent);
    }

    @Override
    public IBinder onBind(Intent intent) {
        return new DataLoaderBinder(this);
    }

    @Override
    public boolean onUnbind(Intent intent) {
        callBack = null;
        return super.onUnbind(intent);
    }

    @Override
    protected void onHandleIntent(final Intent intent) {
        if (JSON_DATA == null) {
            try {
                getData();
            } catch (UniException e) {
                Log.e(LOG_TAG, e.getMessage());
            }
        }
        new Handler(Looper.getMainLooper()).post(new Runnable() {
            @Override
            public void run() {
                if (callBack != null) {
                    callBack.onLoad(JSON_DATA);
                }
            }
        });
    }

    private void getData() throws UniException {
        URL request;
        try {
            request = new URL(CURRENT_URL_STR);
        } catch (MalformedURLException e) {
            throw new UniException(e.getMessage());
        }
        createRequest(request);
        if (JSON_DATA.isEmpty()) {
            throw new UniException("got no result");
        }
    }

    private void createRequest(URL request) throws UniException {
        String curData;
        HttpURLConnection curConnection = null;
        try {
            curConnection = (HttpURLConnection) request.openConnection();
            curConnection.connect();
            if (curConnection.getResponseCode() != HttpURLConnection.HTTP_OK) {
                throw new UniException(curConnection.getResponseMessage());
            } else {
                InputStreamReader reader = new InputStreamReader(curConnection.getInputStream());
                StringBuilder allData = new StringBuilder();
                char[] buffer = new char[2048];
                int len;
                while ((len = reader.read(buffer)) > 0) {
                    allData.append(buffer, 0, len);
                }
                curData = allData.toString();
            }
        } catch (IOException e) {
            throw new UniException(e.getMessage());
        } finally {
            Objects.requireNonNull(curConnection).disconnect();
        }
        JSON_DATA = curData;
    }
}