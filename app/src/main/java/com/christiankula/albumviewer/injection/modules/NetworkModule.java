package com.christiankula.albumviewer.injection.modules;


import android.content.Context;

import com.christiankula.albumviewer.R;
import com.christiankula.albumviewer.network.JsonPlaceholderService;

import javax.inject.Named;
import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import retrofit2.Retrofit;
import retrofit2.converter.jackson.JacksonConverterFactory;

/**
 * Dagger module in charge of providing network related dependencies
 */
@Module
public class NetworkModule {

    private final static String JSON_PLACEHOLDER_BASE_URL = "jsonPlaceholderBaseUrl";

    /**
     * Provides the base URL of jsonplaceholder.com
     */
    @Singleton
    @Provides
    @Named(JSON_PLACEHOLDER_BASE_URL)
    String provideJsonPlaceholderBaseUrl(Context context){
        return context.getString(R.string.json_placeholder_base_url);
    }

    /**
     * Provides an implementation of the API endpoints defined by {@link JsonPlaceholderService}
     *
     * @param baseUrl jsonplaceholder.com base URL
     */
    @Singleton
    @Provides
    JsonPlaceholderService provideJsonPlaceholderService(@Named(JSON_PLACEHOLDER_BASE_URL) String baseUrl) {
        return new Retrofit.Builder()
                .baseUrl(baseUrl)
                .addConverterFactory(JacksonConverterFactory.create())
                .build()
                .create(JsonPlaceholderService.class);
    }

}
