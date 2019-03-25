package com.afterapocalypticcrash.search;

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

import com.afterapocalypticcrash.R;
import com.afterapocalypticcrash.pictureDetails.AllData;
import com.afterapocalypticcrash.search.api.PictureApiContent;
import com.afterapocalypticcrash.recyclerAdapter.RecyclerViewAdapter;
import com.squareup.picasso.Picasso;

import java.util.List;
import java.util.Objects;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;

import static com.afterapocalypticcrash.search.api.RetrofitModule.getApiInstance;

public class ListFragment extends Fragment {
    private static final String LOG_TAG = ListFragment.class.getSimpleName();

    private static final String QUERY = "QUERY";
    private static final String RESULT_LIST = "RESULT_LIST";

    private Disposable subscribe;
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

        if (savedInstanceState != null) {
            Objects.requireNonNull(getActivity()).getIntent()
                    .putExtra(QUERY, savedInstanceState.getString(QUERY));
            getActivity().getIntent()
                    .putParcelableArrayListExtra(RESULT_LIST,
                            savedInstanceState.getParcelableArrayList(RESULT_LIST));
            getActivity().getIntent()
                    .putExtra("TWO_PANE", savedInstanceState.getBoolean("TWO_PANE"));
            setupRecyclerView(savedInstanceState.getParcelableArrayList(RESULT_LIST));
        } else {
            getData();
        }
    }

    private void getData() {
        Log.d(LOG_TAG, "getData");

        subscribe = getApiInstance()
                .getResults(Objects.requireNonNull(getArguments()).getString(QUERY))
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(pictures -> {
                            if (pictures.getResults().size() > 0) {
                                PictureApiContent.Results cur;
                                for (int i = 0; i < pictures.getResults().size(); ++i) {
                                    cur = pictures.getResults().get(i);
                                    AllData.getItemMap().put(cur.getId(), cur);
                                }
                                setupRecyclerView(pictures.getResults());
                            } else {
                                Toast.makeText(getContext(),
                                        "there are no results", Toast.LENGTH_LONG).show();
                            }
                        }, throwable -> {
                            Toast.makeText(getContext(),
                                    "some error occured, \nPlease, repeat your query",
                                    Toast.LENGTH_LONG).show();
                            throwable.printStackTrace();
                        }
                );
    }

    private void setupRecyclerView(List<PictureApiContent.Results> res) {
        Log.d(LOG_TAG, "setupRecyclerView");

//        if (!isVisible()) {
//            return;
//        }
        list.setAdapter(new RecyclerViewAdapter(res));
        list.setLayoutManager(new LinearLayoutManager(getActivity()));
    }

    @Override
    public void onSaveInstanceState(@NonNull Bundle outState) {
        Log.d(LOG_TAG, "onSaveInstanceState");
        super.onSaveInstanceState(outState);
        outState.putString(QUERY, getActivity().getIntent().getStringExtra(QUERY));
        outState.putParcelableArrayList(RESULT_LIST, getActivity().getIntent()
                .getParcelableArrayListExtra(RESULT_LIST));
    }

    @Override
    public void onDestroyView() {
        Log.d(LOG_TAG, "onDestroyView");

        super.onDestroyView();
        Objects.requireNonNull(subscribe).dispose();
//        if (isVisible()) {
            Picasso.with(getActivity()).cancelTag(ListActivity.class);
//        }
    }
}
