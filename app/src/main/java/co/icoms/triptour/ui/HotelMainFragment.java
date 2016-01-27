package co.icoms.triptour.ui;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

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

    @Override
    public void onCreate(Bundle savedInstanceState) {

        listHotel.add(new HotelCell("Mayan Princess",
                "http://www.hondurastips.hn/wp-content/uploads/2011/02/MAYAN-PRINCESS-2.jpg",
                100,
                5));
        listHotel.add(new HotelCell("Anthony's Key Resort",
                "http://www.destination360.com/contents/pictures/roatan/anthonys-key-resort-bungalows.jpg",
                90,
                5));

        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.hotel_main_fragment, container, false);
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        recyclerViewHotel = (RecyclerView) this.getView().findViewById(R.id.recycler_view_hotel);
        final LinearLayoutManager llm = new LinearLayoutManager(getContext());
        recyclerViewHotel.setLayoutManager(llm);
        recyclerViewHotel.setAdapter(adapterHotel);

        //recyclerViewHotel.addItemDecoration(new DividerItemDecoration(getActivity(), DividerItemDecoration.VERTICAL_LIST));
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
                    // End has been reached

                    Log.i("Yaeye!", "end called");
                    listHotel.add(new HotelCell("Mayan Princess",
                            "http://www.hondurastips.hn/wp-content/uploads/2011/02/MAYAN-PRINCESS-2.jpg",
                            llm.getItemCount(),
                            llm.getItemCount()));

                    // Do something
                    Log.i("Yaeye!", String.valueOf(llm.getItemCount()));
                    loading = true;
                    adapterHotel.notifyDataSetChanged();

                    //adapterHotel = new HotelAdapter(listHotel);
                    //recyclerViewHotel.setAdapter(adapterHotel);
                }
            }
        });
    }
}