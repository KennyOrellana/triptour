package co.icoms.triptour.data.adapters;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.NetworkImageView;

import java.util.List;

import co.icoms.triptour.R;
import co.icoms.triptour.ui.MainHotelCell;
import co.icoms.triptour.utils.Final;
import co.icoms.triptour.utils.MySingleton;

public class MainHotelAdapter extends RecyclerView.Adapter<MainHotelAdapter.HotelViewHolder>{

    public List<MainHotelCell> hotels;
    private Context context;
    Listener listener;

    public void setPlace(String place) {
        this.place = place;
    }

    private String place;

    public MainHotelAdapter(List<MainHotelCell> hotels, Context context, Listener listener){
        this.hotels = hotels;
        this.context=context;
        this.listener=listener;
    }

    public interface Listener{
        void onClick(Bundle bundle);
    }

    @Override
    public HotelViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.cell_hotel, parent, false);
        HotelViewHolder hotelViewHolder = new HotelViewHolder(view);
        return hotelViewHolder;
    }

    @Override
    public void onBindViewHolder(final HotelViewHolder holder, final int position) {
        holder.name.setText(hotels.get(position).getName());

        ImageLoader mImageLoader;
        //ImageView mImageView;


        // Get the ImageLoader through your singleton class.
        mImageLoader = MySingleton.getInstance(this.context).getImageLoader();
        //mImageLoader.get(hotels.get(position).getPhotoUrl(), ImageLoader.getImageListener(holder.photo, R.mipmap.def_image, R.mipmap.err_image));

        holder.photo.setImageUrl(hotels.get(position).getPhotoUrl(),mImageLoader);

        holder.price.setText(String.valueOf(hotels.get(position).getPrice()));

        switch (hotels.get(position).getCalification()){
            case 5:
                holder.star5.setImageResource(R.drawable.star_full);
            case 4:
                holder.star4.setImageResource(R.drawable.star_full);
            case 3:
                holder.star3.setImageResource(R.drawable.star_full);
            case 2:
                holder.star2.setImageResource(R.drawable.star_full);
            case 1:
                holder.star1.setImageResource(R.drawable.star_full);
            default:
                break;
        }
        //holder.star1.setImageResource(R.drawable.star_full);

        holder.photo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Bundle bundle=new Bundle();
                bundle.putString(Final.DataHotel.PLACE, place);
                bundle.putString(Final.DataHotel.NAME, hotels.get(position).getName());
                bundle.putInt(Final.DataHotel.ID, hotels.get(position).getId());
                bundle.putString(Final.DataHotel.IMAGE, hotels.get(position).getPhotoUrl());
                bundle.putInt(Final.DataHotel.PRICE, hotels.get(position).getPrice());
                bundle.putInt(Final.DataHotel.STARS, hotels.get(position).getCalification());

                listener.onClick(bundle);
            }
        });

    }

    @Override
    public int getItemCount() {
        return this.hotels.size();
    }

    public static class HotelViewHolder extends RecyclerView.ViewHolder {
        CardView cardView;
        TextView name;
        NetworkImageView photo;
        TextView price;
        ImageView star1;
        ImageView star2;
        ImageView star3;
        ImageView star4;
        ImageView star5;

        HotelViewHolder(View itemView) {
            super(itemView);
            cardView = (CardView)itemView.findViewById(R.id.card_view);
            name = (TextView)itemView.findViewById(R.id.name);
            photo = (NetworkImageView)itemView.findViewById(R.id.photo);
            price = (TextView)itemView.findViewById(R.id.price);
            star1 = (ImageView)itemView.findViewById(R.id.star_1);
            star2 = (ImageView)itemView.findViewById(R.id.star_2);
            star3 = (ImageView)itemView.findViewById(R.id.star_3);
            star4 = (ImageView)itemView.findViewById(R.id.star_4);
            star5 = (ImageView)itemView.findViewById(R.id.star_5);
        }
    }

    @Override
    public void onAttachedToRecyclerView(RecyclerView recyclerView) {
        super.onAttachedToRecyclerView(recyclerView);
    }
}