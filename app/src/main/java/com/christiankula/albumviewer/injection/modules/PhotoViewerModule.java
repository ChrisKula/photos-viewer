package com.christiankula.albumviewer.injection.modules;


import com.christiankula.albumviewer.photoviewer.PhotoViewerModel;
import com.christiankula.albumviewer.photoviewer.PhotoViewerMvp;
import com.christiankula.albumviewer.photoviewer.PhotoViewerPresenter;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

@Module
public class PhotoViewerModule {

    @Provides
    @Singleton
    PhotoViewerMvp.Model provideModel() {
        return new PhotoViewerModel();
    }

    @Provides
    @Singleton
    PhotoViewerMvp.Presenter providePresenter(PhotoViewerMvp.Model model) {
        return new PhotoViewerPresenter(model);
    }
}
