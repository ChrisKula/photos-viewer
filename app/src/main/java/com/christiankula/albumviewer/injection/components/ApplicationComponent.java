package com.christiankula.albumviewer.injection.components;

import com.christiankula.albumviewer.injection.modules.ApplicationModule;
import com.christiankula.albumviewer.injection.modules.NetworkModule;

import javax.inject.Singleton;

import dagger.Component;

/**
 * Application-wide Dagger component
 */
@Singleton
@Component(modules = {ApplicationModule.class, NetworkModule.class})
interface ApplicationComponent extends AlbumViewerComponent {

}
