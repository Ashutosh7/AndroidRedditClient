package com.projects.ashutoshb.redditclient.activities;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;

import com.projects.ashutoshb.redditclient.R;
import com.squareup.picasso.Callback;
import com.squareup.picasso.Picasso;

import fr.castorflex.android.smoothprogressbar.SmoothProgressBar;

/**
 * Created by ashutosh.b on 8/19/15.
 */
public class ImageDialog extends Activity {

    private ImageView mDialog;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dialog_layout);

        String url = getIntent().getExtras().getString("url");

        mDialog = (ImageView)findViewById(R.id.dialog_image);
        final SmoothProgressBar progressBar = (SmoothProgressBar) findViewById(R.id.progressBar);

        Picasso.with(this)
                .load(url).noFade().placeholder(R.drawable.progress_animation)
                .fit().into(mDialog, new Callback() {
            @Override
            public void onSuccess() {
                mDialog.setVisibility(View.VISIBLE);
                progressBar.setVisibility(View.GONE);
            }

            @Override
            public void onError() {

            }
        });


        Log.i("url", url);
        mDialog.setClickable(true);

        //finish the activity (dismiss the image dialog) if the user clicks
        //anywhere on the image
        mDialog.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

    }
}