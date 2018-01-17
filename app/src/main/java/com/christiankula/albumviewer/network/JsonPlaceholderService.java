package com.christiankula.albumviewer.network;

import com.christiankula.albumviewer.models.Photo;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

/**
 * Service interface defining JsonPlaceholder's API endpoints
 */
public interface JsonPlaceholderService {

    /**
     * Returns a {@link Call} of list of {@link Photo} returned by jsonplaceholder.typicode.com
     */
    @GET("photos")
    Call<List<Photo>> getPhotos();

}
