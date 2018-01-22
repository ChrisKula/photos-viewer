package com.christiankula.albumviewer.photoviewer;

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
}
