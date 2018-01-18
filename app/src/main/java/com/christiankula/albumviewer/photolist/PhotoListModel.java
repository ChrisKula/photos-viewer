package com.christiankula.albumviewer.photolist;

import com.christiankula.albumviewer.models.Photo;
import com.christiankula.albumviewer.network.JsonPlaceholderService;
import com.christiankula.albumviewer.persistence.SharedPreferencesHelper;
import com.christiankula.albumviewer.photolist.mvp.PhotoListMvp;

import java.util.List;

import javax.inject.Inject;

import retrofit2.Call;


public class PhotoListModel implements PhotoListMvp.Model {

    private JsonPlaceholderService jsonPlaceholderService;

    private SharedPreferencesHelper sharedPreferencesHelper;

    @Inject
    public PhotoListModel(JsonPlaceholderService service, SharedPreferencesHelper sharedPreferencesHelper) {
        this.jsonPlaceholderService = service;
        this.sharedPreferencesHelper = sharedPreferencesHelper;
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
}
