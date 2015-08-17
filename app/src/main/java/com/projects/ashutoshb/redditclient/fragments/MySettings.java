package com.projects.ashutoshb.redditclient.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.projects.ashutoshb.redditclient.R;
import com.projects.ashutoshb.redditclient.models.PostItem;

import java.util.List;


/**
 * Created by ashutosh.b on 8/14/15.
 */
public class MySettings extends Fragment {

    ListView lvPost;
    List<PostItem> listPostItems;

    @Nullable
    @Override

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_settings, container, false);
        return v;
    }
}
