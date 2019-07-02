package com.menard.mynews.controller;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.menard.mynews.R;
import com.menard.mynews.RecyclerViewAdapter;

public class PageFragment extends Fragment {

    /**
     * Keys for Bundle
     */
    private static final String KEY_POSITION= "position";

    public PageFragment(){}

    public static PageFragment newInstance(int position) {

        PageFragment fragment = new PageFragment();

        Bundle arguments = new Bundle();
        arguments.putInt(KEY_POSITION, position);
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

        int position = getArguments().getInt(KEY_POSITION, -1);

        return result;
    }
}
