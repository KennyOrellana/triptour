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
import co.icoms.triptour.data.adapters.DetailHotelReviewAdapter;
import co.icoms.triptour.utils.Final;

public class DetailHotelReviewFragment extends Fragment {
    private RecyclerView recyclerViewDetailHotelReview;
    List<DetailHotelReviewCell> listReviews= new ArrayList<>();

     DetailHotelReviewAdapter adapterDetailHotelReview = new DetailHotelReviewAdapter(listReviews, getContext());

    private int previousTotal = 0;
    private boolean loading = true;
    private int visibleThreshold = 5;
    int firstVisibleItem, visibleItemCount, totalItemCount;
    String tableDetailHotelReviews;
    SwipeRefreshLayout swipeRefreshDetailHotelReviews;
    TextView textViewEmptyList;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        tableDetailHotelReviews="reviews_"+getArguments().getString(Final.DataHotel.PLACE);

        return inflater.inflate(R.layout.fragment_detail_hotel_review, container, false);
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        textViewEmptyList=(TextView)this.getView().findViewById(R.id.text_view_empty_list_review);
        addItem(getArguments().getInt(Final.DataHotel.ID));

        recyclerViewDetailHotelReview = (RecyclerView) this.getView().findViewById(R.id.recycler_view_review);
        final LinearLayoutManager llmReview = new LinearLayoutManager(getContext());
        recyclerViewDetailHotelReview.setLayoutManager(llmReview);
        recyclerViewDetailHotelReview.setAdapter(adapterDetailHotelReview);

        recyclerViewDetailHotelReview.setHasFixedSize(true);

        //Add generic element to show Input Review
        listReviews.add(new DetailHotelReviewCell());
        adapterDetailHotelReview.notifyDataSetChanged();
        /*
        recyclerViewDetailHotelReview.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);

                visibleItemCount = recyclerView.getChildCount();
                totalItemCount = llmReview.getItemCount();
                firstVisibleItem = llmReview.findFirstVisibleItemPosition();

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
        swipeRefreshDetailHotelReviews = (SwipeRefreshLayout)this.getView().findViewById(R.id.swipe_refresh_detail_hotel_review);
        swipeRefreshDetailHotelReviews.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                listReviews.clear();
                //Add generic element to show Input Review
                listReviews.add(new DetailHotelReviewCell());
                adapterDetailHotelReview.notifyDataSetChanged();

                addItem(getArguments().getInt(Final.DataHotel.ID));

                swipeRefreshDetailHotelReviews.setRefreshing(false);
            }
        });



    }

    void addItem(int n){

        final Integer number = new Integer(n);

        ConnectivityManager cm =
                (ConnectivityManager)getContext().getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetwork = cm.getActiveNetworkInfo();

        if(activeNetwork!=null && activeNetwork.isConnected()) {
            //listReviews.clear();

            ParseQuery query = new ParseQuery(this.tableDetailHotelReviews);
            query.whereEqualTo("number", number);
            query.findInBackground(new FindCallback<ParseObject>() {
                @Override
                public void done(List<ParseObject> item, ParseException e) {
                    int size;
                    if (item != null && ((size = item.size()) > 0)) {
                        for (int k = 0; k < size; k++) {
                            try {
                                listReviews.add(new DetailHotelReviewCell(
                                                item.get(k).getString(Final.TableDetailHotelReview.EMAIL),
                                                item.get(k).getString(Final.TableDetailHotelReview.REVIEW),
                                                item.get(k).getInt(Final.TableDetailHotelReview.STARS),
                                                Final.DateFormat.DateFormat.format(Final.DateFormat.ParseFormat.parse(item.get(k).getCreatedAt().toString()))));
                            } catch (java.text.ParseException e1) {
                                e1.printStackTrace();
                            }
                            Log.e("DATE", item.get(k).getCreatedAt().toString());
                            adapterDetailHotelReview.notifyDataSetChanged();
                        }
                    }

                    if (listReviews.size() == 0)
                        textViewEmptyList.setVisibility(View.VISIBLE);
                    else
                        textViewEmptyList.setVisibility(View.INVISIBLE);
                }
            });
        }
    }


}