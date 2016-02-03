package co.icoms.triptour.ui;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Resources;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;

import com.facebook.login.LoginManager;

import co.icoms.triptour.R;
import co.icoms.triptour.data.adapters.MainTabAdapter;

public class MainActivity extends AppCompatActivity {
    TabLayout tabLayoutMenu;
    ViewPager viewPager;


    //MENU
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        invalidateOptionsMenu();
        getMenuInflater().inflate(R.menu.menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.sing_out:
                singOut();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        /*
        SharedPreferences prefs = getSharedPreferences("login", Context.MODE_PRIVATE);
        if(prefs.getBoolean(Final.LifeCycle.STATE,false)){
            Parse.enableLocalDatastore(this);
            Parse.initialize(getApplicationContext());
        }
        SharedPreferences.Editor editor = prefs.edit();
        editor.putBoolean(Final.LifeCycle.STATE, false);
        editor.commit();
        */
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

    private void singOut(){
        SharedPreferences prefs = getSharedPreferences("login", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = prefs.edit();
        editor.clear();
        editor.commit();
        LoginManager.getInstance().logOut();
        Intent loginIntent = new Intent(getApplicationContext(), LoginActivity.class);
        //loginIntent.putExtra(Final.DataLogin.STATE, Final.DataLogin.RELOGIN);
        startActivity(loginIntent);
        finish();
    }

    @Override
    protected void onStop() {
        super.onStop();
        /*
        SharedPreferences prefs = getSharedPreferences("login", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = prefs.edit();
        editor.putBoolean(Final.LifeCycle.STATE,true);
        editor.commit();
        */
        finish();
        Log.e("TAGP", "onStop()");
    }
}