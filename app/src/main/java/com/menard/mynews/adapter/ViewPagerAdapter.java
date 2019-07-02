package com.menard.mynews.adapter;

import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

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
