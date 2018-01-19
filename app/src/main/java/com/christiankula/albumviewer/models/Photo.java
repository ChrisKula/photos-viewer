
package com.christiankula.albumviewer.models;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

@Entity(tableName = "photos")
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Photo {

    @ColumnInfo(name = "album_id")
    @JsonProperty("albumId")
    private int albumId;

    @PrimaryKey
    @ColumnInfo(name = "id")
    @JsonProperty("id")
    private int id;

    @ColumnInfo(name = "title")
    @JsonProperty("title")
    private String title;

    @ColumnInfo(name = "url")
    @JsonProperty("url")
    private String url;

    @ColumnInfo(name = "thumbnail_url")
    @JsonProperty("thumbnailUrl")
    private String thumbnailUrl;

    @JsonProperty("albumId")
    public int getAlbumId() {
        return albumId;
    }

    @JsonProperty("albumId")
    public void setAlbumId(int albumId) {
        this.albumId = albumId;
    }

    @JsonProperty("id")
    public int getId() {
        return id;
    }

    @JsonProperty("id")
    public void setId(int id) {
        this.id = id;
    }

    @JsonProperty("title")
    public String getTitle() {
        return title;
    }

    @JsonProperty("title")
    public void setTitle(String title) {
        this.title = title;
    }

    @JsonProperty("url")
    public String getUrl() {
        return url;
    }

    @JsonProperty("url")
    public void setUrl(String url) {
        this.url = url;
    }

    @JsonProperty("thumbnailUrl")
    public String getThumbnailUrl() {
        return thumbnailUrl;
    }

    @JsonProperty("thumbnailUrl")
    public void setThumbnailUrl(String thumbnailUrl) {
        this.thumbnailUrl = thumbnailUrl;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Photo photo = (Photo) o;

        return id == photo.id;
    }

    @Override
    public int hashCode() {
        return id;
    }
}
