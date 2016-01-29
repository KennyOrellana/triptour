package co.icoms.triptour.data.adapters;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

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
        Bundle bundle = new Bundle();

        switch (position) {
            case 0:
                MainFoursquareFragment foursquareTab = new MainFoursquareFragment();
                foursquareTab.setArguments(bundle);
                return foursquareTab;
            /*
            case 1:
                MainHotelFragment hotelTab = new MainHotelFragment();
                hotelTab.setArguments(bundle);
                return hotelTab;
            case 2:
                MainRestaurantFragment restaurantTab = new MainRestaurantFragment();
                restaurantTab.setArguments(bundle);
                return restaurantTab;
            case 3:
                MainActivitiesFragment activitiesTab = new MainActivitiesFragment();
                activitiesTab.setArguments(bundle);
                return activitiesTab;
                */
            default:
                return null;
        }
    }

    @Override
    public int getCount() {
        return mNumOfTabs;
    }
}