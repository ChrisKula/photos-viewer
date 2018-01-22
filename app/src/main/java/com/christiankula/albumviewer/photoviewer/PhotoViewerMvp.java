package com.christiankula.albumviewer.photoviewer;


import com.christiankula.albumviewer.models.Photo;
import com.christiankula.albumviewer.mvp.BasePresenter;
import com.christiankula.albumviewer.mvp.BaseView;

import java.util.List;

public interface PhotoViewerMvp {

    interface Model {

    }

    interface View extends BaseView<Presenter> {
        List<Photo> getPhotosFromIntent();

        void setupPhotosViewPager(List<Photo> photos);

        void closeView();
    }

    interface Presenter extends BasePresenter<View> {
        void onCreate();
    }
}
