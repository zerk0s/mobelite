package com.zakaria.mobelite.ui.list_posts;

import com.zakaria.mobelite.ui.list_posts.models.Posts;

import java.util.List;

/**
 * Created by zakariaazizi on 12/23/18.
 */

public interface ListPostsContractor {



    interface ListPostsView
    {
        void onSucced(List<Posts> mList);
        void onFailled(int msg);
        void progressBar(Boolean status);
        void empty(Boolean status);
        void connection(Boolean status);

    }

    interface ListPostPresenter
    {
        void subscribe();
        void unsubscribe();
    }

    interface OnSelectListPost
    {
        void onSelect(Posts mPosts);

    }




}
