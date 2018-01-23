package com.christiankula.albumviewer.injection.components;


import com.christiankula.albumviewer.photolist.PhotoListActivity;
import com.christiankula.albumviewer.photoviewer.PhotoViewerActivity;

public interface AlbumViewerComponent {
    void inject(PhotoListActivity target);

    void inject(PhotoViewerActivity target);
}
