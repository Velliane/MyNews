package com.menard.mynews.controller.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.menard.mynews.R;
import com.menard.mynews.adapter.RecyclerViewAdapter;

public class ScienceFragment extends Fragment {

    /**
     * Keys for Bundle
     */


    public ScienceFragment(){}

    public static ScienceFragment newInstance() {

        ScienceFragment fragment = new ScienceFragment();

        Bundle arguments = new Bundle();
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


        return result;
    }
}
