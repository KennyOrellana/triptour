package co.icoms.triptour.ui;

import android.content.res.Resources;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.CardView;
import android.widget.ImageView;
import android.widget.TextView;

import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.NetworkImageView;

import co.icoms.triptour.R;
import co.icoms.triptour.data.adapters.DetailHotelTabAdapter;
import co.icoms.triptour.utils.Final;
import co.icoms.triptour.utils.MySingleton;

public class DetailHotelActivity extends AppCompatActivity {
    TabLayout tabLayoutDetailHotel;
    ViewPager viewPager;

    //HEADER
    CardView cardView;
    TextView name;
    NetworkImageView photo;
    TextView price;
    ImageView star1;
    ImageView star2;
    ImageView star3;
    ImageView star4;
    ImageView star5;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_hotel);
        Resources res=getResources();

        tabLayoutDetailHotel=(TabLayout)findViewById(R.id.tab_layout_detail_hotel);

        //Adding tabs
        tabLayoutDetailHotel.addTab(tabLayoutDetailHotel.newTab().setIcon(res.getDrawable(R.drawable.ic_info_black_24dp)));

        viewPager = (ViewPager) findViewById(R.id.viewPagerDetailHotel);
        DetailHotelTabAdapter adapter = new DetailHotelTabAdapter(getSupportFragmentManager(), tabLayoutDetailHotel.getTabCount());

        //Pass Bundle to all tabs for query parameters
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

        //HEADER
        Bundle bundle = getIntent().getBundleExtra(Final.DataHotel.DATA);

        cardView = (CardView)findViewById(R.id.card_view);
        name = (TextView)findViewById(R.id.name);
        photo = (NetworkImageView)findViewById(R.id.photo);
        price = (TextView)findViewById(R.id.price);
        star1 = (ImageView)findViewById(R.id.star_1);
        star2 = (ImageView)findViewById(R.id.star_2);
        star3 = (ImageView)findViewById(R.id.star_3);
        star4 = (ImageView)findViewById(R.id.star_4);
        star5 = (ImageView)findViewById(R.id.star_5);

        name.setText(bundle.getString(Final.DataHotel.NAME));

        ImageLoader mImageLoader;
        mImageLoader = MySingleton.getInstance(this).getImageLoader();
        photo.setImageUrl(bundle.getString(Final.DataHotel.IMAGE),mImageLoader);
        price.setText(String.valueOf(bundle.getInt(Final.DataHotel.PRICE)));


        switch (bundle.getInt(Final.DataHotel.STARS)){
            case 5:
                star5.setImageResource(R.drawable.star_full);
            case 4:
                star4.setImageResource(R.drawable.star_full);
            case 3:
                star3.setImageResource(R.drawable.star_full);
            case 2:
                star2.setImageResource(R.drawable.star_full);
            case 1:
                star1.setImageResource(R.drawable.star_full);
            default:
                break;
        }
    }
}