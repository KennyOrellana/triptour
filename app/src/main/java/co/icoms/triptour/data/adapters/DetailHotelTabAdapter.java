package co.icoms.triptour.data.adapters;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import co.icoms.triptour.ui.FoursquareMainFragment;

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
        //bundle.putString("place", this.place);

        switch (position) {
            case 0:
                FoursquareMainFragment foursquareTab = new FoursquareMainFragment();
                foursquareTab.setArguments(bundle);
                return foursquareTab;
            /*
            case 1:
                HotelMainFragment hotelTab = new HotelMainFragment();
                hotelTab.setArguments(bundle);
                return hotelTab;
            case 2:
                RestaurantMainFragment restaurantTab = new RestaurantMainFragment();
                restaurantTab.setArguments(bundle);
                return restaurantTab;
            case 3:
                ActivitiesMainFragment activitiesTab = new ActivitiesMainFragment();
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