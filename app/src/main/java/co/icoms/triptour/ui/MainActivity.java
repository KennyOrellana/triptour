package co.icoms.triptour.ui;

import android.content.res.Resources;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import co.icoms.triptour.R;
import co.icoms.triptour.data.adapters.MainTabAdapter;

public class MainActivity extends AppCompatActivity {
    TabLayout tabLayoutMenu;
    ViewPager viewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.e("TAG Execute", "Activity onCreate");
        setContentView(R.layout.activity_main);
        Resources res=getResources();

        tabLayoutMenu=(TabLayout)findViewById(R.id.tab_layout_menu);

        tabLayoutMenu.addTab(tabLayoutMenu.newTab().setIcon(res.getDrawable(R.mipmap.foursquare)));
        //tabLayoutMenu.addTab(tabLayoutMenu.newTab().setIcon(res.getDrawable(R.drawable.ic_place_black_48dp)));
        tabLayoutMenu.addTab(tabLayoutMenu.newTab().setIcon(res.getDrawable(R.drawable.ic_hotel_black_48dp)));
        tabLayoutMenu.addTab(tabLayoutMenu.newTab().setIcon(res.getDrawable(R.drawable.ic_restaurant_menu_black_48dp)));
        tabLayoutMenu.addTab(tabLayoutMenu.newTab().setIcon(res.getDrawable(R.drawable.ic_local_activity_black_48dp)));

        viewPager = (ViewPager) findViewById(R.id.viewPagerMain);
        MainTabAdapter adapter = new MainTabAdapter(getSupportFragmentManager(), tabLayoutMenu.getTabCount());

        adapter.setPlace(getIntent().getStringExtra("place"));

        viewPager.setAdapter(adapter);

        viewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayoutMenu));
        tabLayoutMenu.setOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                viewPager.setCurrentItem(tab.getPosition());
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {
            }
        });
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finish();
    }
}