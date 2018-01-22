package com.christiankula.albumviewer.injection.components;

import com.christiankula.albumviewer.injection.modules.ApplicationModule;
import com.christiankula.albumviewer.injection.modules.NetworkModule;
import com.christiankula.albumviewer.injection.modules.PersistenceModule;
import com.christiankula.albumviewer.injection.modules.PhotoListModule;
import com.christiankula.albumviewer.injection.modules.PhotoViewerModule;

import javax.inject.Singleton;

import dagger.Component;

/**
 * Application-wide Dagger component
 */
@Singleton
@Component(modules = {ApplicationModule.class, PhotoListModule.class, PhotoViewerModule.class, NetworkModule.class,
        PersistenceModule.class})
interface ApplicationComponent extends AlbumViewerComponent {

}
