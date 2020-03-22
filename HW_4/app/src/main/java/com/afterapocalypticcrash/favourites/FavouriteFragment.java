package com.afterapocalypticcrash.favourites;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.afterapocalypticcrash.AppDelegate;
import com.afterapocalypticcrash.R;
import com.afterapocalypticcrash.favourites.dataBase.PictureBD;
import com.afterapocalypticcrash.recyclerAdapter.RecyclerViewAdapter;
import com.afterapocalypticcrash.pictureDetails.AllData;
import com.afterapocalypticcrash.search.api.PictureApiContent;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;

public class FavouriteFragment extends Fragment {
    static final String LOG_TAG = FavouriteFragment.class.getSimpleName();
    private static String FAVOURITE_LIST = "FAVOURITE_LIST";

    RecyclerView list;
    private Disposable subscribe;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        Log.e(LOG_TAG, "onCreate");
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        Log.e(LOG_TAG, "onCreateView");

        View view = inflater.inflate(R.layout.activity_pictures_list, container, false);
        list = view.findViewById(R.id.res_list);
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        Log.e(LOG_TAG, "onActivityCreated");

        super.onActivityCreated(savedInstanceState);

        if (savedInstanceState != null) {
            getActivity().getIntent()
                    .putParcelableArrayListExtra(FAVOURITE_LIST,
                            savedInstanceState.getParcelableArrayList(FAVOURITE_LIST));
            setupRecyclerView(savedInstanceState.getParcelableArrayList(FAVOURITE_LIST));
        } else {
            getFavData();
        }
    }

    private void getFavData() {
        Log.e(LOG_TAG, "getData");

        subscribe = ((AppDelegate) getActivity().getApplication())
                .getPicDataBase().pictureDao().getFavouriteList()
                .observeOn(AndroidSchedulers.mainThread()).subscribe(pictureBDS -> {
                    if (pictureBDS.size() > 0) {
                        PictureApiContent.Results item;
                        List<PictureApiContent.Results> fav = new ArrayList<>();
                        for (PictureBD cur : pictureBDS) {
                            item = new PictureApiContent.Results(cur.getId(), cur.getLikes(),
                                    cur.getDescription(), cur.getUsername(), cur.getName(),
                                    cur.getAuthorsProfileImage(), cur.getUserHtml(),
                                    cur.getThumb(), cur.getRegular());
                            AllData.addItem(item);
                            fav.add(item);
                        }
                        setupRecyclerView(fav);
                    } else {
                        Toast.makeText(getContext(),
                                "there are no results", Toast.LENGTH_LONG).show();
                    }
                }, throwable -> {
                    Toast.makeText(getContext(),
                            "some error occured, \nPlease, repeat your query",
                            Toast.LENGTH_LONG).show();
                    throwable.printStackTrace();
                });
    }

    private void setupRecyclerView(List<PictureApiContent.Results> res) {
        Log.d(LOG_TAG, "setupRecyclerView");
        getActivity().getIntent()
                .putParcelableArrayListExtra(FAVOURITE_LIST, new ArrayList<>(res));
        list.setAdapter(new RecyclerViewAdapter(res));
        list.setLayoutManager(new LinearLayoutManager(getActivity()));

    }

    @Override
    public void onSaveInstanceState(@NonNull Bundle outState) {
        Log.d(LOG_TAG, "onSaveInstanceState");
        super.onSaveInstanceState(outState);
        outState.putParcelableArrayList(FAVOURITE_LIST, getActivity().getIntent()
                .getParcelableArrayListExtra(FAVOURITE_LIST));
    }

    @Override
    public void onDestroyView() {
        Log.d(LOG_TAG, "onDestroyView");
        if (subscribe != null) {
            subscribe.dispose();
        }
        if (isVisible()) {
            Picasso.with(getActivity()).cancelTag(FavouriteActivity.class);
        }
        super.onDestroyView();

    }
}
