package com.zakaria.mobelite.network;



import com.zakaria.mobelite.ui.list_posts.models.Posts;

import java.util.List;

import retrofit2.http.GET;

/**
 * Created by zakariaazizi on 12/23/18.
 */
public interface NetworkServices {


    @GET("posts")
    io.reactivex.Observable<List<Posts>> getPosts();


}
