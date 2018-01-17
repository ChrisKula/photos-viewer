package com.christiankula.albumviewer.photolist;

import com.christiankula.albumviewer.models.Photo;
import com.christiankula.albumviewer.network.JsonPlaceholderService;
import com.christiankula.albumviewer.photolist.mvp.PhotoListMvp;

import java.util.List;

import javax.inject.Inject;

import retrofit2.Call;


public class PhotoListModel implements PhotoListMvp.Model {

    private JsonPlaceholderService jsonPlaceholderService;

    @Inject
    public PhotoListModel(JsonPlaceholderService service) {
        this.jsonPlaceholderService = service;
    }

    @Override
    public Call<List<Photo>> requestPhotos() {
        return jsonPlaceholderService.getPhotos();
    }
}
