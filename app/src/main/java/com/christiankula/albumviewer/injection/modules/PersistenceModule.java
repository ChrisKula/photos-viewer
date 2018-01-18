package com.christiankula.albumviewer.injection.modules;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

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
}
