package com.christiankula.albumviewer;

import android.app.Application;

import com.christiankula.albumviewer.injection.components.AlbumViewerComponent;
import com.christiankula.albumviewer.injection.components.DaggerApplicationComponent;


public class AlbumViewerApplication extends Application {

    private final AlbumViewerComponent component = createComponent();

    /**
     * Builds the application component with all relevant modules in order to set up Dagger for dependency injection
     */
    protected AlbumViewerComponent createComponent() {
        if (component == null) {
            return DaggerApplicationComponent.builder()
                    .build();

        } else {
            throw new IllegalStateException("You can't recreate a component for AlbumViewerApplication");
        }
    }

    public AlbumViewerComponent getComponent() {
        return component;
    }
}
