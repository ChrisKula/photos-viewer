package com.christiankula.albumviewer.photolist;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.christiankula.albumviewer.R;
import com.christiankula.albumviewer.models.Photo;
import com.squareup.picasso.Picasso;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;


public class PhotoAdapter extends RecyclerView.Adapter<PhotoAdapter.PhotoViewHolder> {
    private List<Photo> data;

    PhotoAdapter(List<Photo> data) {
        this.data = data;
    }

    @Override
    public PhotoViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item_photo, parent, false);

        return new PhotoViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(PhotoViewHolder holder, int position) {
        Photo photo = data.get(position);

        holder.tvPhotoTitle.setText(photo.getTitle());

        //TODO Add caching from disk before getting from network
        Picasso.with(holder.ivPhotoImage.getContext())
                .load(photo.getThumbnailUrl())
                .error(R.drawable.ic_broken_image_black_24dp)
                .into(holder.ivPhotoImage);
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    public void setData(List<Photo> data) {
        this.data = data;
        notifyDataSetChanged();
    }


    class PhotoViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.iv_photo_image)
        ImageView ivPhotoImage;

        @BindView(R.id.tv_photo_title)
        TextView tvPhotoTitle;

        PhotoViewHolder(View itemView) {
            super(itemView);

            ButterKnife.bind(this, itemView);
        }
    }
}
