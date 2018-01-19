package com.christiankula.albumviewer.photolist.mvp;


import com.christiankula.albumviewer.models.Photo;
import com.christiankula.albumviewer.mvp.BasePresenter;
import com.christiankula.albumviewer.mvp.BaseView;

import java.util.List;

import io.reactivex.Flowable;
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

        /**
         * Saves the preferred list style
         *
         * @param style to save
         */
        void savePreferredListStyle(int style);

        /**
         * Returns the preferred list style
         */
        int getPreferredListStyle();

        /**
         * Saves the list of photos in the database
         *
         * @param photos photos to save
         */
        void savePhotos(List<Photo> photos);

        /**
         * Returns a Flowable that emits the content of the Photo table whenever it's updated
         */
        Flowable<List<Photo>> loadPhotos();

        /**
         * Sets the model loaded photos
         *
         * @param photos photos that are loaded
         */
        void setLoadedPhotos(List<Photo> photos);

        /**
         * Returns the model's loaded photos
         */
        List<Photo> getLoadedPhotos();
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
        void showUnableToRetrievePhotosOperatingInOfflineModeMessage();

        /**
         * Displays a message indicating there's no photo to display
         */
        void showNoPhotosToDisplayMessage();
    }

    interface Presenter extends BasePresenter<View> {

        /**
         * Called when {@link PhotoListMvp.View} is created
         */
        void onCreate();

        /**
         * Called when {@link PhotoListMvp.View} is refreshed
         */
        void onRefresh();

        /**
         * Returns the preferred list style given by the model
         */
        int getPreferredListStyle();

        /**
         * Called once the photo list is ready
         */
        void onPhotoListReady();

        /**
         * Called when a menu item corresponding to list style is clicked
         *
         * @param itemId the id of the clicked item
         */
        void onMenuItemListStyleClick(int itemId);
    }
}
