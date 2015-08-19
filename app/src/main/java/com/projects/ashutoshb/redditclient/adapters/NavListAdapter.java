package com.projects.ashutoshb.redditclient.adapters;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;


import com.projects.ashutoshb.redditclient.R;
import com.projects.ashutoshb.redditclient.models.NavItem;

import java.util.List;

/**
 * Created by ashutosh.b on 8/14/15.
 */
public class NavListAdapter extends ArrayAdapter<NavItem> {

    Context context;
    int resLayout;
    List<NavItem> listNavItems;

    public NavListAdapter(Context context, int resLayout, List<NavItem> listNavItems) {
        super(context, resLayout, listNavItems);
        this.context = context;
        this.resLayout = resLayout;
        this.listNavItems = listNavItems;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View v = View.inflate(context, resLayout, null);

        TextView tvTitle = (TextView) v.findViewById(R.id.title);

        NavItem navItem = listNavItems.get(position);
        tvTitle.setText(navItem.getTitle());

        return v;
    }
}
