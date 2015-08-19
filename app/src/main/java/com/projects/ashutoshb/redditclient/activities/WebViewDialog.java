package com.projects.ashutoshb.redditclient.activities;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.VideoView;

import com.projects.ashutoshb.redditclient.R;

import fr.castorflex.android.smoothprogressbar.SmoothProgressBar;


/**
 * Created by ashutosh.b on 8/19/15.
 */
public class WebViewDialog extends Activity {

    WebView myWebView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.webview_layout);
        String url = getIntent().getExtras().getString("link");
        final SmoothProgressBar progressBar = (SmoothProgressBar) findViewById(R.id.progressBar);
        final String domain = getIntent().getExtras().getString("domain");

        myWebView = (WebView) findViewById(R.id.webView);
        myWebView.setWebViewClient(new WebViewClient() {

            @Override
            public void onPageFinished(WebView view, String url) {
                if (domain.startsWith("self."))
                    myWebView.loadUrl("javascript:(function() { " +
                            "document.getElementById('header').style.display=\"none\"; " +
                            "document.getElementsByClassName('side')[0].style.display=\"none\"; " +
                            "})()");

                myWebView.setVisibility(View.VISIBLE);
                progressBar.setVisibility(View.GONE);


            }
        });

        myWebView.getSettings().setJavaScriptEnabled(true);
        myWebView.getSettings().setLoadWithOverviewMode(true);
        Log.i("opening", url);
        myWebView.loadUrl(url);
    }


}

