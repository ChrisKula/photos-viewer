package com.christiankula.albumviewer.photolist.adapters;


import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.christiankula.albumviewer.R;
import com.christiankula.albumviewer.models.Album;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class AlbumAdapter extends RecyclerView.Adapter<AlbumAdapter.AlbumViewHolder> {

    private List<Album> data;

    public AlbumAdapter() {
        data = new ArrayList<>();
    }

    @Override
    public AlbumViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.grid_item_album, parent, false);

        return new AlbumViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(AlbumViewHolder holder, int position) {
        Album album = data.get(position);

        holder.tvAlbumId.setText(holder.tvAlbumId.getContext().getString(R.string.album_number_s, album.getId()));
        holder.tvAlbumPhotosCount.setText(holder.tvAlbumPhotosCount.getContext().getString(R.string.album_photos_count_n, album.getPhotos().size()));

        if (!album.getPhotos().isEmpty()) {
            Picasso.with(holder.ivAlbumCover.getContext())
                    .load(album.getPhotos().get(0).getThumbnailUrl())
                    .error(R.drawable.ic_broken_image_black_24dp)
                    .into(holder.ivAlbumCover);
        }
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    public void setData(List<Album> data) {
        this.data = data;
        notifyDataSetChanged();
    }

    class AlbumViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.iv_album_cover)
        ImageView ivAlbumCover;

        @BindView(R.id.tv_album_photos_id)
        TextView tvAlbumId;

        @BindView(R.id.tv_album_photos_count)
        TextView tvAlbumPhotosCount;

        AlbumViewHolder(View itemView) {
            super(itemView);

            ButterKnife.bind(this, itemView);
        }
    }
}
