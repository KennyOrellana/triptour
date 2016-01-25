package co.icoms.triptour.ui;

/**
 * Created by kenny on 20/1/16.
 */

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.android.volley.toolbox.ImageLoader;

import co.icoms.triptour.R;
import co.icoms.triptour.utils.MySingleton;

public class ScreenSlidePageFragment extends Fragment {

    private static final String URL = "url";
    private static final String INDEX = "index";

    private String url;
    private int index;

    public static ScreenSlidePageFragment newInstance(String url, int index) {

        // Instantiate a new fragment
        ScreenSlidePageFragment fragment = new ScreenSlidePageFragment();

        // Save the parameters
        Bundle bundle = new Bundle();
        bundle.putString(URL, url);
        bundle.putInt(INDEX, index);
        fragment.setArguments(bundle);
        fragment.setRetainInstance(true);

        return fragment;

    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        // Load parameters when the initial creation of the fragment is done
        this.url = (getArguments() != null) ? getArguments().getString(
                URL) : "http://news.aroundcommodities.com/assets/backgrounds/photo-default-th.png";
        this.index = (getArguments() != null) ? getArguments().getInt(INDEX)
                : -1;

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        ViewGroup rootView = (ViewGroup) inflater.inflate(
                R.layout.fragment_screen_slide_page, container, false);

        ImageLoader mImageLoader;
        ImageView mImageView;

        // The URL for the image that is being loaded.
        final String IMAGE_URL = new String(url);
        mImageView = (ImageView) rootView.findViewById(R.id.imageView);

        mImageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //getActivity().finish();
                Intent intent = new Intent(getContext(), MainActivity.class);
                startActivity(intent);
            }
        });

        // Get the ImageLoader through your singleton class.
        mImageLoader = MySingleton.getInstance(getContext()).getImageLoader();
        mImageLoader.get(IMAGE_URL, ImageLoader.getImageListener(mImageView,
                R.mipmap.def_image, R.mipmap.err_image));

        return rootView;
    }
}
