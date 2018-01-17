package com.christiankula.albumviewer;

import android.app.Application;

import com.christiankula.albumviewer.injection.AlbumViewerComponent;
import com.christiankula.albumviewer.injection.DaggerApplicationComponent;


public class AlbumViewerApplication extends Application {

    private final AlbumViewerComponent component = createComponent();

    /**
     * Builds the application component with all relevant modules in order to set up Dagger for dependency injection
     */
    protected AlbumViewerComponent createComponent() {
        return DaggerApplicationComponent.builder()
                .build();
    }

}
