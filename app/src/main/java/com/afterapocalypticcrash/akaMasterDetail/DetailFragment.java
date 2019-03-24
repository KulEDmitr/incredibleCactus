package com.afterapocalypticcrash.akaMasterDetail;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.afterapocalypticcrash.AppDelegate;
import com.afterapocalypticcrash.R;
import com.afterapocalypticcrash.api.AllData;
import com.afterapocalypticcrash.api.PictureApiContent;
import com.afterapocalypticcrash.dataBase.PictureBD;
import com.afterapocalypticcrash.dataBase.PictureDao;
import com.squareup.picasso.Picasso;

import java.util.Objects;

public class DetailFragment extends Fragment {
    private static final String LOG_TAG = DetailFragment.class.getSimpleName();

    public static final String ITEM_ID = "ITEM_ID";

    private PictureApiContent.Results item;
    PictureDao picDao;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null && getArguments().containsKey(ITEM_ID)) {
            item = AllData.getItemMap().get(getArguments().getString(ITEM_ID));
        }

        picDao = ((AppDelegate) Objects.requireNonNull(getActivity()).getApplication())
                .getPicDataBase().pictureDao();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.picture_full_detail, container, false);
        ImageView picImageView = rootView.findViewById(R.id.picture);
        TextView picTextView = rootView.findViewById(R.id.full_description);
        ImageView userImageView = rootView.findViewById(R.id.user_avatar);
        TextView userTextView = rootView.findViewById(R.id.user_info);

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
        userTextView.setOnClickListener(v -> { });

        TextView saver = rootView.findViewById(R.id.saver);
        saver.setOnClickListener(v -> {
            picDao.insertLPic(convertData());
        });
        return rootView;
    }

    private PictureBD convertData() {
        return new PictureBD(item.getId(), item.getUser().getUsername(),
                item.getUser().getProfile_image().getLarge(), item.getUser().getLinks().getHtml(),
                item.getUrls().getThumb(), item.getUrls().getRegular());
    }
}
