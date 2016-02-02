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
import co.icoms.triptour.data.adapters.DetailHotelImageAdapter;

public class DetailHotelImageFragment extends Fragment {
    private RecyclerView recyclerViewHotel;
    List<DetailHotelImageCell> listImages= new ArrayList<>();

    DetailHotelImageAdapter adapterHotel = new DetailHotelImageAdapter(listImages, getContext());

    private int previousTotal = 0;
    private boolean loading = true;
    private int visibleThreshold = 5;
    int firstVisibleItem, visibleItemCount, totalItemCount;
    String tableDetailHotelImages;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        //tableDetailHotelImages="ceiba";
        tableDetailHotelImages=getArguments().getString("place")+"_hotel_image";


        for(int k=1;k<6;k++) {
            addItem(k);
        }

        return inflater.inflate(R.layout.fragment_detail_hotel_image, container, false);
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        recyclerViewHotel = (RecyclerView) this.getView().findViewById(R.id.recycler_view_images);
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
                    addItem(1+totalItemCount);
                }
            }
        });
    }

    //TODO arreglar el parametro extras
    void addItem(int n){
        final Integer number = new Integer(n);

        ParseQuery query = new ParseQuery(this.tableDetailHotelImages);
        query.whereEqualTo("number", number);
        query.findInBackground(new FindCallback<ParseObject>() {
            @Override
            public void done(List<ParseObject> item, ParseException e) {
                if (item != null && item.size() > 0) {
                    listImages.set(number, new DetailHotelImageCell(
                                                item.get(0).getString("url")));
                    adapterHotel.notifyDataSetChanged();
                }
            }
        });
    }
}