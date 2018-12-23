package com.zakaria.mobelite.app;

import android.app.Application;


import com.zakaria.mobelite.BuildConfig;
import com.zakaria.mobelite.network.di.AppModule;
import com.zakaria.mobelite.network.di.DaggerNetComponent;
import com.zakaria.mobelite.network.di.NetComponent;
import com.zakaria.mobelite.network.di.NetModule;

import io.paperdb.Paper;


/**
 * Created by zakariaazizi on 12/23/18.
 */

public class App extends Application {
    private NetComponent mNetComponent;

    public NetComponent getNetComponent() {
        return mNetComponent;
    }


    @Override
    public void onCreate() {
        super.onCreate();

        mNetComponent = DaggerNetComponent.builder()
                .appModule(new AppModule(this))
                .netModule(new NetModule(BuildConfig.BASE_URL))
                .build();

        Paper.init(getApplicationContext());

    }

}
