package com.christiankula.albumviewer.photolist;

import com.christiankula.albumviewer.models.Photo;
import com.christiankula.albumviewer.network.JsonPlaceholderService;
import com.christiankula.albumviewer.photolist.mvp.PhotoListMvp;

import java.util.List;

import javax.inject.Inject;

import retrofit2.Call;


public class PhotoListModel implements PhotoListMvp.Model {

    private int preferredListStyle;

    private JsonPlaceholderService jsonPlaceholderService;

    @Inject
    public PhotoListModel(JsonPlaceholderService service) {
        this.jsonPlaceholderService = service;
    }

    @Override
    public Call<List<Photo>> requestPhotos() {
        return jsonPlaceholderService.getPhotos();
    }

    @Override
    public void savePreferredListStyle(int style) {
        preferredListStyle = style;
    }

    @Override
    public int getPreferredListStyle(){
        return preferredListStyle;
    }
}
