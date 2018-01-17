package com.christiankula.albumviewer.injection.modules;

import com.christiankula.albumviewer.network.JsonPlaceholderService;
import com.christiankula.albumviewer.photolist.PhotoListModel;
import com.christiankula.albumviewer.photolist.PhotoListPresenter;
import com.christiankula.albumviewer.photolist.mvp.PhotoListMvp;

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
    PhotoListMvp.Model provideModel(JsonPlaceholderService service) {
        return new PhotoListModel(service);
    }


    /**
     * Provides a {@link PhotoListMvp.Presenter}
     *
     * @param model corresponding {@link PhotoListMvp.Model}
     */
    @Provides
    PhotoListMvp.Presenter providePresenter(PhotoListMvp.Model model) {
        return new PhotoListPresenter(model);
    }
}
