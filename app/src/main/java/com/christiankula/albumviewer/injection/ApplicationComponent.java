package com.christiankula.albumviewer.injection;

import javax.inject.Singleton;

import dagger.Component;

/**
 * Application-wide Dagger component
 */
@Singleton
//TODO Add future relevant modules here
@Component()
interface ApplicationComponent extends AlbumViewerComponent {

}
