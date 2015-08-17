package com.projects.ashutoshb.redditclient.services;

import android.os.AsyncTask;
import android.os.Build;
import android.util.Log;
import android.view.View;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;


/**
 * Created by ashutosh.b on 8/14/15.
 */
public class QueryAPI extends AsyncTask<Void, Void, String> {

    private Exception exception;

    String endpoint="/r/askreddit?after=\"\"";

    public QueryAPI(String endpoint) {
        this.endpoint = endpoint;
    }


    protected String doInBackground(Void... urls) {
        // Do some validation here

        String serverAddress = null;
        String uri;
        if(Build.FINGERPRINT.startsWith("generic"))
            serverAddress = "http://10.0.2.2:8080/api";
        else
            serverAddress = "192.168.0.100";

        uri = serverAddress + endpoint;

        try {
            URL url = new URL(uri);
            HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
            try {
                BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(urlConnection.getInputStream()));
                StringBuilder stringBuilder = new StringBuilder();
                String line;
                while ((line = bufferedReader.readLine()) != null) {
                    stringBuilder.append(line).append("\n");
                }
                bufferedReader.close();
                return stringBuilder.toString();
            }
            finally{
                urlConnection.disconnect();
            }
        }
        catch(Exception e) {
            Log.e("ERROR", e.getMessage(), e);
            return null;
        }
    }

    protected void onPostExecute(String response) {
        if(response == null) {
            response = "THERE WAS AN ERROR";
        }
        Log.i("INFO", response);

    }
}