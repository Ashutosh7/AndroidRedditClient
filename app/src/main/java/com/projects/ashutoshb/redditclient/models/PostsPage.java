package com.projects.ashutoshb.redditclient.models;

import java.util.List;

/**
 * Created by ashutosh.b on 8/18/15.
 */
public class PostsPage {

    private List<PostItem> _postItemList;
    private String _after;
    private String _before;

    public boolean get_loadingMore() {
        return _loadingMore;
    }

    public void set_loadingMore(boolean _loadingMore) {
        this._loadingMore = _loadingMore;
    }

    private boolean _loadingMore;


    public PostsPage(List<PostItem> _postItemList, String _after, String _before) {
        this._postItemList = _postItemList;
        this._after = _after;
        this._before = _before;
    }


    public List<PostItem> get_postItemList() {
        return _postItemList;
    }

    public void set_postItemList(List<PostItem> _postItemList) {
        this._postItemList = _postItemList;
    }

    public String get_after() {
        return _after;
    }

    public void set_after(String _after) {
        this._after = _after;
    }

    public String get_before() {
        return _before;
    }

    public void set_before(String _before) {
        this._before = _before;
    }
}
