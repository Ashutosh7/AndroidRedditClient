package com.projects.ashutoshb.redditclient.inner.fragments;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.projects.ashutoshb.redditclient.R;
import com.projects.ashutoshb.redditclient.services.QueryAPI;


/**
 * Created by ashutosh.b on 8/13/15.
 */
public class Tab3Fragment extends Fragment {

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.tab3fragment_layout, container, false);
        QueryAPI task = new QueryAPI("/r/askreddit?after=\"\"");
        task.execute();

        return v;
    }
}
