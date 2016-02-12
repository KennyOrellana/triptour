package co.icoms.triptour.data.adapters;

import android.content.Context;
import android.content.SharedPreferences;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.EditorInfo;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.parse.ParseObject;

import java.util.List;

import co.icoms.triptour.R;
import co.icoms.triptour.ui.DetailHotelReviewCell;
import co.icoms.triptour.utils.Final;

public class DetailHotelReviewAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder>{

    public List<DetailHotelReviewCell> detailHotelReview;
    private Context context;
    private static int stars;

    public DetailHotelReviewAdapter(List<DetailHotelReviewCell> detailHotelReview, Context context){
        this.detailHotelReview = detailHotelReview;
        this.context=context;

    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view;
        RecyclerView.ViewHolder holder;


        switch (viewType){
            case Final.ViewTypeHotelReview.INPUT_REVIEW:
                view = LayoutInflater.from(parent.getContext()).inflate(R.layout.cell_detail_hotel_input_review, parent, false);
                //DetailHotelInputReviewViewHolder detailHotelInputReviewViewHolder= new DetailHotelInputReviewViewHolder(view);
                holder = new DetailHotelInputReviewViewHolder(view);
                break;

            default:

            case Final.ViewTypeHotelReview.REVIEW:
                view = LayoutInflater.from(parent.getContext()).inflate(R.layout.cell_detail_hotel_review, parent, false);
                //DetailHotelReviewViewHolder detailHotelReviewViewHolder = new DetailHotelReviewViewHolder(view);
                holder = new DetailHotelReviewViewHolder(view);
                break;


        }

        return holder;
    }

    @Override
    public int getItemViewType(int position) {
        if(position==0)
            return Final.ViewTypeHotelReview.INPUT_REVIEW;
        else
            return Final.ViewTypeHotelReview.REVIEW;
    }

    @Override
    public void onBindViewHolder(final RecyclerView.ViewHolder viewHolder, int position) {

        if(position==0) {
            DetailHotelInputReviewViewHolder holder = (DetailHotelInputReviewViewHolder)viewHolder;

            holder.star5.setImageResource(R.drawable.star_empty);
            holder.star5.setVisibility(View.INVISIBLE);
            holder.star4.setImageResource(R.drawable.star_full);
            holder.star4.setVisibility(View.INVISIBLE);
            holder.star3.setImageResource(R.drawable.star_full);
            holder.star3.setVisibility(View.INVISIBLE);
            holder.star2.setImageResource(R.drawable.star_full);
            holder.star2.setVisibility(View.INVISIBLE);
            holder.star1.setImageResource(R.drawable.star_full);
            holder.star1.setVisibility(View.INVISIBLE);

            holder.star5.setVisibility(View.VISIBLE);
            holder.star4.setVisibility(View.VISIBLE);
            holder.star3.setVisibility(View.VISIBLE);
            holder.star2.setVisibility(View.VISIBLE);
            holder.star1.setVisibility(View.VISIBLE);
        }
        else{
            DetailHotelReviewViewHolder holder = (DetailHotelReviewViewHolder) viewHolder;

            holder.textViewDate.setText(detailHotelReview.get(position).getDate());
            holder.textViewReview.setText(String.valueOf(detailHotelReview.get(position).getReview()));

            holder.star5.setImageResource(R.drawable.star_full);
            holder.star4.setImageResource(R.drawable.star_full);
            holder.star3.setImageResource(R.drawable.star_full);
            holder.star2.setImageResource(R.drawable.star_full);
            holder.star1.setImageResource(R.drawable.star_full);

            holder.star5.setVisibility(View.VISIBLE);
            holder.star4.setVisibility(View.VISIBLE);
            holder.star3.setVisibility(View.VISIBLE);
            holder.star2.setVisibility(View.VISIBLE);
            holder.star1.setVisibility(View.VISIBLE);
        }
    }

    @Override
    public int getItemCount() {
        return this.detailHotelReview.size();
    }

    public static class DetailHotelReviewViewHolder extends RecyclerView.ViewHolder {
        CardView cardView;
        TextView textViewReview;
        TextView textViewDate;
        ImageView star1;
        ImageView star2;
        ImageView star3;
        ImageView star4;
        ImageView star5;

        DetailHotelReviewViewHolder(View itemView) {
            super(itemView);
            cardView = (CardView)itemView.findViewById(R.id.card_view);
            textViewReview = (TextView)itemView.findViewById(R.id.text_view_review);
            textViewDate = (TextView)itemView.findViewById(R.id.text_view_date);
            star1 = (ImageView)itemView.findViewById(R.id.star_1);
            star2 = (ImageView)itemView.findViewById(R.id.star_2);
            star3 = (ImageView)itemView.findViewById(R.id.star_3);
            star4 = (ImageView)itemView.findViewById(R.id.star_4);
            star5 = (ImageView)itemView.findViewById(R.id.star_5);
        }
    }

    public static class DetailHotelInputReviewViewHolder extends RecyclerView.ViewHolder {
        CardView cardView;
        EditText editTextReview;
        Button buttonSend;
        ImageView star1;
        ImageView star2;
        ImageView star3;
        ImageView star4;
        ImageView star5;
        int stars;

        DetailHotelInputReviewViewHolder(final View itemView) {
            super(itemView);
            cardView = (CardView)itemView.findViewById(R.id.card_view);
            editTextReview = (EditText)itemView.findViewById(R.id.edit_text_review);
            //buttonSend = (Button)itemView.findViewById(R.id.button_send);
            star1 = (ImageView)itemView.findViewById(R.id.star_1);
            star2 = (ImageView)itemView.findViewById(R.id.star_2);
            star3 = (ImageView)itemView.findViewById(R.id.star_3);
            star4 = (ImageView)itemView.findViewById(R.id.star_4);
            star5 = (ImageView)itemView.findViewById(R.id.star_5);

            star1.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    star5.setImageResource(R.drawable.star_empty);
                    star4.setImageResource(R.drawable.star_empty);
                    star3.setImageResource(R.drawable.star_empty);
                    star2.setImageResource(R.drawable.star_empty);
                    star1.setImageResource(R.drawable.star_full);
                    stars=1;

                }
            });

            star2.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    star5.setImageResource(R.drawable.star_empty);
                    star4.setImageResource(R.drawable.star_empty);
                    star3.setImageResource(R.drawable.star_empty);
                    star2.setImageResource(R.drawable.star_full);
                    star1.setImageResource(R.drawable.star_full);
                    stars=2;
                }
            });

            star3.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    star5.setImageResource(R.drawable.star_empty);
                    star4.setImageResource(R.drawable.star_empty);
                    star3.setImageResource(R.drawable.star_full);
                    star2.setImageResource(R.drawable.star_full);
                    star1.setImageResource(R.drawable.star_full);
                    stars=3;
                }
            });

            star4.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    star5.setImageResource(R.drawable.star_empty);
                    star4.setImageResource(R.drawable.star_full);
                    star3.setImageResource(R.drawable.star_full);
                    star2.setImageResource(R.drawable.star_full);
                    star1.setImageResource(R.drawable.star_full);
                    stars=4;
                }
            });

            star5.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    star5.setImageResource(R.drawable.star_full);
                    star4.setImageResource(R.drawable.star_full);
                    star3.setImageResource(R.drawable.star_full);
                    star2.setImageResource(R.drawable.star_full);
                    star1.setImageResource(R.drawable.star_full);
                    stars = 5;
                }
            });

            final EditText editTextReview = (EditText)itemView.findViewById(R.id.edit_text_review);
            editTextReview.setOnEditorActionListener(new TextView.OnEditorActionListener() {
                @Override
                public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                    boolean handled = false;
                    if (actionId == EditorInfo.IME_ACTION_SEND) {

                        ParseObject newReview = new ParseObject("reviews_" + getPlace(itemView.getContext()));
                        newReview.put(Final.TableDetailHotelReview.REVIEW, editTextReview.getText().toString());
                        newReview.put(Final.TableDetailHotelReview.NUMBER, getHotelId(itemView.getContext()));
                        newReview.put(Final.TableDetailHotelReview.STARS, stars);
                        newReview.put(Final.TableDetailHotelReview.EMAIL, getEmail(itemView.getContext()));
                        newReview.saveInBackground();

                        editTextReview.setText("");
                        editTextReview.clearFocus();
                        handled = true;
                    }
                    return handled;
                }
            });
        }
    }

    public int getStars(){
        return 0;
    }

    public static String getEmail(Context context){
        SharedPreferences prefs = context.getSharedPreferences("login", Context.MODE_PRIVATE);
        return prefs.getString("email","");
    }

    public static int getHotelId(Context context){
        SharedPreferences prefs = context.getSharedPreferences(Final.DataHotel.DATA, Context.MODE_PRIVATE);
        return prefs.getInt(Final.DataHotel.ID, 0);
    }

    public static String getPlace(Context context){
        SharedPreferences prefs = context.getSharedPreferences(Final.DataHotel.DATA, Context.MODE_PRIVATE);
        return prefs.getString(Final.DataHotel.PLACE,"crash");
    }
}