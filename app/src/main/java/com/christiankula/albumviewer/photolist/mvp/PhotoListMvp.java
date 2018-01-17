package com.christiankula.albumviewer.photolist.mvp;


import com.christiankula.albumviewer.models.Photo;
import com.christiankula.albumviewer.mvp.BasePresenter;
import com.christiankula.albumviewer.mvp.BaseView;

import java.util.List;

import retrofit2.Call;

/**
 * Root interface for the Model, View and Presenter interfaces for the Photo list
 */
public interface PhotoListMvp {

    interface Model {

        /**
         * Requests photos from the corresponding API endpoint
         *
         * @return the call in charge of retrieving the photos from the corresponding API endpoint
         */
        Call<List<Photo>> requestPhotos();
    }

    interface View extends BaseView<Presenter> {

        /**
         * Displays the given List of {@link Photo}
         */
        void displayPhotos(List<Photo> photos);

        /**
         * Sets whether the spinning progress bar should be displayed
         */
        void setRefreshing(boolean refreshing);

        /**
         * Displays a message indicating that photos couldn't be retrieved
         */
        void showUnableToRetrievePhotosMessage();

        /**
         * Displays a message indicating there's no photo to display
         */
        void showNoPhotosToDisplayMessage();
    }

    interface Presenter extends BasePresenter<View> {

        /**
         * Called when {@link PhotoListMvp.View} is resumed
         */
        void onCreate();

        /**
         * Called when {@link PhotoListMvp.View} is refreshed
         */
        void onRefresh();
    }
}
