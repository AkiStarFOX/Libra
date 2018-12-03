package com.stoka.libra;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.stoka.libra.fragments.CalibrateFragment;
import com.stoka.libra.fragments.ListFragment;
import com.stoka.libra.fragments.PutOnTableFragment;

public class PageAdapter extends FragmentPagerAdapter {
    private CalibrateFragment calibrateFragment;
    private PutOnTableFragment putOnTableFragment;
    private ListFragment listFragment;

    public PageAdapter(FragmentManager fm, CalibrateFragment calibrateFragment, PutOnTableFragment putOnTableFragment, ListFragment listFragment) {
        super(fm);
        this.calibrateFragment = calibrateFragment;
        this.putOnTableFragment = putOnTableFragment;
        this.listFragment = listFragment;
    }

    @Override
    public Fragment getItem(int i) {
        Fragment fragment;
        switch (i){
            case 0:
                return calibrateFragment;
            case 1:
                return putOnTableFragment;
            case 2:
                return listFragment;
            default:
                return new CalibrateFragment();


        }
    }

    @Override
    public int getCount() {
        return 3;
    }
}
