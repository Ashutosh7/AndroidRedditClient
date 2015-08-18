package com.projects.ashutoshb.redditclient.inner.fragments;


import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.ListFragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.ListView;
import android.widget.Toast;
import android.widget.AbsListView.OnScrollListener;

import com.projects.ashutoshb.redditclient.R;
import com.projects.ashutoshb.redditclient.activities.MainActivity;
import com.projects.ashutoshb.redditclient.adapters.PostListAdapter;
import com.projects.ashutoshb.redditclient.models.PostItem;
import com.projects.ashutoshb.redditclient.models.PostsPage;
import com.projects.ashutoshb.redditclient.services.AsyncDelegate;
import com.projects.ashutoshb.redditclient.services.GetFromAPI;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;


/**
 * Created by ashutosh.b on 8/13/15.
 */
public class Tab1Fragment extends ListFragment implements AsyncDelegate{

    ListView lvPost;
    private String _path;
    private String _subReddit;
    private String _category;
    List<PostItem> listPostItems;
    PostListAdapter postListAdapter;
    PostsPage parentPostsPage;
    boolean loadingMore = false;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.tab1fragment_layout, container, false);

        listPostItems = new ArrayList<PostItem>();
        _subReddit = getArguments().getString("subReddit");
        _category = getArguments().getString("category");
        _path = getPath(_subReddit, _category, "");
        Log.i("OnCreateviewFor", _path);

        loadingMore = true;
        makeGetRequest(_path);
        return v;
    }


    @Override
    public void onViewCreated(View view, Bundle savedInstanceState){


        this.getListView().setOnScrollListener(new OnScrollListener() {
            @Override
            public void onScrollStateChanged(AbsListView view, int scrollState) {
            }

            @Override
            public void onScroll(AbsListView view, int firstVisibleItem,
                                 int visibleItemCount, int totalItemCount) {
                int lastInScreen = firstVisibleItem + visibleItemCount;
                if ((lastInScreen == totalItemCount) && !(loadingMore)) {
                    loadingMore = true;
                    String path = getPath(_subReddit,_category,parentPostsPage.get_after());
                    makeGetRequest(path);
                    Toast.makeText(getActivity(), "Loading more posts...",
                            Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    public void makeGetRequest(String path){

        GetFromAPI req = new GetFromAPI(path, this);
        req.execute();
        return;

    }

    public void asyncComplete(boolean x, PostsPage postspage){
        Log.i("wtf", "here");
        parentPostsPage = postspage;
        listPostItems.addAll(postspage.get_postItemList());
        Log.i("OnCreateviewFor", _path);
        Log.i("size", String.valueOf(listPostItems.size()));
        if(postListAdapter!=null)
            postListAdapter.notifyDataSetChanged();
        else {
            postListAdapter = new PostListAdapter(getActivity().getApplicationContext(),
                    R.layout.item_post, listPostItems);
            setListAdapter(postListAdapter);
        }
        loadingMore = false;
    }

    private String getPath(String subReddit, String category, String after) {

        return "/r/" + subReddit + "/" + category + "?after=" + after + "&limit=10";

    }

}
