package co.icoms.triptour.data.adapters;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import co.icoms.triptour.ui.DetailHotelImageFragment;
import co.icoms.triptour.ui.MainFoursquareFragment;

public class DetailHotelTabAdapter extends FragmentStatePagerAdapter {
    int mNumOfTabs;
    Bundle bundle;

    public Bundle getBundle() {
        return bundle;
    }

    public void setBundle(Bundle bundle) {
        this.bundle = bundle;
    }

    public DetailHotelTabAdapter(FragmentManager fm, int NumOfTabs) {
        super(fm);
        this.mNumOfTabs = NumOfTabs;
    }

    @Override
    public Fragment getItem(int position) {
        //Bundle bundle = new Bundle();

        switch (position) {
            case 0:
                MainFoursquareFragment foursquareTab = new MainFoursquareFragment();
                foursquareTab.setArguments(bundle);
                return foursquareTab;

            case 1:
                DetailHotelImageFragment detailHotelImageFragment = new DetailHotelImageFragment();
                detailHotelImageFragment.setArguments(bundle);
                return detailHotelImageFragment;
            case 2:
                MainFoursquareFragment foursquareTab2 = new MainFoursquareFragment();
                foursquareTab2.setArguments(bundle);
                return foursquareTab2;
            case 3:
                MainFoursquareFragment foursquareTab3 = new MainFoursquareFragment();
                foursquareTab3.setArguments(bundle);
                return foursquareTab3;
            case 4:
                MainFoursquareFragment foursquareTab4 = new MainFoursquareFragment();
                foursquareTab4.setArguments(bundle);
                return foursquareTab4;

            default:
                return null;
        }
    }

    @Override
    public int getCount() {
        return mNumOfTabs;
    }
}