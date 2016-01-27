package co.icoms.triptour.data.adapters;

import android.content.Context;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.android.volley.toolbox.ImageLoader;

import java.util.List;

import co.icoms.triptour.R;
import co.icoms.triptour.ui.HotelCell;
import co.icoms.triptour.utils.MySingleton;

public class HotelAdapter extends RecyclerView.Adapter<HotelAdapter.HotelViewHolder>{

    public List<HotelCell> hotels;
    private Context context;

    public HotelAdapter(List<HotelCell> hotels, Context context){
        this.hotels = hotels;
        this.context=context;
    }

    @Override
    public HotelViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.hotel_cell, parent, false);
        HotelViewHolder hotelViewHolder = new HotelViewHolder(view);
        return hotelViewHolder;
    }

    @Override
    public void onBindViewHolder(final HotelViewHolder holder, int position) {
        holder.name.setText(hotels.get(position).getName());

        //holder.photo.setImageResource(R.mipmap.err_image);

        ImageLoader mImageLoader;
        //ImageView mImageView;

        // Get the ImageLoader through your singleton class.
        mImageLoader = MySingleton.getInstance(this.context).getImageLoader();
        mImageLoader.get(hotels.get(position).getPhotoUrl(), ImageLoader.getImageListener(holder.photo,
                R.mipmap.def_image, R.mipmap.err_image));

        holder.price.setText(String.valueOf(hotels.get(position).getPrice()));

        //TODO estrellas segun calificación
        // Retrieves an image specified by the URL, displays it in the UI.
        holder.calification.setImageResource(R.mipmap.start);

    }

    @Override
    public int getItemCount() {
        return this.hotels.size();
    }

    public static class HotelViewHolder extends RecyclerView.ViewHolder {
        CardView cardView;
        TextView name;
        ImageView photo;
        TextView price;
        ImageView calification;

        HotelViewHolder(View itemView) {
            super(itemView);
            cardView = (CardView)itemView.findViewById(R.id.card_view);
            name = (TextView)itemView.findViewById(R.id.name);
            photo = (ImageView)itemView.findViewById(R.id.photo);
            price = (TextView)itemView.findViewById(R.id.price);
            calification = (ImageView)itemView.findViewById(R.id.calification);
        }
    }

    @Override
    public void onAttachedToRecyclerView(RecyclerView recyclerView) {
        super.onAttachedToRecyclerView(recyclerView);
    }
}