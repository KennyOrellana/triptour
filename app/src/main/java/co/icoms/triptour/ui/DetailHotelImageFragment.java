package co.icoms.triptour.ui;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.parse.FindCallback;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQuery;

import java.util.ArrayList;
import java.util.List;

import co.icoms.triptour.R;
import co.icoms.triptour.data.adapters.DetailHotelImageAdapter;
import co.icoms.triptour.utils.Final;

public class DetailHotelImageFragment extends Fragment {
    private RecyclerView recyclerViewHotel;
    List<DetailHotelImageCell> listImages= new ArrayList<>();

    DetailHotelImageAdapter adapterDetailHotelImage = new DetailHotelImageAdapter(listImages, getContext());

    private int previousTotal = 0;
    private boolean loading = true;
    private int visibleThreshold = 5;
    int firstVisibleItem, visibleItemCount, totalItemCount;
    String tableDetailHotelImages;
    SwipeRefreshLayout swipeRefreshDetailHotelImages;
    TextView textViewEmptyList;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        tableDetailHotelImages=getArguments().getString(Final.DataHotel.PLACE)+"_hotel_image";
        Log.e("Query",tableDetailHotelImages);

        return inflater.inflate(R.layout.fragment_detail_hotel_image, container, false);
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        textViewEmptyList=(TextView)this.getView().findViewById(R.id.text_view_empty_list);
        addItem(getArguments().getInt(Final.DataHotel.ID));

        recyclerViewHotel = (RecyclerView) this.getView().findViewById(R.id.recycler_view_image);
        final LinearLayoutManager llm = new LinearLayoutManager(getContext());
        recyclerViewHotel.setLayoutManager(llm);
        recyclerViewHotel.setAdapter(adapterDetailHotelImage);

        recyclerViewHotel.setHasFixedSize(true);

        /*
        recyclerViewHotel.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);

                visibleItemCount = recyclerView.getChildCount();
                totalItemCount = llm.getItemCount();
                firstVisibleItem = llm.findFirstVisibleItemPosition();

                if (loading) {
                    if (totalItemCount > previousTotal) {
                        loading = false;
                        previousTotal = totalItemCount;
                    }
                }
                if (!loading && (totalItemCount - visibleItemCount)
                        <= (firstVisibleItem + visibleThreshold)) {

                    loading = true;
                    addItem(1 + totalItemCount);
                }
            }
        });
        */


        //Pull to Refresh
        swipeRefreshDetailHotelImages = (SwipeRefreshLayout)this.getView().findViewById(R.id.swipe_refresh_detail_hotel_image);
        swipeRefreshDetailHotelImages.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                listImages.clear();
                addItem(1);
                swipeRefreshDetailHotelImages.setRefreshing(false);
            }
        });
    }

    //TODO arreglar el parametro extras
    void addItem(int n){

        final Integer number = new Integer(n);

        ConnectivityManager cm =
                (ConnectivityManager)getContext().getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetwork = cm.getActiveNetworkInfo();

        if(activeNetwork!=null && activeNetwork.isConnected()) {
            listImages.clear();

            ParseQuery query = new ParseQuery(this.tableDetailHotelImages);
            query.whereEqualTo("number", number);
            query.findInBackground(new FindCallback<ParseObject>() {
                @Override
                public void done(List<ParseObject> item, ParseException e) {
                    int size;
                    if (item != null && ((size = item.size()) > 0)) {
                        for (int k = 0; k < size; k++) {
                            listImages.add(new DetailHotelImageCell(
                                    item.get(k).getString("url")));
                            Log.e("TAGurl", item.get(k).getString("url"));
                            adapterDetailHotelImage.notifyDataSetChanged();
                        }
                    }

                    if (listImages.size() == 0)
                        textViewEmptyList.setVisibility(View.VISIBLE);
                    else
                        textViewEmptyList.setVisibility(View.INVISIBLE);
                }
            });
        }
    }
}