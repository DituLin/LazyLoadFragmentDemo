package atu.com.lazyloadfragmentdemo;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.ArrayList;
import java.util.List;

import atu.com.lazyloadfragmentdemo.fragment.IndexFragment;
import atu.com.lazyloadfragmentdemo.fragment.TrackFragment;
import atu.com.lazyloadfragmentdemo.fragment.UserFragment;
import atu.com.lazyloadfragmentdemo.fragment.VideoFragment;

/**
 * Created by atu on 2017/9/20.
 */

public class ViewPagerAdapter extends FragmentPagerAdapter {

    private List<String> mTitleList = new ArrayList<>();//页卡标题集合
    Fragment[] fragments;

    public ViewPagerAdapter(FragmentManager fm, List<String> list) {
        super(fm);
        this.mTitleList = list;
        fragments = new Fragment[mTitleList.size()];
    }

    @Override
    public Fragment getItem(int position) {
        switch (position) {
            case 0:
                if (fragments[0] == null) {
                    fragments[0] = new IndexFragment();
                }
                return fragments[0];
            case 1:
                if (fragments[1] == null) {
                    fragments[1] = new TrackFragment();
                }
                return fragments[1];
            case 2:
                if (fragments[2] == null) {
                    fragments[2] = new VideoFragment();
                }
                return fragments[2];
            case 3:
                if (fragments[3] == null) {
                    fragments[3] = new UserFragment();
                }
                return fragments[3];
        }
        return null;
    }

    @Override
    public int getCount() {
        return fragments.length;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        String tabTitle = mTitleList.get(position);
        return tabTitle;
    }
}
