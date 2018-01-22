package com.christiankula.albumviewer.photoviewer.page;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.christiankula.albumviewer.R;
import com.christiankula.albumviewer.models.Photo;
import com.squareup.picasso.Picasso;

import org.parceler.Parcels;


public class PhotoPageFragment extends Fragment {

    private static final String PHOTO_PARCELABLE_KEY = "photo_parcelable";

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        ViewGroup rootView = (ViewGroup) inflater.inflate(
                R.layout.fragment_photo_page, container, false);

        ImageView ivPhotoImage = rootView.findViewById(R.id.iv_photo_image);
        TextView tvPhotoTitle = rootView.findViewById(R.id.tv_photo_title);

        Photo p = Parcels.unwrap(getArguments().getParcelable(PHOTO_PARCELABLE_KEY));

        if (p != null) {
            tvPhotoTitle.setText(p.getTitle());

            Picasso.with(rootView.getContext())
                    .load(p.getUrl())
                    .into(ivPhotoImage);

        }

        return rootView;
    }

    public static PhotoPageFragment newInstance(Photo photo) {
        PhotoPageFragment photoPageFragment = new PhotoPageFragment();

        Bundle bundle = new Bundle();

        bundle.putParcelable(PHOTO_PARCELABLE_KEY, Parcels.wrap(photo));
        photoPageFragment.setArguments(bundle);

        return photoPageFragment;
    }
}
