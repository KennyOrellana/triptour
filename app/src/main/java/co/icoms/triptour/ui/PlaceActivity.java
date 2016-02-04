package co.icoms.triptour.ui;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.view.ViewPager;
import android.util.Log;

import com.crashlytics.android.Crashlytics;
import com.facebook.AccessToken;
import com.facebook.FacebookSdk;
import com.parse.Parse;
import com.viewpagerindicator.CirclePageIndicator;

import co.icoms.triptour.R;
import co.icoms.triptour.data.adapters.PlaceSlideAdapter;
import io.fabric.sdk.android.Fabric;

public class PlaceActivity extends FragmentActivity {
    ViewPager pagerRoatan = null;
    ViewPager pagerAmapala = null;
    ViewPager pagerCeiba = null;


    @Override
    protected void onCreate(Bundle arg0) {
        super.onCreate(arg0);
        Fabric.with(this, new Crashlytics());

        //Initialization
        //Facebook
        FacebookSdk.sdkInitialize(getApplicationContext());
        //Parse
        Parse.enableLocalDatastore(this);
        Parse.initialize(getApplicationContext());
        //-----------------------------

        SharedPreferences preferences = getSharedPreferences("login", Context.MODE_PRIVATE);
        if(preferences.getBoolean("logged", false)) {
            Log.e("Boot", "Login con Email");
        }
        else if (isLoggedFacebook()) {
            Log.e("Boot", "Login con Facebook");
        }
        else {
            Log.e("Boot", "onCreate");
            startLoginActivity();
        }

        //

        this.setContentView(R.layout.activity_place);

        // Instantiate a ViewPager
        this.pagerRoatan = (ViewPager) this.findViewById(R.id.viewPagerPlaceRoatan);
        this.pagerAmapala = (ViewPager) this.findViewById(R.id.viewPagerPlaceAmapala);
        this.pagerCeiba = (ViewPager) this.findViewById(R.id.viewPagerPlaceCeiba);


        //ROATAN  Create an adapter with the fragments we show on the ViewPager

        PlaceSlideAdapter adapterRoatan = new PlaceSlideAdapter(getSupportFragmentManager());
        adapterRoatan.addFragment(PlaceSlideFragment.newInstance("http://i.imgur.com/3v1ZKgg.jpg", 0, "roatan"));
        adapterRoatan.addFragment(PlaceSlideFragment.newInstance("http://i.imgur.com/wRtO8SJ.jpg", 1, "roatan"));
        adapterRoatan.addFragment(PlaceSlideFragment.newInstance("http://i.imgur.com/0bgrTfw.jpg", 2, "roatan"));
        adapterRoatan.addFragment(PlaceSlideFragment.newInstance("http://i.imgur.com/t29Iv1x.jpg", 3, "roatan"));
        adapterRoatan.addFragment(PlaceSlideFragment.newInstance("http://i.imgur.com/AL5zF2K.jpg", 4, "roatan"));
        this.pagerRoatan.setAdapter(adapterRoatan);

        CirclePageIndicator pageIndicatorRoatan=(CirclePageIndicator)findViewById(R.id.circle_page_indicator_roatan);
        pageIndicatorRoatan.setViewPager(this.pagerRoatan);

        //AMAPALA
        PlaceSlideAdapter adapterAmapala = new PlaceSlideAdapter(getSupportFragmentManager());
        adapterAmapala.addFragment(PlaceSlideFragment.newInstance("http://i.imgur.com/hfsEUuX.jpg", 0, "amapala"));
        adapterAmapala.addFragment(PlaceSlideFragment.newInstance("http://i.imgur.com/ksUvZyf.jpg", 1, "amapala"));
        adapterAmapala.addFragment(PlaceSlideFragment.newInstance("http://i.imgur.com/uM2gEjh.jpg", 2, "amapala"));
        adapterAmapala.addFragment(PlaceSlideFragment.newInstance("http://i.imgur.com/CicnRWh.jpg", 3, "amapala"));
        adapterAmapala.addFragment(PlaceSlideFragment.newInstance("http://i.imgur.com/iK7Qwis.jpg", 4, "amapala"));
        adapterAmapala.addFragment(PlaceSlideFragment.newInstance("http://i.imgur.com/bcvLmJv.jpg", 5, "amapala"));
        adapterAmapala.addFragment(PlaceSlideFragment.newInstance("http://i.imgur.com/UQJIVwI.jpg", 6, "amapala"));
        this.pagerAmapala.setAdapter(adapterAmapala);

        CirclePageIndicator pageIndicatorAmapala=(CirclePageIndicator)findViewById(R.id.circle_page_indicator_amapala);
        pageIndicatorAmapala.setViewPager(this.pagerAmapala);

        //CEIBA
        PlaceSlideAdapter adapterCeiba = new PlaceSlideAdapter(getSupportFragmentManager());
        adapterCeiba.addFragment(PlaceSlideFragment.newInstance("http://i.imgur.com/XYoSQfD.jpg", 0, "ceiba"));
        adapterCeiba.addFragment(PlaceSlideFragment.newInstance("http://i.imgur.com/ZF35tMX.jpg", 1, "ceiba"));
        adapterCeiba.addFragment(PlaceSlideFragment.newInstance("http://i.imgur.com/rOsXhgL.jpg", 2, "ceiba"));
        adapterCeiba.addFragment(PlaceSlideFragment.newInstance("http://i.imgur.com/p19vgtC.jpg", 3, "ceiba"));
        this.pagerCeiba.setAdapter(adapterCeiba);

        CirclePageIndicator pageIndicatorCeiba=(CirclePageIndicator)findViewById(R.id.circle_page_indicator_ceiba);
        pageIndicatorCeiba.setViewPager(this.pagerCeiba);

    }

    @Override
    public void onBackPressed() {

       moveTaskToBack(true);
    }

    private boolean isLoggedFacebook() {
        AccessToken accessToken = AccessToken.getCurrentAccessToken();
        return accessToken != null;
    }

    private void startLoginActivity(){
        Intent loginIntent = new Intent(getApplicationContext(), LoginActivity.class);
        startActivity(loginIntent);
    }
}
