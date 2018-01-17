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

@Module
public class NetworkModule {

    private final static String JSON_PLACEHOLDER_BASE_URL = "jsonPlaceholderBaseUrl";

    @Singleton
    @Provides
    @Named(JSON_PLACEHOLDER_BASE_URL)
    String provideJsonPlaceholderBaseUrl(Context context){
        return context.getString(R.string.json_placeholder_base_url);
    }

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
