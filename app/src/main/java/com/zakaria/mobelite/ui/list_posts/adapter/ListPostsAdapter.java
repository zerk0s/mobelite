package com.zakaria.mobelite.ui.list_posts.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.zakaria.mobelite.R;
import com.zakaria.mobelite.ui.list_posts.ListPostsContractor;
import com.zakaria.mobelite.ui.list_posts.models.Posts;

import java.util.List;

/**
 * Created by zakariaazizi on 12/23/18.
 */

public class ListPostsAdapter extends RecyclerView.Adapter<ListPostsViewHolder> {

    private Context mContext;
    private List<Posts> mList;
    private ListPostsContractor.OnSelectListPost mListner;

    public ListPostsAdapter(Context mContext, List<Posts> mList, ListPostsContractor.OnSelectListPost mListner) {
        this.mContext = mContext;
        this.mList = mList;
        this.mListner = mListner;
    }

    @Override
    public ListPostsViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ListPostsViewHolder(LayoutInflater.from(mContext).inflate(R.layout.item_list_posts, parent, false));
    }

    @Override
    public void onBindViewHolder(ListPostsViewHolder holder, final int position) {
        holder.mTitle.setText(mList.get(position).getTitle());
        holder.mDesc.setText(mList.get(position).getBody());
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mListner.onSelect(mList.get(position));
            }
        });
    }

    @Override
    public int getItemCount() {
        return mList.size();
    }
}
