package com.christiankula.albumviewer.models;

import java.util.List;

public class Album {

    private int id;

    private List<Photo> photos;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public List<Photo> getPhotos() {
        return photos;
    }

    public void setPhotos(List<Photo> photos) {
        this.photos = photos;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Album album = (Album) o;

        return id == album.id;
    }

    @Override
    public int hashCode() {
        return id;
    }
}
