package com.menard.mynews.adapter;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import com.menard.mynews.controller.fragments.MostPopularFragment;
import com.menard.mynews.controller.fragments.MoviesFragment;
import com.menard.mynews.controller.fragments.ScienceFragment;
import com.menard.mynews.controller.fragments.TopStoriesFragment;
import com.menard.mynews.controller.fragments.TravelFragment;
import com.menard.mynews.model.Category;

public class ViewPagerAdapter extends FragmentPagerAdapter {


    public ViewPagerAdapter(FragmentManager manager) {
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
        switch (position) {
            case 0:
                return TopStoriesFragment.newInstance();
            case 1:
                return MostPopularFragment.newInstance();
            case 2:
                return MoviesFragment.newInstance();
            case 3:
                return ScienceFragment.newInstance();
            case 4:
                return TravelFragment.newInstance();
            default:
                return null;
        }
    }
}
