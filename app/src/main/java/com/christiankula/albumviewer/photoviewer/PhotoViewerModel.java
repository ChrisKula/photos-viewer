package com.christiankula.albumviewer.photoviewer;

import com.christiankula.albumviewer.models.Photo;
import com.christiankula.albumviewer.persistence.PhotoDao;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.Flowable;

public class PhotoViewerModel implements PhotoViewerMvp.Model {

    private final PhotoDao photoDao;

    @Inject
    public PhotoViewerModel(PhotoDao photoDao) {
        this.photoDao = photoDao;
    }

    @Override
    public Flowable<List<Photo>> loadPhotos() {
        return photoDao.loadPhotos();
    }
}
