package com.afterapocalypticcrash.pictureDetails;

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

import com.afterapocalypticcrash.AppDelegate;
import com.afterapocalypticcrash.R;
import com.afterapocalypticcrash.favourites.dataBase.PictureDao;
import com.afterapocalypticcrash.search.api.PictureApiContent;
import com.afterapocalypticcrash.favourites.dataBase.PictureBD;
import com.squareup.picasso.Picasso;

import java.util.Objects;

public class DetailFragment extends Fragment {
    private static final String LOG_TAG = DetailFragment.class.getSimpleName();
    private static final String ITEM_ID = "ITEM_ID";

    private PictureApiContent.Results item;
    private PictureDao favPicDao;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        Log.d(LOG_TAG, "onCreate");
        super.onCreate(savedInstanceState);
        if (getArguments() != null && getArguments().containsKey(ITEM_ID)) {
            item = AllData.getItem(getArguments().getString(ITEM_ID));
        }
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        Log.d(LOG_TAG, "onActivityCreated");
        super.onActivityCreated(savedInstanceState);
        favPicDao = ((AppDelegate) Objects.requireNonNull(getActivity())
                .getApplication()).getPicDataBase().pictureDao();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        Log.d(LOG_TAG, "onCreateView");

        View rootV = inflater.inflate(R.layout.picture_full_detail, container, false);

        setData(rootV, rootV.findViewById(R.id.picture),
                rootV.findViewById(R.id.full_description),
                rootV.findViewById(R.id.user_avatar),
                rootV.findViewById(R.id.user_info));

        PictureBD favPic = returnElement();

        TextView deleter = rootV.findViewById(R.id.deleter);
        TextView saver = rootV.findViewById(R.id.saver);

        deleter.setOnClickListener(v -> {
            (new Thread(() -> favPicDao.deletePic(favPic))).start();
            deleter.setText("done");
            saver.setText("Save picture to favourites");
        });

        saver.setOnClickListener(v -> {
            (new Thread(() -> favPicDao.insert(favPic))).start();
            saver.setText("done");
            deleter.setText("Delete picture from favourites");
        });

        return rootV;
    }

    private PictureBD returnElement() {
        Log.d(LOG_TAG, "returnElement");
        return new PictureBD(item.getId(), item.getLikes(),
                item.getDescription(), item.getUser().getUsername(),
                item.getUser().getProfile_image().getLarge(),
                item.getUser().getLinks().getHtml(), item.getUrls().getThumb(),
                item.getUrls().getRegular(), item.getUser().getName());
    }


    private void setData(View rootView, ImageView picImageView, TextView picTextView,
                         ImageView userImageView, TextView userTextView) {
        Log.d(LOG_TAG, "setData");

        picImageView.setContentDescription(item.toString());
        Picasso.with(rootView.getContext())
                .load(item.getUrls().getRegular())
                .into(picImageView);
        picTextView.setText(item.getFullDescription());

        userImageView.setContentDescription(item.getId());
        Picasso.with(rootView.getContext())
                .load(item.getUser().getProfile_image().getLarge())
                .into(userImageView);
        userTextView.setText(item.getUserInfo());

        userTextView.setOnClickListener(v -> {
            Intent intent = new Intent(Intent.ACTION_VIEW);
            intent.setData(Uri.parse(item.getUser().getLinks().getHtml()));
            rootView.getContext().startActivity(intent);
        });
    }

    @Override
    public void onDestroyView() {
        Log.d(LOG_TAG, "onDestroyView");

        super.onDestroyView();
        if (isVisible()) {
            Picasso.with(getActivity()).cancelTag(DetailActivity.class);
        }
    }
}
