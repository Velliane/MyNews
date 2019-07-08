package com.menard.mynews.controller.fragments;

import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.menard.mynews.R;
import com.menard.mynews.adapter.RecyclerViewAdapter;

public class TopStoriesFragment extends Fragment {

    /**
     * Keys for Bundle
     */
    //private static final String KEY_POSITION= "position";

    public TopStoriesFragment(){}

    public static TopStoriesFragment newInstance() {

        TopStoriesFragment fragment = new TopStoriesFragment();

        Bundle arguments = new Bundle();
        //arguments.putInt(KEY_POSITION, position);
        fragment.setArguments(arguments);

        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View result = inflater.inflate(R.layout.fragment_page, container, false);
        RecyclerView list = result.findViewById(R.id.fragment_list);

        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext());
        list.setLayoutManager(layoutManager);
        RecyclerViewAdapter adapter = new RecyclerViewAdapter();
        list.setAdapter(adapter);

        //int position = getArguments().getInt(KEY_POSITION, -1);

        return result;
    }
}
