package co.icoms.triptour.ui;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.parse.FindCallback;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQuery;

import java.util.ArrayList;
import java.util.List;

import co.icoms.triptour.R;
import co.icoms.triptour.data.adapters.HotelAdapter;

public class HotelMainFragment extends Fragment {
    private RecyclerView recyclerViewHotel;
    List<HotelCell> listHotel= new ArrayList<>();
    HotelAdapter adapterHotel = new HotelAdapter(listHotel, getContext());

    private int previousTotal = 0;
    private boolean loading = true;
    private int visibleThreshold = 5;
    int firstVisibleItem, visibleItemCount, totalItemCount;
    String tableHotel;

    @Override
    public void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        tableHotel=MainActivity.place;

        if(tableHotel.equals("roatan"))
            tableHotel="hotels";
        else
            tableHotel="hotels_"+tableHotel;

        for(int k=1;k<6;k++) {
            addItemHotel(k);
        }

        return inflater.inflate(R.layout.hotel_main_fragment, container, false);
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

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
                    addItemHotel(1+totalItemCount);
                }
            }
        });
    }

    void addItemHotel(int n){
        final Integer number = new Integer(n);

        ParseQuery query = new ParseQuery(this.tableHotel);
        query.whereEqualTo("number", number);
        query.findInBackground(new FindCallback<ParseObject>() {
            @Override
            public void done(List<ParseObject> item, ParseException e) {
                if (item != null && item.size() > 0) {
                    listHotel.add(new HotelCell(item.get(0).getString("name"),
                                                item.get(0).getString("url"),
                                                item.get(0).getInt("price"),
                                                item.get(0).getInt("stars")));
                    adapterHotel.notifyDataSetChanged();
                }
            }
        });
    }
}