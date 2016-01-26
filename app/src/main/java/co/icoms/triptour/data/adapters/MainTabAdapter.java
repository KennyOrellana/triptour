package co.icoms.triptour.data.adapters;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import co.icoms.triptour.ui.ActivitiesMainFragment;
import co.icoms.triptour.ui.FoursquareMainFragment;
import co.icoms.triptour.ui.HotelMainFragment;
import co.icoms.triptour.ui.RestaurantMainFragment;

public class MainTabAdapter extends FragmentStatePagerAdapter {
    int mNumOfTabs;

    public MainTabAdapter(FragmentManager fm, int NumOfTabs) {
        super(fm);
        this.mNumOfTabs = NumOfTabs;
    }

    @Override
    public Fragment getItem(int position) {

        switch (position) {
            case 0:
                FoursquareMainFragment foursquareTab = new FoursquareMainFragment();
                return foursquareTab;
            case 1:
                HotelMainFragment hotelTab = new HotelMainFragment();
                return hotelTab;
            case 2:
                RestaurantMainFragment restaurantTab = new RestaurantMainFragment();
                return restaurantTab;
            case 3:
                ActivitiesMainFragment activitiesTab = new ActivitiesMainFragment();
                return activitiesTab;
            default:
                return null;
        }
    }

    @Override
    public int getCount() {
        return mNumOfTabs;
    }
}