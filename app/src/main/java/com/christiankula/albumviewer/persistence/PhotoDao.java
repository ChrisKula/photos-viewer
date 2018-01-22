package com.christiankula.albumviewer.persistence;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;

import com.christiankula.albumviewer.models.Photo;

import java.util.List;

import io.reactivex.Flowable;

@Dao
public interface PhotoDao {

    @Query("SELECT * FROM photos")
    Flowable<List<Photo>> loadPhotos();

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertPhotos(Photo... photos);

}
