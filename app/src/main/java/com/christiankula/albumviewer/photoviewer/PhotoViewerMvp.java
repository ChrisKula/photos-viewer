package com.christiankula.albumviewer.photoviewer;


import com.christiankula.albumviewer.models.Photo;
import com.christiankula.albumviewer.mvp.BasePresenter;
import com.christiankula.albumviewer.mvp.BaseView;

import java.util.List;

import io.reactivex.Flowable;

public interface PhotoViewerMvp {

    interface Model {
        /**
         * Returns a Flowable that emits the content of the Photo table whenever it's updated
         */
        Flowable<List<Photo>> loadPhotos();
    }

    interface View extends BaseView<Presenter> {
        /**
         * Returns the list given via Intent
         */
        List<Photo> getPhotosFromIntent();

        /**
         * Returns the photo id to select given via Intent
         */
        int getSelectedPhotoIdFromIntent();

        /**
         * Setups the view pager with the given List of {@link Photo}
         */
        void setupPhotosViewPager(List<Photo> photos);

        /**
         * Setups the view pager with the given List of {@link Photo} and selects immediately the item at the given
         * index
         */
        void setupPhotosViewPager(List<Photo> photos, int selectedPhotoIndex);

        /**
         * Request the close of the View
         */
        void closeView();
    }

    interface Presenter extends BasePresenter<View> {

        /**
         * Called when {@link PhotoViewerMvp.View} is created
         */
        void onCreate();
    }
}
