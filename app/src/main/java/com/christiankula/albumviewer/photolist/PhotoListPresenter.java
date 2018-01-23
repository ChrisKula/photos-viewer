package com.christiankula.albumviewer.photolist;

import android.support.annotation.NonNull;
import android.util.Log;

import com.christiankula.albumviewer.R;
import com.christiankula.albumviewer.models.Album;
import com.christiankula.albumviewer.models.Photo;
import com.christiankula.albumviewer.photolist.mvp.PhotoListMvp;
import com.christiankula.albumviewer.utils.AlbumUtils;

import java.util.List;
import java.util.concurrent.TimeUnit;

import javax.inject.Inject;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class PhotoListPresenter implements PhotoListMvp.Presenter, Callback<List<Photo>> {

    private static final String TAG = PhotoListPresenter.class.getSimpleName();

    private static final long INTERVAL_BETWEEN_TWO_UPDATES = TimeUnit.MINUTES.toMillis(10);

    private PhotoListMvp.View view;

    private PhotoListMvp.Model model;

    private Call<List<Photo>> getPhotosCall;

    private Disposable loadPhotosDisposable;

    @Inject
    public PhotoListPresenter(PhotoListMvp.Model model) {
        this.model = model;
    }

    @Override
    public void attachView(PhotoListMvp.View view) {
        this.view = view;

        loadPhotosDisposable = model.loadPhotos()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<List<Photo>>() {
                    @Override
                    public void accept(List<Photo> photos) throws Exception {
                        model.setLoadedPhotos(photos);
                        displayPhotos(photos);
                    }
                });
    }

    @Override
    public void detachView() {
        if (loadPhotosDisposable != null && !loadPhotosDisposable.isDisposed()) {
            loadPhotosDisposable.dispose();
        }

        if (!getPhotosCall.isExecuted()) {
            this.getPhotosCall.cancel();
        }

        this.view = null;
    }

    @Override
    public void onCreate() {
        if (System.currentTimeMillis() > model.getLastRefreshTimestamp() + INTERVAL_BETWEEN_TWO_UPDATES) {
            refreshPhotos();
        }
    }

    @Override
    public void onRefresh() {
        refreshPhotos();
    }

    @Override
    public int getPreferredListStyle() {
        return model.getPreferredListStyle();
    }

    @Override
    public void onPhotoListReady() {
        displayPhotos(model.getLoadedPhotos());
    }

    @Override
    public void onMenuItemListStyleClick(int itemId) {
        switch (itemId) {
            case R.id.menu_item_list_style_list:
                model.savePreferredListStyle(PhotoListMvp.Model.STYLE_LIST);
                break;

            case R.id.menu_item_list_style_album:
                model.savePreferredListStyle(PhotoListMvp.Model.STYLE_ALBUM);
                break;

            case R.id.menu_item_list_style_grid:
            default:
                model.savePreferredListStyle(PhotoListMvp.Model.STYLE_GRID);
                break;
        }
    }

    @Override
    public void onItemClick(Album album) {
        view.startPhotoViewerActivity(album.getPhotos());
    }

    @Override
    public void onItemClick(Photo photo) {
        view.startPhotoViewerActivity(photo.getId());
    }

    @Override
    public void onResponse(@NonNull Call<List<Photo>> call, @NonNull Response<List<Photo>> response) {
        List<Photo> photos = response.body();

        if (response.isSuccessful() && photos != null) {
            model.setLastRefreshTimestamp(System.currentTimeMillis());
            model.savePhotos(photos);
        } else {
            view.showUnableToRetrievePhotosOperatingInOfflineModeMessage();
        }

        this.view.setRefreshing(false);
    }

    @Override
    public void onFailure(@NonNull Call<List<Photo>> call, @NonNull Throwable t) {
        Log.e(TAG, "GET request for photos failed", t);

        view.showUnableToRetrievePhotosOperatingInOfflineModeMessage();

        this.view.setRefreshing(false);
    }

    private void refreshPhotos() {
        this.getPhotosCall = model.requestPhotos();

        this.getPhotosCall.enqueue(this);

        this.view.setRefreshing(true);
    }

    private void displayPhotos(List<Photo> photos) {
        if (photos != null && !photos.isEmpty()) {
            if (model.getPreferredListStyle() == PhotoListMvp.Model.STYLE_ALBUM) {
                this.view.displayAlbums(AlbumUtils.toAlbumList(photos));
            } else {
                this.view.displayPhotos(photos);
            }
        } else {
            view.showNoPhotosToDisplayMessage();
        }
    }
}
