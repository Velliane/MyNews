package com.menard.mynews.controller;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.menard.mynews.R;

public class PageFragment extends Fragment {

    /**
     * Keys for Bundle
     */
    private static final String KEY_POSITION= "position";
    private static final String KEY_COLOR= "color";

    public PageFragment(){}

    public static PageFragment newInstance(int position, int color) {

        PageFragment fragment = new PageFragment();

        Bundle arguments = new Bundle();
        arguments.putInt(KEY_POSITION, position);
        arguments.putInt(KEY_COLOR, color);
        fragment.setArguments(arguments);

        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View result = inflater.inflate(R.layout.fragment_page, container, false);
        RecyclerView list = result.findViewById(R.id.fragment_list);

        int position = getArguments().getInt(KEY_POSITION, -1);
        int color = getArguments().getInt(KEY_COLOR, -1);

        return result;
    }
}
