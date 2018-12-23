package com.zakaria.mobelite.ui.list_posts.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.zakaria.mobelite.R;

/**
 * Created by zakariaazizi on 12/23/18.
 */

public class ListPostsViewHolder extends RecyclerView.ViewHolder {

    TextView mTitle, mDesc;

    public ListPostsViewHolder(View itemView) {
        super(itemView);

        mTitle = itemView.findViewById(R.id.textView_title_list_posts);
        mDesc = itemView.findViewById(R.id.textView_desc_list_posts);

    }
}
