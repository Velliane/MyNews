package com.menard.mynews;

import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.menard.mynews.controller.PageFragment;

public class ViewPagerAdapter extends FragmentPagerAdapter {

    private int[] colors;

    public ViewPagerAdapter(FragmentManager manager, int[]colors){
        super(manager);
        this.colors = colors;
    }

    @Override
    public int getCount() {
        return 5;
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        return "Page" + position;
    }

    @Override
    public Fragment getItem(int position) {
        return (PageFragment.newInstance(position, this.colors[position] ));
    }
}
