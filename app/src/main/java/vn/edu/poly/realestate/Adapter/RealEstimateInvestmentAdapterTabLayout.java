package vn.edu.poly.realestate.Adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.app.FragmentStatePagerAdapter;

import vn.edu.poly.realestate.View.RealEstateInvestments.InvestedUpland.InvestedUplandFragment;
import vn.edu.poly.realestate.View.RealEstateInvestments.InvestingUpland.InvestingUplandFragment;

public class RealEstimateInvestmentAdapterTabLayout extends FragmentPagerAdapter {

    private int numberOfTab;

    public RealEstimateInvestmentAdapterTabLayout(FragmentManager fm, int numberOfTab) {
        super(fm);
        this.numberOfTab = numberOfTab;
    }

    @Override
    public Fragment getItem(int i) {
        switch (i) {
            case 0:
                return new InvestingUplandFragment();
            case 1:
                return new InvestedUplandFragment();
            default:
                return null;
        }
    }

    @Override
    public int getCount() {
        return numberOfTab;
    }
}
