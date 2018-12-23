package com.zakaria.mobelite.ui.list_posts;


import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.zakaria.mobelite.R;
import com.zakaria.mobelite.app.App;
import com.zakaria.mobelite.commun.Constants;
import com.zakaria.mobelite.network.di.ActivityScope;
import com.zakaria.mobelite.ui.detail_post.DetailPostFragment;
import com.zakaria.mobelite.ui.list_posts.adapter.ListPostsAdapter;
import com.zakaria.mobelite.ui.list_posts.di.DaggerListPostsComponent;
import com.zakaria.mobelite.ui.list_posts.di.ListPostsModule;
import com.zakaria.mobelite.ui.list_posts.models.Posts;

import java.util.List;

import javax.inject.Inject;

/**
 * A simple {@link Fragment} subclass.
 */
public class ListPostsFragment extends Fragment implements ListPostsContractor.ListPostsView, View.OnClickListener, ListPostsContractor.OnSelectListPost {

    private ListPostsAdapter mAdapter;
    private RecyclerView mRecyclerView;
    private ImageView mBack;
    private TextView mTitle;
    private Button mCheckConnection;
    private ProgressBar mProgressBar;
    private FrameLayout mFrameEmptyData, mFrameErrorConnection;
    private Activity mActivity;

    private Boolean isConnected = true;

    private FragmentTransaction transaction;
    private Fragment mFragment;

    @Inject
    ListPostsContractor.ListPostPresenter mListPostPresenter;



    public ListPostsFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mActivity = getActivity();

        DaggerListPostsComponent.builder()
                .netComponent(((App)getActivity().getApplication()).getNetComponent())
                .listPostsModule(new ListPostsModule(this))
                .build().inject(this);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_list_posts, container, false);
        init(view);

        mListPostPresenter.subscribe();

        return view;
    }

    void init(View view)
    {
        mFrameEmptyData = view.findViewById(R.id.frame_empty_data);
        mFrameErrorConnection = view.findViewById(R.id.frame_error_connection);

        mBack = view.findViewById(R.id.image_back);
        mBack.setVisibility(View.INVISIBLE);

        mTitle = view.findViewById(R.id.textView_title);
        mTitle.setText("Posts");

        mProgressBar = view.findViewById(R.id.progressBar_list_posts);

        mRecyclerView = view.findViewById(R.id.recyclerView_list_posts);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getActivity().getApplicationContext());
        mRecyclerView.setLayoutManager(mLayoutManager);
        mRecyclerView.addItemDecoration(new DividerItemDecoration(getActivity(), 0));

        mCheckConnection = view.findViewById(R.id.button_check_connection);
        mCheckConnection.setOnClickListener(this);
    }

    @Override
    public void onSucced(List<Posts> mList) {
        mAdapter = new ListPostsAdapter(mActivity, mList, this);
        mRecyclerView.setAdapter(mAdapter);
    }

    @Override
    public void onFailled(int msg) {
        Toast.makeText(mActivity, getResources().getString(msg), Toast.LENGTH_SHORT).show();
    }

    @Override
    public void progressBar(Boolean status) {
        if (status)
            mProgressBar.setVisibility(View.VISIBLE);
        else
            mProgressBar.setVisibility(View.GONE);
    }

    @Override
    public void empty(Boolean status) {
        if (status)
            mFrameEmptyData.setVisibility(View.VISIBLE);
        else
            mFrameEmptyData.setVisibility(View.GONE);
    }

    @Override
    public void connection(Boolean status) {
        isConnected = !status;
        if (!isConnected)
            Toast.makeText(mActivity, getResources().getString(R.string.msg_connection), Toast.LENGTH_SHORT).show();

        if (status)
            mFrameErrorConnection.setVisibility(View.VISIBLE);
        else
            mFrameErrorConnection.setVisibility(View.GONE);

    }

    @Override
    public void onClick(View view) {
        switch (view.getId())
        {
            case R.id.button_check_connection:
                isConnected = false;
                mListPostPresenter.subscribe();
                break;
        }
    }

    @Override
    public void onSelect(Posts mPosts) {

        Bundle mBundle = new Bundle();
        mBundle.putParcelable(Constants.POSTS, mPosts);

        mFragment = new DetailPostFragment();
        mFragment.setArguments(mBundle);

        transaction = getActivity().getSupportFragmentManager().beginTransaction();
        transaction.add(R.id.frame_main, mFragment);
        transaction.addToBackStack("detail");
        transaction.commitAllowingStateLoss();


    }
}
