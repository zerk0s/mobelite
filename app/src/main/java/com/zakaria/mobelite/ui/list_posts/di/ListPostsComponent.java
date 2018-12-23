package com.zakaria.mobelite.ui.list_posts.di;

import com.zakaria.mobelite.network.di.ActivityScope;
import com.zakaria.mobelite.network.di.NetComponent;
import com.zakaria.mobelite.ui.list_posts.ListPostsFragment;

import dagger.Component;

/**
 * Created by zakariaazizi on 12/23/18.
 */
@ActivityScope
@Component(dependencies = {NetComponent.class}, modules = {ListPostsModule.class})
public interface ListPostsComponent {
    void inject(ListPostsFragment fragment);
}
