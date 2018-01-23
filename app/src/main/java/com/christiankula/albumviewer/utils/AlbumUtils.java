package com.christiankula.albumviewer.utils;

import android.util.SparseArray;

import com.christiankula.albumviewer.models.Album;
import com.christiankula.albumviewer.models.Photo;

import java.util.ArrayList;
import java.util.List;

public class AlbumUtils {

    private AlbumUtils() {

    }

    public static List<Album> toAlbumList(List<Photo> photos) {
        SparseArray<Album> sa = new SparseArray<>();

        for (Photo photo : photos) {
            Album album = sa.get(photo.getAlbumId());

            if (album == null) {
                album = new Album();
                album.setId(photo.getAlbumId());
            }

            List<Photo> pList = album.getPhotos();

            if (pList == null) {
                pList = new ArrayList<>();
                album.setPhotos(pList);
            }

            pList.add(photo);

            sa.put(album.getId(), album);
        }

        return ListUtils.asList(sa);
    }
}
