package com.christiankula.albumviewer.injection.modules;

import android.arch.persistence.room.Room;
import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

import com.christiankula.albumviewer.persistence.AlbumViewerDatabase;
import com.christiankula.albumviewer.persistence.PhotoDao;
import com.christiankula.albumviewer.persistence.SharedPreferencesHelper;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * Dagger module in charge of providing persistence related dependencies
 */
@Module
public class PersistenceModule {

    /**
     * Provides the default Shared Preferences
     */
    @Singleton
    @Provides
    SharedPreferences provideDefaultSharedPreferences(Context context) {
        return PreferenceManager.getDefaultSharedPreferences(context);
    }

    /**
     * Provides a singleton instance of {@link SharedPreferencesHelper}
     *
     * @param sharedPreferences the default Shared Preferences
     */
    @Provides
    @Singleton
    SharedPreferencesHelper provideSharedPreferencesHelper(SharedPreferences sharedPreferences) {
        return new SharedPreferencesHelper(sharedPreferences);
    }

    /**
     * Provides a singleton instance of {@link AlbumViewerDatabase}
     *
     * @param context the application context
     */
    @Provides
    @Singleton
    AlbumViewerDatabase provideApplicationDatabase(Context context) {
        return Room.databaseBuilder(context, AlbumViewerDatabase.class, AlbumViewerDatabase.NAME).build();
    }

    /**
     * Provides a singleton instance of {@link PhotoDao}
     *
     * @param albumViewerDb the application's database
     */
    @Provides
    @Singleton
    PhotoDao providePhotoDao(AlbumViewerDatabase albumViewerDb) {
        return albumViewerDb.photoDao();
    }
}
