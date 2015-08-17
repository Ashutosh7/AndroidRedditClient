package com.projects.ashutoshb.redditclient.services;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;
import android.os.Build;
import android.util.Log;


import com.projects.ashutoshb.redditclient.adapters.PostListAdapter;
import com.projects.ashutoshb.redditclient.models.PostItem;
import com.projects.ashutoshb.redditclient.models.PostsPage;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URI;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class GetFromAPI extends AsyncTask<String, Void, List<PostItem>> {

    private String _endpoint;
    private AsyncDelegate _delegate;
    private String _serverAddress = null;
    private String _uri = null;
    private String _after;
    private String _before;


    public GetFromAPI( String endpoint, AsyncDelegate delegate) {
        _endpoint = endpoint;
        this._delegate = delegate;
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
        Log.i("url", _endpoint);
    }

    @Override
    protected List<PostItem> doInBackground(String... params) {
        if (Build.FINGERPRINT.startsWith("generic"))
            _serverAddress = "http://10.0.2.2:8080/api";
        else
            _serverAddress = "192.168.0.100";

        _uri = _serverAddress + _endpoint;

        List<PostItem> result = new ArrayList<PostItem>();

        try {
            URL url = new URL(_uri);
            HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
            try {
                BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(urlConnection.getInputStream()));
                StringBuilder stringBuilder = new StringBuilder();
                String line;
                while ((line = bufferedReader.readLine()) != null) {
                    stringBuilder.append(line).append("\n");
                }
                bufferedReader.close();
                JSONObject obj = new JSONObject(stringBuilder.toString());
                JSONArray arr = obj.getJSONArray("children");
                _after = obj.getString("after");
                _before = obj.getString("before");
                Log.i("JSON", String.valueOf(arr.length()));

                for (int i = 0; i < arr.length(); i++) {
                    result.add(convertPost(arr.getJSONObject(i)));
                }

                return result;

            } finally {
                urlConnection.disconnect();
            }
        } catch (Exception e) {
            Log.e("ERROR", e.getMessage(), e);
            return null;
        }
    }

    @Override
    protected void onPostExecute(List<PostItem> result) {
        super.onPostExecute(result);
        Log.i("after",_after);
        PostsPage postsPage = new PostsPage(result, _after, _before);
        _delegate.asyncComplete(true, postsPage);
    }


    private PostItem convertPost(JSONObject obj) throws JSONException {
        String domain, postHint, author, subredditId, title, permalink, subRedditName, previewURL;
        domain = obj.getString("domain");
        author = obj.getString("author");
        boolean over18 = obj.getBoolean("over_18");
        subredditId = obj.getString("subreddit_id");
        int numComments = obj.getInt("num_comments");

        try {
            postHint = obj.getString("post_hint");
        }catch(Exception e){
            postHint = "";
        }
        title = obj.getString("title");
        String thumbnail = obj.getString("thumbnail");
        String url = obj.getString("url");
        try {
            JSONObject preview = (obj.getJSONObject("preview").getJSONArray("images")).getJSONObject(0);
            previewURL = preview.getJSONObject("source").getString("url");
        }catch (Exception e){
            previewURL = "";
        }
        int score = obj.getInt("score");
        permalink = obj.getString("permalink");
        subRedditName = obj.getString("subreddit");


        return new PostItem(domain, author, over18, URI.create(thumbnail), numComments, subredditId, postHint,
                URI.create(url), title, URI.create(previewURL), score, permalink, subRedditName);
    }

}
