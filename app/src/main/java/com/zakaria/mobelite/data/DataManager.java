package com.zakaria.mobelite.data;

import com.zakaria.mobelite.commun.Constants;
import com.zakaria.mobelite.ui.list_posts.models.Posts;

import java.util.ArrayList;
import java.util.List;

import io.paperdb.Paper;

/**
 * Created by zakariaazizi on 06/11/2018.
 */

public class DataManager {

    private static DataManager mDataManager;

    private DataManager(){
    }

    public static DataManager getInstance(){
        if(mDataManager == null)
        {
            mDataManager = new DataManager();
        }
        return mDataManager;
    }

    public void savePostsResponse(List<Posts> mList)
    {
        Paper.book().write(Constants.POSTSRESPONSE_DATA,mList);
    }
    public List<Posts> getPostsResponse()
    {
        List<Posts> mData = new ArrayList<>();
        if (Paper.book().exist(Constants.POSTSRESPONSE_DATA)) {
            mData = Paper.book().read(Constants.POSTSRESPONSE_DATA);
        }
        return mData;
    }
}
