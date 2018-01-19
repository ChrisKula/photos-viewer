package com.christiankula.albumviewer.persistence;


import android.arch.persistence.room.Database;
import android.arch.persistence.room.RoomDatabase;

import com.christiankula.albumviewer.models.Photo;

@Database(entities = {Photo.class}, version = 1)
public abstract class AlbumViewerDatabase extends RoomDatabase {

    public final static String NAME = "application_database";

    public abstract PhotoDao photoDao();
}
