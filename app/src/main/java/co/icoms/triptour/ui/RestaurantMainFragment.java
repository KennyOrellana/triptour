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
import co.icoms.triptour.data.adapters.RestaurantAdapter;

public class RestaurantMainFragment extends Fragment {
    private RecyclerView recyclerViewRestaurant;
    List<RestaurantCell> listRestaurant= new ArrayList<>();
    RestaurantAdapter adapterRestaurant = new RestaurantAdapter(listRestaurant, getContext());

    private int previousTotal = 0;
    private boolean loading = true;
    private int visibleThreshold = 5;
    int firstVisibleItem, visibleItemCount, totalItemCount;
    String tableRestaurant;



    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        //tableRestaurant="ceiba";
        tableRestaurant="rest_"+getArguments().getString("place");

        for(int k=1;k<6;k++) {
            addItemRestaurant(k);
        }

        return inflater.inflate(R.layout.restaurant_main_fragment, container, false);
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        recyclerViewRestaurant = (RecyclerView) this.getView().findViewById(R.id.recycler_view_restaurant);
        final LinearLayoutManager llm = new LinearLayoutManager(getContext());
        recyclerViewRestaurant.setLayoutManager(llm);
        recyclerViewRestaurant.setAdapter(adapterRestaurant);

        recyclerViewRestaurant.setHasFixedSize(true);

        recyclerViewRestaurant.addOnScrollListener(new RecyclerView.OnScrollListener() {
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
                    addItemRestaurant(1+totalItemCount);
                }
            }
        });
    }

    void addItemRestaurant(int n){
        final Integer number = new Integer(n);

        ParseQuery query = new ParseQuery(this.tableRestaurant);
        query.whereEqualTo("number", number);
        query.findInBackground(new FindCallback<ParseObject>() {
            @Override
            public void done(List<ParseObject> item, ParseException e) {
                if (item != null && item.size() > 0) {
                    listRestaurant.add(new RestaurantCell(item.get(0).getString("name"),
                                                item.get(0).getString("url"),
                                                String.valueOf(item.get(0).getInt("min"))+" - "+String.valueOf(item.get(0).getInt("max")),
                                                item.get(0).getInt("stars")));
                    adapterRestaurant.notifyDataSetChanged();
                }
            }
        });
    }
}