package com.christiankula.albumviewer.injection.components;


import com.christiankula.albumviewer.photolist.PhotoListActivity;

public interface AlbumViewerComponent {
    void inject(PhotoListActivity photoListActivity);
}
