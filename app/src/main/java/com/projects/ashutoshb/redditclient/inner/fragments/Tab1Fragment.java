package com.projects.ashutoshb.redditclient.inner.fragments;


import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.ListFragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.projects.ashutoshb.redditclient.R;
import com.projects.ashutoshb.redditclient.activities.MainActivity;
import com.projects.ashutoshb.redditclient.adapters.PostListAdapter;
import com.projects.ashutoshb.redditclient.models.PostItem;
import com.projects.ashutoshb.redditclient.services.AsyncDelegate;
import com.projects.ashutoshb.redditclient.services.GetFromAPI;
import com.projects.ashutoshb.redditclient.services.QueryAPI;

import java.util.ArrayList;
import java.util.List;


/**
 * Created by ashutosh.b on 8/13/15.
 */
public class Tab1Fragment extends ListFragment implements AsyncDelegate{
    ListView lvPost;
    List<PostItem> listPostItems;
    PostListAdapter postListAdapter;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.tab1fragment_layout, container, false);

        listPostItems = new ArrayList<PostItem>();

        GetFromAPI req = new GetFromAPI("/r/askreddit?after=\"\"", this);
        req.execute();

       // QueryAPI req = new QueryAPI("/r/askreddit?after=\"\"");
        //req.execute();
        return v;
    }

    public void asyncComplete(boolean x, List<PostItem> tp){
        Log.i("wtf","here");
        listPostItems = tp;
        Log.i("size", String.valueOf(listPostItems.size()));
        postListAdapter = new PostListAdapter(getActivity().getApplicationContext(),
                R.layout.item_post,listPostItems);
        setListAdapter(postListAdapter);
    }

}
