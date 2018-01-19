package com.christiankula.albumviewer;

import android.app.Application;

import com.christiankula.albumviewer.injection.components.AlbumViewerComponent;
import com.christiankula.albumviewer.injection.components.DaggerApplicationComponent;
import com.christiankula.albumviewer.injection.modules.ApplicationModule;
import com.christiankula.albumviewer.injection.modules.NetworkModule;
import com.christiankula.albumviewer.injection.modules.PersistenceModule;
import com.christiankula.albumviewer.injection.modules.PhotoListModule;


public class AlbumViewerApplication extends Application {

    private final AlbumViewerComponent component = createComponent();

    /**
     * Builds the application component with all relevant modules in order to set up Dagger for dependency injection
     */
    protected AlbumViewerComponent createComponent() {
        if (component == null) {
            return DaggerApplicationComponent.builder()
                    .applicationModule(new ApplicationModule(this))
                    .networkModule(new NetworkModule())
                    .photoListModule(new PhotoListModule())
                    .persistenceModule(new PersistenceModule())
                    .build();

        } else {
            throw new IllegalStateException("You can't recreate a component for AlbumViewerApplication");
        }
    }

    public AlbumViewerComponent getComponent() {
        return component;
    }
}
