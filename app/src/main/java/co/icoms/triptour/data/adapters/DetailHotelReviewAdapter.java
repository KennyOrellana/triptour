package co.icoms.triptour.data.adapters;

import android.content.Context;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

import co.icoms.triptour.R;
import co.icoms.triptour.ui.DetailHotelReviewCell;
import co.icoms.triptour.utils.Final;

public class DetailHotelReviewAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder>{

    public List<DetailHotelReviewCell> detailHotelReview;
    private Context context;

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

            holder.star5.setImageResource(R.drawable.star_full);
            holder.star5.setVisibility(View.INVISIBLE);
            holder.star4.setImageResource(R.drawable.star_full);
            holder.star4.setVisibility(View.INVISIBLE);
            holder.star3.setImageResource(R.drawable.star_full);
            holder.star3.setVisibility(View.INVISIBLE);
            holder.star2.setImageResource(R.drawable.star_full);
            holder.star2.setVisibility(View.INVISIBLE);
            holder.star1.setImageResource(R.drawable.star_full);
            holder.star1.setVisibility(View.INVISIBLE);

            int x=5;
            switch (x) {
                case 5:
                    holder.star5.setVisibility(View.VISIBLE);
                case 4:
                    holder.star4.setVisibility(View.VISIBLE);
                case 3:
                    holder.star3.setVisibility(View.VISIBLE);
                case 2:
                    holder.star2.setVisibility(View.VISIBLE);
                case 1:
                    holder.star1.setVisibility(View.VISIBLE);
                default:
                    break;
            }
            //DetailHotelInputReviewViewHolder holder = new DetailHotelInputReviewViewHolder(viewHolder.itemView);

            /*
            holder.buttonSend.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    //TODO guarda review en parse
                }
            });
            */
        }
        else{
            DetailHotelReviewViewHolder holder = (DetailHotelReviewViewHolder) viewHolder;
            //DetailHotelReviewViewHolder holder = new DetailHotelReviewViewHolder(viewHolder.itemView);

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

        DetailHotelInputReviewViewHolder(View itemView) {
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
                    stars=5;
                }
            });
        }
    }

}