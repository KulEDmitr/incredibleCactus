package com.afterapocalypticcrash.oldStructure.view.pictureDetails;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.afterapocalypticcrash.oldStructure.data.AppDelegate;
import com.afterapocalypticcrash.R;
import com.afterapocalypticcrash.oldStructure.data.AllData;
import com.afterapocalypticcrash.oldStructure.model.dao.PictureDao;
import com.afterapocalypticcrash.oldStructure.data.structures.PictureApiContent;
import com.afterapocalypticcrash.oldStructure.data.structures.PictureBD;
import com.afterapocalypticcrash.oldStructure.presenter.HandToHand;
import com.squareup.picasso.Picasso;

import java.util.Objects;

public class DetailFragment extends Fragment {
    private static final String LOG_TAG = DetailFragment.class.getSimpleName();

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        Log.d(LOG_TAG, "onCreateView");

        View rootV = inflater.inflate(R.layout.picture_full_detail, container, false);

        setData(rootV, rootV.findViewById(R.id.picture),
                rootV.findViewById(R.id.full_description),
                rootV.findViewById(R.id.user_avatar),
                rootV.findViewById(R.id.user_info));

        TextView deleter = rootV.findViewById(R.id.deleter);
        TextView saver = rootV.findViewById(R.id.saver);

        HandToHand.initDeleter(deleter, saver, HandToHand.getKey());
        HandToHand.initSaver(deleter, saver, HandToHand.getKey());

        return rootV;
    }



    private void setData(View rootView, ImageView picImageView, TextView picTextView,
                         ImageView userImageView, TextView userTextView) {
        Log.d(LOG_TAG, "setData");

        picImageView.setContentDescription(HandToHand.getItem(HandToHand.getKey()).toString());
        HandToHand.pitureFullLoad(picImageView, HandToHand.getKey());
        picTextView.setText(HandToHand.getItem(HandToHand.getKey()).getFullDescription());

        userImageView.setContentDescription(HandToHand.getItem(HandToHand.getKey()).getId());
        HandToHand.userImageLoad(userImageView, HandToHand.getKey());
        userTextView.setText(HandToHand.getUserInfo(HandToHand.getKey()));

        userTextView.setOnClickListener(v -> {
            Intent intent = new Intent(Intent.ACTION_VIEW);
            intent.setData(Uri.parse(HandToHand.getHtml(HandToHand.getKey())));
            rootView.getContext().startActivity(intent);
        });
    }

    @Override
    public void onDestroy() {
        Picasso.with(getActivity()).cancelTag(DetailActivity.class);
        super.onDestroy();
    }
}
