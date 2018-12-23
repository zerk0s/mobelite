package com.zakaria.mobelite.ui.main;

import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.zakaria.mobelite.R;
import com.zakaria.mobelite.ui.list_posts.ListPostsFragment;

public class MainActivity extends AppCompatActivity {

    private FragmentTransaction transaction;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        loadFragmentList();
    }

    void loadFragmentList()
    {

        transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.frame_main, new ListPostsFragment());
        transaction.commitAllowingStateLoss();
    }
}
