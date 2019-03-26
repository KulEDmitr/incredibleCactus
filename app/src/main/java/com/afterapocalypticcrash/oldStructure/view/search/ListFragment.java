package com.afterapocalypticcrash.oldStructure.view.search;

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

import com.afterapocalypticcrash.R;
import com.afterapocalypticcrash.oldStructure.presenter.HandToHand;

public class ListFragment extends Fragment {
    private static final String LOG_TAG = ListFragment.class.getSimpleName();

    private RecyclerView list;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        Log.d(LOG_TAG, "onCreateView");

        View view = inflater.inflate(R.layout.activity_pictures_list, container, false);
        list = view.findViewById(R.id.res_list);
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        Log.d(LOG_TAG, "onActivityCreated");

        super.onActivityCreated(savedInstanceState);
        HandToHand.getCurData(this, list);
    }
}
