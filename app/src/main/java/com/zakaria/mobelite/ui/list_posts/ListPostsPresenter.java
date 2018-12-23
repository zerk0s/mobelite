package com.zakaria.mobelite.ui.list_posts;

import android.app.Application;

import com.zakaria.mobelite.R;
import com.zakaria.mobelite.commun.Utils;
import com.zakaria.mobelite.data.DataManager;
import com.zakaria.mobelite.network.NetworkServices;
import com.zakaria.mobelite.ui.list_posts.models.Posts;

import java.util.List;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Retrofit;

/**
 * Created by zakariaazizi on 12/23/18.
 */

public class ListPostsPresenter implements ListPostsContractor.ListPostPresenter{

    private Retrofit mRetrofit;
    private Application mApplication;
    private ListPostsContractor.ListPostsView mListPostsView;
    private CompositeDisposable mCompositeDisposable;


    public ListPostsPresenter(Retrofit mRetrofit, Application mApplication, ListPostsContractor.ListPostsView mListPostsView) {
        this.mRetrofit = mRetrofit;
        this.mApplication = mApplication;
        this.mListPostsView = mListPostsView;
        mCompositeDisposable = new CompositeDisposable();
    }

    public void loadCommits() {
        Disposable disposable = mRetrofit.create(NetworkServices.class).getPosts()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                        new Consumer<List<Posts>>() {
                            @Override
                            public void accept(List<Posts> commits) throws Exception {
                                mListPostsView.connection(false);

                                if (commits.size()>0)
                                {
                                    mListPostsView.empty(false);
                                    mListPostsView.onSucced(commits);
                                }else
                                {
                                    mListPostsView.empty(true);
                                }
                                mListPostsView.progressBar(false);

                                DataManager.getInstance().savePostsResponse(commits);
                            }
                        },
                        new Consumer<Throwable>() {
                            @Override
                            public void accept(Throwable t) throws Exception {
                                mListPostsView.progressBar(false);
                                mListPostsView.onFailled(R.string.msg_error);
                            }
                        }
                );
        mCompositeDisposable.add(disposable);

    }

    @Override
    public void subscribe() {
        mListPostsView.progressBar(true);
        if (Utils.checkNetworkState(mApplication))
        {
            mListPostsView.connection(false);
            loadCommits();
        }else{
            List<Posts> mListTemp = DataManager.getInstance().getPostsResponse();
            mListPostsView.progressBar(false);
            mListPostsView.empty(false);

            if (mListTemp.size()>0) {
                mListPostsView.onSucced(mListTemp);
            }
            else {
                mListPostsView.connection(true);
            }
        }
    }

    @Override
    public void unsubscribe() {
        mCompositeDisposable.clear();
    }


}
