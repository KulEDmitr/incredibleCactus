package com.example.hw_3.masterDetail;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.graphics.BitmapFactory;
import android.os.IBinder;
import android.support.annotation.NonNull;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.hw_3.R;
import com.example.hw_3.loaders.OnLoad;
import com.example.hw_3.loaders.pictures.*;
import com.example.hw_3.pictureItems.PictureContent;

import java.util.Objects;

public class ItemDetailFragment extends Fragment {

    public static final String ARG_ITEM_ID = "item_id";

    private PictureContent.PictureItem item;

    ImageView imageView;
    TextView textView;

    private ServiceConnection serviceConnection = new ServiceConnection() {
        @Override
        public void onServiceConnected(final ComponentName name, IBinder service) {
            ((PictureLoaderBinder) service).setCallBack(new OnLoad() {
                @Override
                public void onLoad(String data) {
                    if (data != null) {
                        imageView.setImageBitmap(BitmapFactory.decodeFile(data));
                    }
                }
            });
        }

        @Override
        public void onServiceDisconnected(ComponentName name) { }
    };

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null && getArguments().containsKey(ARG_ITEM_ID)) {
            item = PictureContent.ITEM_MAP.get(getArguments().getString(ARG_ITEM_ID));
        }
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.item_detail, container, false);
        imageView = rootView.findViewById(R.id.image_view);
        textView = rootView.findViewById(R.id.image_description);

        PictureLoader.start(rootView.getContext(), item);

        imageView.setContentDescription(item.description);
        textView.setText(item.description);
        return rootView;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        Objects.requireNonNull(getContext())
                .bindService(new Intent(getContext(), PictureLoader.class), serviceConnection, 0);
    }

    @Override
    public void onDetach() {
        Objects.requireNonNull(getContext()).unbindService(serviceConnection);
        super.onDetach();
    }
}