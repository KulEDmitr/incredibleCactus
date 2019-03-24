package com.afterapocalypticcrash.akaMasterDetail;

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
import com.afterapocalypticcrash.api.AllData;
import com.afterapocalypticcrash.api.PictureApiContent;
import com.afterapocalypticcrash.akaMasterDetail.recyclerAdapter.RecyclerViewAdapter;
import com.squareup.picasso.Picasso;

import java.util.List;
import java.util.Objects;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;

import static com.afterapocalypticcrash.api.RetrofitModule.getApiInstance;

public class ListFragment extends Fragment {
    static final String LOG_TAG = ListFragment.class.getSimpleName();

    static final String QUERY = "QUERY";
    static final String RESULT_LIST = "RESULT_LIST";

    Disposable subscribe;

    private RecyclerView list;

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
            Objects.requireNonNull(getActivity()).getIntent()
                    .putExtra(QUERY, savedInstanceState.getString(QUERY));
            getActivity().getIntent()
                    .putParcelableArrayListExtra(RESULT_LIST,
                            savedInstanceState.getParcelableArrayList(RESULT_LIST));
            setupRecyclerView(savedInstanceState.getParcelableArrayList(RESULT_LIST));
        } else {
            getData();
        }
    }

    private void getData() {
        Log.e(LOG_TAG, "getData");

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
                                Log.e(LOG_TAG, "some error occured");
//                                Intent errorIntent = new Intent(getActivity(), ErrorActivity.class);
//                                errorIntent.putExtra("ERROR", "no results for your request");
//                                startActivity(errorIntent);
                            }
                        }, throwable -> {
                            throwable.printStackTrace();
//                            Intent errorIntent = new Intent(getActivity(), ErrorActivity.class);
//                            errorIntent.putExtra("ERROR",
//                                    throwable.getMessage());
//                            startActivity(errorIntent);
                        }
                );
    }

    private void setupRecyclerView(List<PictureApiContent.Results> res) {
        Log.e(LOG_TAG, "setupRecyclerView");

//        if (!isVisible()) {
//            return;
//        }
        list.setAdapter(new RecyclerViewAdapter(res));
        list.setLayoutManager(new LinearLayoutManager(getActivity()));
    }

    @Override
    public void onDestroyView() {
        Log.w(LOG_TAG, "onDestroyView");

        super.onDestroyView();
        Objects.requireNonNull(subscribe).dispose();
        if (isVisible()) {
            Picasso.with(getActivity()).cancelTag(ListActivity.class);
        }
    }
}
