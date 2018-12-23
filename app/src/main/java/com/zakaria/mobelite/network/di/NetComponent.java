package com.zakaria.mobelite.network.di;

import android.app.Application;
import javax.inject.Singleton;
import dagger.Component;
import retrofit2.Retrofit;

/**
 * Created by zakariaazizi on 12/23/18.
 */
@Singleton
@Component(modules = {AppModule.class, NetModule.class})
public interface NetComponent {

    Retrofit retrofit();
    Application application();
}
