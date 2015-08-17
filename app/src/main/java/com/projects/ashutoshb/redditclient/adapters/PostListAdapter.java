package com.projects.ashutoshb.redditclient.adapters;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.projects.ashutoshb.redditclient.R;
import com.projects.ashutoshb.redditclient.models.NavItem;
import com.projects.ashutoshb.redditclient.models.PostItem;

import java.net.URL;
import java.util.List;

/**
 * Created by ashutosh.b on 8/14/15.
 */
public class PostListAdapter extends ArrayAdapter<PostItem> {

    Context context;
    int resLayout;
    List<PostItem> listPostItems;

    public PostListAdapter(Context context, int resLayout, List<PostItem> listPostItems) {
        super(context,resLayout,listPostItems);
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
        ImageView preview = (ImageView) v.findViewById(R.id.preview);

        PostItem postItem = listPostItems.get(position);
        title.setText(postItem.getTitle());
        domain.setText(postItem.getDomain().toString());
        score.setText(String.valueOf(postItem.getScore()));
        numComments.setText(String.valueOf(postItem.getNumComments()));
        preview.setImageResource(convertToImage(postItem.getPreviewUrl()));

        return v;
    }

    private int convertToImage(URL previewUrl) {

        return 0;
    }
}
