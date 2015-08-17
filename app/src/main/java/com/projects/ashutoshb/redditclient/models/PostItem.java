package com.projects.ashutoshb.redditclient.models;

import java.net.URI;

/**
 * Created by ashutosh.b on 8/17/15.
 */
public class PostItem {

    private String _domain;
    private String _author;
    private boolean _over18;
    private URI _thumbnail;
    private int _numComments;
    private String _subredditId;
    private String _postHint;
    private URI _url;
    private String _title;
    private URI _previewUrl;
    private int _score;
    private String _permalink;
    private String _subredditName;

    public PostItem(String _domain, String _author, boolean _over18, URI thumbnail,
                    int _numComments, String subredditId, String postHint, URI url,
                    String title, URI _previewUrl, int _score, String _permalink,
                    String _subredditName) {

        this._domain = _domain;
        this._author = _author;
        this._over18 = _over18;
        this._thumbnail = thumbnail;
        this._numComments = _numComments;
        this._subredditId = subredditId;
        this._postHint = postHint;
        this._url = url;
        this._title = title;
        this._previewUrl = _previewUrl;
        this._score = _score;
        this._permalink = _permalink;
        this._subredditName = _subredditName;
    }

    public String getDomain() {
        return _domain;
    }

    public void setDomain(String domain) {
        this._domain = domain;
    }

    public String getAuthor() {
        return _author;
    }

    public void setAuthor(String author) {
        this._author = author;
    }

    public boolean isOver18() {
        return _over18;
    }

    public void setOver18(boolean over18) {
        this._over18 = over18;
    }

    public URI getThumbnail() {
        return _thumbnail;
    }

    public void setThumbnail(URI thumbnail) {
        this._thumbnail = thumbnail;
    }

    public int getNumComments() {
        return _numComments;
    }

    public void setNumComments(int numComments) {
        this._numComments = numComments;
    }

    public String getSubredditId() {
        return _subredditId;
    }

    public void setSubredditId(String subredditId) {
        this._subredditId = subredditId;
    }

    public String getPostHint() {
        return _postHint;
    }

    public void setPostHint(String postHint) {
        this._postHint = postHint;
    }

    public URI getUrl() {
        return _url;
    }

    public void setUrl(URI url) {
        this._url = url;
    }

    public String getTitle() {
        return _title;
    }

    public void setTitle(String title) {
        this._title = title;
    }

    public URI getPreviewUrl() {
        return _previewUrl;
    }

    public void setPreviewUrl(URI previewUrl) {
        this._previewUrl = previewUrl;
    }

    public int getScore() {
        return _score;
    }

    public void setScore(int score) {
        this._score = score;
    }

    public String getPermalink() {
        return _permalink;
    }

    public void setPermalink(String permalink) {
        this._permalink = permalink;
    }

    public String getSubredditName() {
        return _subredditName;
    }

    public void setSubredditName(String subredditName) {
        this._subredditName = subredditName;
    }
}
