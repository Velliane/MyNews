package com.menard.mynews.adapter;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import com.menard.mynews.controller.PageFragment;
import com.menard.mynews.model.Category;

public class ViewPagerAdapter extends FragmentPagerAdapter {



    public ViewPagerAdapter(FragmentManager manager){
        super(manager);

    }

    @Override
    public int getCount() {
        return Category.values().length;
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        return Category.values()[position].getName();
    }

    @Override
    public Fragment getItem(int position) {
        return (PageFragment.newInstance(position));
    }
}
