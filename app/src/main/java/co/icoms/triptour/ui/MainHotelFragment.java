package co.icoms.triptour.ui;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
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
import co.icoms.triptour.data.adapters.MainHotelAdapter;
import co.icoms.triptour.utils.Final;

public class MainHotelFragment extends Fragment implements MainHotelAdapter.Listener{
    private RecyclerView recyclerViewHotel;
    List<MainHotelCell> listHotel= new ArrayList<>();
    MainHotelAdapter adapterHotel = new MainHotelAdapter(listHotel, getContext(), this);
    SwipeRefreshLayout swipeRefreshMainHotel;
    TextView textViewEmptyList;
    boolean modifyRecycleView=true;


    private int previousTotal = 0;
    private boolean loading = true;
    private int visibleThreshold = 5;
    int firstVisibleItem, visibleItemCount, totalItemCount;
    String tableHotel;

    @Override
    public void onClick(Bundle bundle) {
            Intent intent = new Intent(getContext(), DetailHotelActivity.class);
            intent.putExtra(Final.DataHotel.DATA, bundle);
            saveLogin(bundle.getString(Final.DataHotel.PLACE), bundle.getInt(Final.DataHotel.ID));
            startActivity(intent);
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        adapterHotel.setPlace(getArguments().getString("place"));
        Log.e("TAG Execute", "Fragment onCreate");


    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        //tableHotel="ceiba";
        Log.e("TAG Execute", "Fragment onCreateView");
        tableHotel=getArguments().getString("place");

        if(tableHotel.equals("roatan"))
            tableHotel="hotels";
        else
            tableHotel="hotels_"+tableHotel;

        return inflater.inflate(R.layout.fragment_main_hotel, container, false);
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        Log.e("TAG Execute", "Fragment onActivityCreated");
        super.onActivityCreated(savedInstanceState);

        textViewEmptyList=(TextView)this.getView().findViewById(R.id.text_view_empty_list);

        //Pull to Refresh
        swipeRefreshMainHotel = (SwipeRefreshLayout)this.getView().findViewById(R.id.swipe_refresh_main_hotel);
        swipeRefreshMainHotel.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                //if(modifyRecycleView){

                    listHotel.clear();
                    Log.e("AddItem", "Refresh");
                    addItemHotel(1);
                //}
            }
        });
        addItemHotel(1);



        recyclerViewHotel = (RecyclerView) this.getView().findViewById(R.id.recycler_view_hotel);
        final LinearLayoutManager llm = new LinearLayoutManager(getContext());
        recyclerViewHotel.setLayoutManager(llm);
        recyclerViewHotel.setAdapter(adapterHotel);

        recyclerViewHotel.setHasFixedSize(true);


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
                    Log.e("AddItem", "Scroll Try");
                    //if(modifyRecycleView){
                        Log.e("AddItem", "Scroll");
                        addItemHotel(1+totalItemCount);
                    //}
                }
            }
        });


    }

    void addItemHotel(int n){
        final Integer number = new Integer(n);

        ConnectivityManager cm =
                (ConnectivityManager)getContext().getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetwork = cm.getActiveNetworkInfo();

       // if(activeNetwork!=null && activeNetwork.isConnected()&&modifyRecycleView) {
        if(activeNetwork!=null && activeNetwork.isConnected()) {
            modifyRecycleView=false;
            ParseQuery query = new ParseQuery(this.tableHotel);
            query.whereGreaterThanOrEqualTo("number", number);
            query.setLimit(5);
            query.findInBackground(new FindCallback<ParseObject>() {
                @Override
                public void done(List<ParseObject> item, ParseException e) {
                    int size;
                    if (item != null && ((size = item.size()) > 0)) {
                        //Log.e("TAG number ", String.valueOf(number));
                        //Log.e("TAG Size ", String.valueOf(listHotel.size()));
                        for (int k = 0; k < size; k++) {
                            listHotel.add(new MainHotelCell(
                                    item.get(k).getInt("number"),
                                    item.get(k).getString("name"),
                                    item.get(k).getString("url"),
                                    item.get(k).getInt("price"),
                                    item.get(k).getInt("stars")));
                            adapterHotel.notifyDataSetChanged();
                        }
                        swipeRefreshMainHotel.setRefreshing(false);
                        //Log.e("TAG size 2 ", String.valueOf(number));
                        //Log.e("TAG number ", String.valueOf(listHotel.get(number-1).getId()));
                    }

                    //modifyRecycleView=true;


                    if (listHotel.size() == 0)
                        textViewEmptyList.setVisibility(View.VISIBLE);
                    else
                        textViewEmptyList.setVisibility(View.INVISIBLE);
                    Log.e("AddItem", "Finish");
                }
            });
        }
    }

    private void saveLogin(String place, int id){
        SharedPreferences prefs = getContext().getSharedPreferences(Final.DataHotel.DATA, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = prefs.edit();

        editor.putString(Final.DataHotel.PLACE, place);
        editor.putInt(Final.DataHotel.ID, id);

        editor.commit();
    }
}