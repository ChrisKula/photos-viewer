package com.christiankula.albumviewer.injection.modules;

import android.app.Application;
import android.content.Context;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * Dagger module in charge of providing application dependencies (i.e. Context)
 */
@Module
public class ApplicationModule {

    private Application application;

    public ApplicationModule(Application application) {
        this.application = application;
    }

    /**
     * Provides application's Context
     */
    @Provides
    @Singleton
    Context provideContext() {
        return application;
    }
}
