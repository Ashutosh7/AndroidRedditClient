package com.projects.ashutoshb.redditclient.inner.fragments;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.ListFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.projects.ashutoshb.redditclient.R;
import com.projects.ashutoshb.redditclient.adapters.PostListAdapter;
import com.projects.ashutoshb.redditclient.models.PostItem;
import com.projects.ashutoshb.redditclient.services.QueryAPI;

import java.util.ArrayList;
import java.util.List;


/**
 * Created by ashutosh.b on 8/13/15.
 */
public class Tab1Fragment extends ListFragment {
    ListView lvPost;
    List<PostItem> listPostItems;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.tab1fragment_layout, container, false);
        QueryAPI task = new QueryAPI("/r/askreddit?after=\"\"");
        task.execute();
        listPostItems = new ArrayList<PostItem>();
        populatePostList();
        return v;
    }

    private void populatePostList() {
        listPostItems.add(new PostItem("domain", "title", false, null, 10000, "abc", "blah", null, "Title", null, 123456, "phew", "funny" ));
        listPostItems.add(new PostItem("domain", "title", false, null, 10000, "abc", "blah", null, "Title", null, 123456, "phew", "funny" ));
        listPostItems.add(new PostItem("domain", "title", false, null, 10000, "abc", "blah", null, "Title", null, 123456, "phew", "funny" ));
        listPostItems.add(new PostItem("domain", "title", false, null, 10000, "abc", "blah", null, "Title", null, 123456, "phew", "funny" ));
        listPostItems.add(new PostItem("domain", "title", false, null, 10000, "abc", "blah", null, "Title", null, 123456, "phew", "funny" ));
        listPostItems.add(new PostItem("domain", "title", false, null, 10000, "abc", "blah", null, "Title", null, 123456, "phew", "funny" ));
        listPostItems.add(new PostItem("domain", "title", false, null, 10000, "abc", "blah", null, "Title", null, 123456, "phew", "funny" ));
        listPostItems.add(new PostItem("domain", "title", false, null, 10000, "abc", "blah", null, "Title", null, 123456, "phew", "funny"));
        listPostItems.add(new PostItem("domain", "title", false, null, 10000, "abc", "blah", null, "Title", null, 123456, "phew", "funny"));
        listPostItems.add(new PostItem("domain", "title", false, null, 10000, "abc", "blah", null, "Title", null, 123456, "phew", "funny" ));

        PostListAdapter postListAdapter = new PostListAdapter(getActivity().getApplicationContext(),
                R.layout.item_post,listPostItems);
        setListAdapter(postListAdapter);
    }
}
