package co.icoms.triptour.data.adapters;

import android.content.Context;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.NetworkImageView;

import java.util.List;

import co.icoms.triptour.R;
import co.icoms.triptour.ui.DetailHotelImageCell;
import co.icoms.triptour.utils.MySingleton;

public class DetailHotelImageAdapter extends RecyclerView.Adapter<DetailHotelImageAdapter.DetailHotelImageViewHolder>{

    public List<DetailHotelImageCell> detailHotelImage;
    private Context context;

    public DetailHotelImageAdapter(List<DetailHotelImageCell> detailHotelImage, Context context){
        this.detailHotelImage = detailHotelImage;
        this.context=context;
    }

    @Override
    public DetailHotelImageViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.cell_detail_hotel_image, parent, false);
        DetailHotelImageViewHolder detailHotelImageViewHolder = new DetailHotelImageViewHolder(view);
        return detailHotelImageViewHolder;
    }

    @Override
    public void onBindViewHolder(final DetailHotelImageViewHolder holder, int position) {
        ImageLoader mImageLoader;

        // Get the ImageLoader through your singleton class.
        mImageLoader = MySingleton.getInstance(this.context).getImageLoader();
        holder.photo.setImageUrl(detailHotelImage.get(position).getPhotoUrl(),mImageLoader);
    }

    @Override
    public int getItemCount() {
        return this.detailHotelImage.size();
    }

    public static class DetailHotelImageViewHolder extends RecyclerView.ViewHolder {
        CardView cardView;
        NetworkImageView photo;

        DetailHotelImageViewHolder(View itemView) {
            super(itemView);
            cardView = (CardView)itemView.findViewById(R.id.card_view);
            photo = (NetworkImageView)itemView.findViewById(R.id.photo);
        }
    }

    @Override
    public void onAttachedToRecyclerView(RecyclerView recyclerView) {
        super.onAttachedToRecyclerView(recyclerView);
    }
}