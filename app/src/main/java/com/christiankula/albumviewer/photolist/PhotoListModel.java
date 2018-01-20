package com.christiankula.albumviewer.photolist;

import com.christiankula.albumviewer.models.Photo;
import com.christiankula.albumviewer.network.JsonPlaceholderService;
import com.christiankula.albumviewer.persistence.PhotoDao;
import com.christiankula.albumviewer.persistence.SharedPreferencesHelper;
import com.christiankula.albumviewer.photolist.mvp.PhotoListMvp;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.Flowable;
import retrofit2.Call;


public class PhotoListModel implements PhotoListMvp.Model {

    private JsonPlaceholderService jsonPlaceholderService;
    private SharedPreferencesHelper sharedPreferencesHelper;
    private PhotoDao photoDao;

    private List<Photo> loadedPhotos;

    private long lastRefreshTimestamp = -1;

    @Inject
    public PhotoListModel(JsonPlaceholderService service, SharedPreferencesHelper sharedPreferencesHelper, PhotoDao photoDao) {
        this.jsonPlaceholderService = service;
        this.sharedPreferencesHelper = sharedPreferencesHelper;
        this.photoDao = photoDao;
    }

    @Override
    public Call<List<Photo>> requestPhotos() {
        return jsonPlaceholderService.getPhotos();
    }

    @Override
    public void savePreferredListStyle(int style) {
        sharedPreferencesHelper.savePreferredListStyle(style);
    }

    @Override
    public int getPreferredListStyle() {
        return sharedPreferencesHelper.getPreferredListStyle();
    }

    @Override
    public void savePhotos(final List<Photo> photos) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                photoDao.insertPhotos(photos.toArray(new Photo[photos.size()]));
            }
        }).start();
    }

    @Override
    public Flowable<List<Photo>> loadPhotos() {
        return photoDao.loadPhotos();
    }

    @Override
    public List<Photo> getLoadedPhotos() {
        return loadedPhotos;
    }

    @Override
    public void setLoadedPhotos(List<Photo> photos) {
        this.loadedPhotos = photos;
    }

    @Override
    public void setLastRefreshTimestamp(long timestamp) {
        this.lastRefreshTimestamp = timestamp;
    }

    @Override
    public long getLastRefreshTimestamp() {
        return this.lastRefreshTimestamp;
    }
}
