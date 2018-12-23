package com.zakaria.mobelite.ui.detail_post;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.zakaria.mobelite.R;
import com.zakaria.mobelite.commun.Constants;
import com.zakaria.mobelite.ui.list_posts.models.Posts;

/**
 * A simple {@link Fragment} subclass.
 */
public class DetailPostFragment extends Fragment implements View.OnClickListener{

    private TextView mUserId, mPostId, mTitlePost, mBodyPost;
    private TextView mTitle;
    private ImageView mBack;

    private Posts mPost;

    public DetailPostFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_detail_post, container, false);
        mPost = getArguments().getParcelable(Constants.POSTS);

        init(view);

        return view;
    }

    void init(View view) {

        mTitle = view.findViewById(R.id.textView_title);
        mTitle.setText("Details Post");

        mBack = view.findViewById(R.id.image_back);
        mBack.setOnClickListener(this);

        mUserId = view.findViewById(R.id.textView_user_detail);
        mPostId = view.findViewById(R.id.textView_post_detail);
        mTitlePost = view.findViewById(R.id.textView_title_detail);
        mBodyPost = view.findViewById(R.id.textView_body_detail);


        mUserId.setText(mPost.getUserId().toString());
        mPostId.setText(mPost.getId().toString());
        mTitlePost.setText(mPost.getTitle());
        mBodyPost.setText(mPost.getBody());

    }

    @Override
    public void onClick(View view) {
        switch (view.getId())
        {
            case R.id.image_back:
                getActivity().onBackPressed();
                break;
        }
    }
}
