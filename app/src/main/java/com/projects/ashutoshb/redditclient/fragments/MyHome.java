package com.projects.ashutoshb.redditclient.fragments;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.HorizontalScrollView;
import android.widget.ListView;
import android.widget.TabHost;
import android.support.v4.app.ListFragment;
import com.projects.ashutoshb.redditclient.R;
import com.projects.ashutoshb.redditclient.adapters.MyFragmentPagerAdapter;
import com.projects.ashutoshb.redditclient.adapters.PostListAdapter;
import com.projects.ashutoshb.redditclient.inner.fragments.Tab1Fragment;
import com.projects.ashutoshb.redditclient.inner.fragments.Tab2Fragment;
import com.projects.ashutoshb.redditclient.inner.fragments.Tab3Fragment;
import com.projects.ashutoshb.redditclient.models.PostItem;

import java.util.ArrayList;
import java.util.List;


/**
 * Created by ashutosh.b on 8/14/15.
 */
public class MyHome extends Fragment implements TabHost.OnTabChangeListener,
        ViewPager.OnPageChangeListener{

    private ViewPager viewPager;
    private TabHost tabHost;
    private MyFragmentPagerAdapter myViewPagerAdapter;
    int i=0;
    View v;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        v = inflater.inflate(R.layout.tabs_viewpager_layout, container, false);

        //TabHostView Pager"
        i++;
        this.initViewPager();
        this.initTabHost(savedInstanceState);

        return v;
    }




    
    public class FakeContent implements TabHost.TabContentFactory {

        Context context;
        public FakeContent(Context context){
            this.context = context;
        }

        @Override
        public View createTabContent(String tag) {
            View fakeView = new View(context);
            fakeView.setMinimumHeight(0);
            fakeView.setMinimumWidth(0);
            return fakeView;
        }
    }

    private void initTabHost(Bundle savedInstanceState) {

        tabHost = (TabHost)v.findViewById(R.id.tabhost);
        tabHost.setup();

        String[] tabNames = {"Hot", "New", "Rising", "Saved", "Top", "Controversial","Gilded"};
        for(int i=0;i<tabNames.length;i++){
            TabHost.TabSpec tabSpec;
            tabSpec = tabHost.newTabSpec(tabNames[i]);
            tabSpec.setIndicator(tabNames[i]);
            tabSpec.setContent(new FakeContent(getActivity()));
            tabHost.addTab(tabSpec);
        }

        tabHost.setOnTabChangedListener(this);

    }

    @Override
    public void onPageScrolled(int i, float v, int i1) {

    }

    @Override
    public void onPageSelected(int selectedItem) {
        tabHost.setCurrentTab(selectedItem);
    }

    @Override
    public void onPageScrollStateChanged(int i) {

    }

    @Override
    public void onTabChanged(String tabId) {

        int selectedItem = tabHost.getCurrentTab();
        viewPager.setCurrentItem(selectedItem);
        HorizontalScrollView hScrollView = (HorizontalScrollView)v.findViewById(R.id.h_scroll_view);
        View tabView = tabHost.getCurrentTabView();
        int scrollPos = tabView.getLeft() - (hScrollView.getWidth() - tabView.getWidth())/2;
        hScrollView.smoothScrollTo(scrollPos,0);

    }

    private void initViewPager() {
        viewPager = (ViewPager) v.findViewById(R.id.view_pager);
        List<Fragment> listFragments = new ArrayList<Fragment>();
        listFragments.add(new Tab1Fragment());
        listFragments.add(new Tab2Fragment());
        listFragments.add(new Tab3Fragment());
        listFragments.add(new Tab1Fragment());
        listFragments.add(new Tab2Fragment());
        listFragments.add(new Tab3Fragment());

        MyFragmentPagerAdapter myFragmentPagerAdapter = new MyFragmentPagerAdapter
                (getFragmentManager(), listFragments);

        viewPager.setAdapter(myFragmentPagerAdapter);
        viewPager.addOnPageChangeListener(this);
    }
}
