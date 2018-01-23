package com.christiankula.albumviewer.photoviewer;

import com.christiankula.albumviewer.models.Photo;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;


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
        final int id = view.getSelectedPhotoIdFromIntent();

        if (id >= 0) {
            model.loadPhotos()
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .first(new ArrayList<Photo>())
                    .subscribe(new Consumer<List<Photo>>() {
                        @Override
                        public void accept(List<Photo> photos) throws Exception {
                            int pos = -1;

                            for (int i = 0; i < photos.size(); i++) {
                                if (id == photos.get(i).getId()) {
                                    pos = i;
                                    break;
                                }
                            }

                            if (pos >= 0) {
                                view.setupPhotosViewPager(photos, pos);
                            } else {
                                view.closeView();
                            }
                        }
                    });
        } else {
            List<Photo> photos = view.getPhotosFromIntent();

            if (photos != null && !photos.isEmpty()) {
                view.setupPhotosViewPager(photos);
            } else {
                view.closeView();
            }
        }
    }
}
