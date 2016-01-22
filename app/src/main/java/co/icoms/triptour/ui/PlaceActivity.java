package co.icoms.triptour.ui;

import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.view.ViewPager;

import co.icoms.triptour.R;
import co.icoms.triptour.data.adapters.PlaceSlideAdapter;

public class PlaceActivity extends FragmentActivity {
    ViewPager pagerRoatan = null;
    ViewPager pagerAmapala = null;
    ViewPager pagerCeiba = null;


    @Override
    protected void onCreate(Bundle arg0) {
        super.onCreate(arg0);

        this.setContentView(R.layout.activity_place);

        // Instantiate a ViewPager
        this.pagerRoatan = (ViewPager) this.findViewById(R.id.viewPagerPlaceRoatan);
        this.pagerAmapala = (ViewPager) this.findViewById(R.id.viewPagerPlaceAmapala);
        this.pagerCeiba = (ViewPager) this.findViewById(R.id.viewPagerPlaceCeiba);


        //ROATAN  Create an adapter with the fragments we show on the ViewPager

        PlaceSlideAdapter adapterRoatan = new PlaceSlideAdapter(getSupportFragmentManager());
        adapterRoatan.addFragment(ScreenSlidePageFragment.newInstance("http://i.imgur.com/3v1ZKgg.jpg", 0));
        adapterRoatan.addFragment(ScreenSlidePageFragment.newInstance("http://i.imgur.com/wRtO8SJ.jpg", 1));
        adapterRoatan.addFragment(ScreenSlidePageFragment.newInstance("http://i.imgur.com/0bgrTfw.jpg", 2));
        adapterRoatan.addFragment(ScreenSlidePageFragment.newInstance("http://i.imgur.com/t29Iv1x.jpg", 3));
        adapterRoatan.addFragment(ScreenSlidePageFragment.newInstance("http://i.imgur.com/AL5zF2K.jpg", 4));
        this.pagerRoatan.setAdapter(adapterRoatan);

        //AMAPALA
        PlaceSlideAdapter adapterAmapala = new PlaceSlideAdapter(getSupportFragmentManager());
        adapterAmapala.addFragment(ScreenSlidePageFragment.newInstance("http://i.imgur.com/hfsEUuX.jpg", 0));
        adapterAmapala.addFragment(ScreenSlidePageFragment.newInstance("http://i.imgur.com/ksUvZyf.jpg", 1));
        adapterAmapala.addFragment(ScreenSlidePageFragment.newInstance("http://i.imgur.com/uM2gEjh.jpg", 2));
        adapterAmapala.addFragment(ScreenSlidePageFragment.newInstance("http://i.imgur.com/CicnRWh.jpg", 3));
        adapterAmapala.addFragment(ScreenSlidePageFragment.newInstance("http://i.imgur.com/iK7Qwis.jpg", 4));
        adapterAmapala.addFragment(ScreenSlidePageFragment.newInstance("http://i.imgur.com/bcvLmJv.jpg", 5));
        adapterAmapala.addFragment(ScreenSlidePageFragment.newInstance("http://i.imgur.com/UQJIVwI.jpg", 6));
        this.pagerAmapala.setAdapter(adapterAmapala);


        //CEIBA
        PlaceSlideAdapter adapterCeiba = new PlaceSlideAdapter(getSupportFragmentManager());
        adapterCeiba.addFragment(ScreenSlidePageFragment.newInstance("http://i.imgur.com/XYoSQfD.jpg", 0));
        adapterCeiba.addFragment(ScreenSlidePageFragment.newInstance("http://i.imgur.com/ZF35tMX.jpg", 1));
        adapterCeiba.addFragment(ScreenSlidePageFragment.newInstance("http://i.imgur.com/rOsXhgL.jpg", 2));
        adapterCeiba.addFragment(ScreenSlidePageFragment.newInstance("http://i.imgur.com/p19vgtC.jpg", 3));
        this.pagerCeiba.setAdapter(adapterCeiba);

    }

    @Override
    public void onBackPressed() {

        // Return to previous page when we press back button
        if (this.pagerRoatan.getCurrentItem() == 0)
            super.onBackPressed();
        else
            this.pagerRoatan.setCurrentItem(this.pagerRoatan.getCurrentItem() - 1);

    }

}
