package co.icoms.triptour.ui;

import android.content.res.Resources;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v7.app.AppCompatActivity;

import co.icoms.triptour.R;

public class MainActivity extends AppCompatActivity {
    TabLayout tabLayoutMenu;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Resources res=getResources();

        tabLayoutMenu=(TabLayout)findViewById(R.id.tab_layout_menu);

        tabLayoutMenu.addTab(tabLayoutMenu.newTab().setIcon(res.getDrawable(R.drawable.ic_place_black_48dp)));
        tabLayoutMenu.addTab(tabLayoutMenu.newTab().setIcon(res.getDrawable(R.drawable.ic_hotel_black_48dp)));
        tabLayoutMenu.addTab(tabLayoutMenu.newTab().setIcon(res.getDrawable(R.drawable.ic_restaurant_menu_black_48dp)));
        tabLayoutMenu.addTab(tabLayoutMenu.newTab().setIcon(res.getDrawable(R.drawable.ic_local_activity_black_48dp)));
    }
}
