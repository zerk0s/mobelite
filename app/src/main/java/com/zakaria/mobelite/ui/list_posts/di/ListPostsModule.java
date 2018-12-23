package com.zakaria.mobelite.ui.list_posts.di;

import android.app.Application;

import com.zakaria.mobelite.network.di.ActivityScope;
import com.zakaria.mobelite.ui.list_posts.ListPostsContractor;
import com.zakaria.mobelite.ui.list_posts.ListPostsPresenter;

import dagger.Module;
import dagger.Provides;
import retrofit2.Retrofit;

/**
 * Created by zakariaazizi on 12/23/18.
 */
@Module
public class ListPostsModule {

    ListPostsContractor.ListPostsView mListPostsView;

    public ListPostsModule(ListPostsContractor.ListPostsView mListPostsView) {
        this.mListPostsView = mListPostsView;
    }

    @ActivityScope
    @Provides
    ListPostsContractor.ListPostsView getmListPostsView() {
        return mListPostsView;
    }

    @ActivityScope
    @Provides
    ListPostsContractor.ListPostPresenter mListPostPresenter(Retrofit mRetrofit, Application mApplication, ListPostsContractor.ListPostsView mListPostsView)
    {
        return new ListPostsPresenter(mRetrofit,mApplication,mListPostsView);
    }
}
