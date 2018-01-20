package com.christiankula.albumviewer.injection.modules;

import com.christiankula.albumviewer.network.JsonPlaceholderService;
import com.christiankula.albumviewer.persistence.PhotoDao;
import com.christiankula.albumviewer.persistence.SharedPreferencesHelper;
import com.christiankula.albumviewer.photolist.PhotoListModel;
import com.christiankula.albumviewer.photolist.PhotoListPresenter;
import com.christiankula.albumviewer.photolist.mvp.PhotoListMvp;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * Dagger module in charge of providing dependencies for everything related to the Photo list activity
 */
@Module
public class PhotoListModule {

    /**
     * Provides a {@link PhotoListMvp.Model}
     *
     * @param service REST client
     */
    @Provides
    @Singleton
    PhotoListMvp.Model provideModel(JsonPlaceholderService service, SharedPreferencesHelper sharedPreferencesHelper, PhotoDao photoDao) {
        return new PhotoListModel(service, sharedPreferencesHelper, photoDao);
    }


    /**
     * Provides a {@link PhotoListMvp.Presenter}
     *
     * @param model corresponding {@link PhotoListMvp.Model}
     */
    @Provides
    @Singleton
    PhotoListMvp.Presenter providePresenter(PhotoListMvp.Model model) {
        return new PhotoListPresenter(model);
    }
}
