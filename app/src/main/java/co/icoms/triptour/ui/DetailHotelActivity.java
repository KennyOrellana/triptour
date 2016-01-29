package co.icoms.triptour.ui;

import android.content.res.Resources;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;

import co.icoms.triptour.R;
import co.icoms.triptour.data.adapters.DetailHotelTabAdapter;
import co.icoms.triptour.utils.Final;

public class DetailHotelActivity extends AppCompatActivity {
    TabLayout tabLayoutDetailHotel;
    ViewPager viewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_hotel);
        Resources res=getResources();

        tabLayoutDetailHotel=(TabLayout)findViewById(R.id.tab_layout_detail_hotel);

        tabLayoutDetailHotel.addTab(tabLayoutDetailHotel.newTab().setIcon(res.getDrawable(R.drawable.ic_info_black_24dp)));

        viewPager = (ViewPager) findViewById(R.id.viewPagerDetailHotel);
        DetailHotelTabAdapter adapter = new DetailHotelTabAdapter(getSupportFragmentManager(), tabLayoutDetailHotel.getTabCount());

        adapter.setBundle(getIntent().getBundleExtra(Final.DataHotel.DATA));

        viewPager.setAdapter(adapter);

        viewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayoutDetailHotel));
        tabLayoutDetailHotel.setOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
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
}