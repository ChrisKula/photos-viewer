package com.christiankula.albumviewer.persistence;

import android.content.SharedPreferences;

import com.christiankula.albumviewer.photolist.PhotoAdapter;

import javax.inject.Inject;

/**
 * Helper class to get from and save to the default Shared Preferences
 */
public class SharedPreferencesHelper {

    private final String PREFERRED_LIST_STYLE_KEY = "PREFERRED_LIST_STYLE";

    private SharedPreferences sharedPreferences;

    @Inject
    public SharedPreferencesHelper(SharedPreferences sharedPreferences) {
        this.sharedPreferences = sharedPreferences;
    }

    /**
     * Save the given listStyle in the default Shared preferences
     *
     * @param listStyle to save
     */
    public void savePreferredListStyle(int listStyle) {
        sharedPreferences.edit().putInt(PREFERRED_LIST_STYLE_KEY, listStyle).apply();
    }

    /**
     * Returns the saved list style from the default Shared Preferences
     */
    public int getPreferredListStyle() {
        return sharedPreferences.getInt(PREFERRED_LIST_STYLE_KEY, PhotoAdapter.STYLE_GRID);
    }
}
