package com.christiankula.albumviewer.photolist.adapters;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.christiankula.albumviewer.R;
import com.christiankula.albumviewer.models.Photo;
import com.christiankula.albumviewer.photolist.mvp.PhotoListMvp;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;


public class PhotoAdapter extends RecyclerView.Adapter<PhotoAdapter.PhotoViewHolder> {

    private List<Photo> data;

    private final int style;

    private OnItemClickListener onItemClickListener;

    public PhotoAdapter(int style) {
        data = new ArrayList<>();
        this.style = style;
    }

    @Override
    public PhotoViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        int layoutResId;

        switch (this.style) {
            case PhotoListMvp.Model.STYLE_LIST:
                layoutResId = R.layout.list_item_photo;
                break;

            case PhotoListMvp.Model.STYLE_GRID:
            default:
                layoutResId = R.layout.grid_item_photo;
                break;
        }

        View itemView = LayoutInflater.from(parent.getContext()).inflate(layoutResId, parent, false);

        return new PhotoViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(PhotoViewHolder holder, int position) {
        final Photo photo = data.get(position);

        holder.tvPhotoTitle.setText(photo.getTitle());

        //TODO Add caching from disk before getting from network
        Picasso.with(holder.ivPhotoImage.getContext())
                .load(photo.getThumbnailUrl())
                .error(R.drawable.ic_broken_image_black_24dp)
                .into(holder.ivPhotoImage);

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (onItemClickListener != null) {
                    onItemClickListener.onItemClick(photo);
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    public void setData(List<Photo> data) {
        this.data = data;
        notifyDataSetChanged();
    }

    public void setOnItemClickListener(OnItemClickListener listener) {
        this.onItemClickListener = listener;
    }

    public interface OnItemClickListener {
        void onItemClick(Photo photo);
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
