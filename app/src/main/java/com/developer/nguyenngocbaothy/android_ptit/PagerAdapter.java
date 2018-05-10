package com.developer.nguyenngocbaothy.android_ptit;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

/**
 * Created by HOME on 10-May-18.
 */

public class PagerAdapter extends FragmentStatePagerAdapter {

    int numberOfTabs;

    public PagerAdapter(FragmentManager fm, int numberOfTabs) {
        super(fm);
        this.numberOfTabs = numberOfTabs;
    }


    @Override
    public Fragment getItem(int position) {
        switch (position) {
            case 0:
                TapHomeFragment tapHome = new TapHomeFragment();
                return tapHome;
            case 1:
                TapCookInstructionFragment tapCook = new TapCookInstructionFragment();
                return tapCook;
            case 2:
                TapStoreFragment tapStore = new TapStoreFragment();
                return tapStore;
            case 3:
                TabUserFragment tapUser = new TabUserFragment();
                return tapUser;
            default:
                return null;
        }
    }

    @Override
    public int getCount() {
        return numberOfTabs;
    }
}
