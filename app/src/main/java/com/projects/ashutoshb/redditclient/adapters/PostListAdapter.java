package com.projects.ashutoshb.redditclient.adapters;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.widget.ArrayAdapter;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.projects.ashutoshb.redditclient.R;
import com.projects.ashutoshb.redditclient.activities.ImageDialog;
import com.projects.ashutoshb.redditclient.activities.WebViewDialog;
import com.projects.ashutoshb.redditclient.models.PostItem;
import com.projects.ashutoshb.redditclient.services.RoundedTransformation;
import com.squareup.picasso.Picasso;

import java.net.URI;
import java.util.List;

/**
 * Created by ashutosh.b on 8/14/15.
 */
public class PostListAdapter extends ArrayAdapter<PostItem> {

    Context context;
    int resLayout;

    public void setListPostItems(List<PostItem> listPostItems) {
        this.listPostItems = listPostItems;
    }

    List<PostItem> listPostItems;

    public PostListAdapter(Context context, int resLayout, List<PostItem> listPostItems) {
        super(context, resLayout, listPostItems);
        this.context = context;
        this.resLayout = resLayout;
        this.listPostItems = listPostItems;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View v = View.inflate(context, resLayout, null);

        TextView title = (TextView) v.findViewById(R.id.title);
        TextView domain = (TextView) v.findViewById(R.id.domain);
        TextView score = (TextView) v.findViewById(R.id.score);
        TextView numComments = (TextView) v.findViewById(R.id.num_comments);
        ImageButton preview = (ImageButton) v.findViewById(R.id.preview);

        final PostItem postItem = listPostItems.get(position);
        title.setText(postItem.getTitle());
        domain.setText(postItem.getDomain().toString());
        score.setText(String.valueOf(postItem.getScore()));
        numComments.setText(String.valueOf(postItem.getNumComments()) + " comments");
//        preview.setImageResource(convertToImage(postItem.getPreviewUrl()));
        if(postItem.getPreviewUrl().toString().length()>0) {
            Picasso.with(getContext())
                    .load(postItem.getPreviewUrl().toString()).noFade().placeholder(R.drawable.progress_animation)
                    .fit().transform(new RoundedTransformation(20,0)).into(preview);
        }
        else{
            ViewGroup.LayoutParams layoutParams = preview.getLayoutParams();
            layoutParams.height = 100;
            layoutParams.width = 100;
            preview.setLayoutParams(layoutParams);
            preview.setImageResource(R.drawable.go);
        }

        preview.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(postItem.getPostHint().equals("image")) {
                    Intent intent = new Intent(context, ImageDialog.class);
                    intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    Bundle bundle = new Bundle();
                    bundle.putString("url", postItem.getUrl().toString());
                    intent.putExtras(bundle);
                    context.startActivity(intent);
                }
                else{
                    Intent intent = new Intent(context, WebViewDialog.class);
                    intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    Bundle bundle = new Bundle();
                    bundle.putString("link", postItem.getUrl().toString());
                    bundle.putString("domain",postItem.getDomain());
                    intent.putExtras(bundle);
                    context.startActivity(intent);
                }

            }
        });
        return v;
    }

}
