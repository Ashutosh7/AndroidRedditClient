package com.projects.ashutoshb.redditclient.services;

import com.projects.ashutoshb.redditclient.models.PostItem;

import java.util.List;

/**
 * Created by ashutosh.b on 8/17/15.
 */
public interface AsyncDelegate {

    public void asyncComplete(boolean success, List<PostItem> result);


}
