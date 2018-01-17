package com.christiankula.albumviewer.photolist;

import android.support.annotation.NonNull;
import android.util.Log;

import com.christiankula.albumviewer.models.Photo;
import com.christiankula.albumviewer.photolist.mvp.PhotoListMvp;

import java.util.List;

import javax.inject.Inject;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class PhotoListPresenter implements PhotoListMvp.Presenter, Callback<List<Photo>> {

    private static final String TAG = PhotoListPresenter.class.getSimpleName();

    private PhotoListMvp.View view;

    private PhotoListMvp.Model model;

    private Call<List<Photo>> getPhotosCall;

    @Inject
    public PhotoListPresenter(PhotoListMvp.Model model) {
        this.model = model;
    }

    @Override
    public void attachView(PhotoListMvp.View view) {
        this.view = view;
    }

    @Override
    public void detachView() {
        if (!getPhotosCall.isExecuted()) {
            this.getPhotosCall.cancel();
        }

        this.view = null;
    }

    @Override
    public void onCreate() {
        refreshPhotos();
    }

    @Override
    public void onRefresh() {
        refreshPhotos();
    }


    private void refreshPhotos() {
        this.getPhotosCall = model.requestPhotos();

        this.getPhotosCall.enqueue(this);

        this.view.setRefreshing(true);
    }

    @Override
    public void onResponse(@NonNull Call<List<Photo>> call, @NonNull Response<List<Photo>> response) {
        List<Photo> photos = response.body();

        if (response.isSuccessful() && photos != null) {
            if (photos.isEmpty()) {
                Log.w(TAG, "Response body is empty");

                view.showNoPhotosToDisplayMessage();
            } else {
                view.displayPhotos(photos);
            }
        } else {
            view.showUnableToRetrievePhotosMessage();
        }

        this.view.setRefreshing(false);
    }

    @Override
    public void onFailure(@NonNull Call<List<Photo>> call, @NonNull Throwable t) {
        Log.e(TAG, "GET request for photos failed", t);

        view.showUnableToRetrievePhotosMessage();

        this.view.setRefreshing(false);
    }
}
