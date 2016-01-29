package co.icoms.triptour.data.adapters;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import co.icoms.triptour.ui.MainActivitiesFragment;
import co.icoms.triptour.ui.MainFoursquareFragment;
import co.icoms.triptour.ui.MainHotelFragment;
import co.icoms.triptour.ui.MainRestaurantFragment;

public class MainTabAdapter extends FragmentStatePagerAdapter {
    int mNumOfTabs;
    String place;

    public String getPlace() {
        return place;
    }

    public void setPlace(String place) {
        this.place = place;
    }

    public MainTabAdapter(FragmentManager fm, int NumOfTabs) {
        super(fm);
        this.mNumOfTabs = NumOfTabs;
    }

    @Override
    public Fragment getItem(int position) {
        Bundle bundle = new Bundle();
        bundle.putString("place", this.place);

        switch (position) {
            case 0:
                MainFoursquareFragment foursquareTab = new MainFoursquareFragment();
                foursquareTab.setArguments(bundle);
                return foursquareTab;
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
            default:
                return null;
        }
    }

    @Override
    public int getCount() {
        return mNumOfTabs;
    }
}