package com.christiankula.albumviewer.photoviewer;

import com.christiankula.albumviewer.models.Photo;

import java.util.List;

import javax.inject.Inject;


public class PhotoViewerPresenter implements PhotoViewerMvp.Presenter {

    private PhotoViewerMvp.View view;
    private PhotoViewerMvp.Model model;

    @Inject
    public PhotoViewerPresenter(PhotoViewerMvp.Model model) {
        this.model = model;
    }

    @Override
    public void attachView(PhotoViewerMvp.View view) {
        this.view = view;
    }

    @Override
    public void detachView() {
        this.view = null;
    }

    @Override
    public void onCreate() {
        List<Photo> photos = view.getPhotosFromIntent();

        if (photos != null && !photos.isEmpty()) {
            view.setupPhotosViewPager(photos);
        } else {
            view.closeView();
        }
    }
}
