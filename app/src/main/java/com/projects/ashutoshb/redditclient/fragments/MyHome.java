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
import android.widget.TabHost;

import com.projects.ashutoshb.redditclient.R;
import com.projects.ashutoshb.redditclient.adapters.MyFragmentPagerAdapter;
import com.projects.ashutoshb.redditclient.inner.fragments.Tab1Fragment;

import java.util.ArrayList;
import java.util.List;


/**
 * Created by ashutosh.b on 8/14/15.
 */
public class MyHome extends Fragment implements TabHost.OnTabChangeListener,
        ViewPager.OnPageChangeListener {

    private ViewPager _viewPager;
    private TabHost _tabHost;
    private MyFragmentPagerAdapter _myViewPagerAdapter;
    private int _idx = 0;
    private View view;
    private String _subReddit;
    String[] tabNames = {"Hot", "New", "Rising", /*"Saved",*/ "Top", "Controversial",/*"Gilded"*/};

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        view = inflater.inflate(R.layout.tabs_viewpager_layout, container, false);
        _subReddit = getArguments().getString("subReddit");
        //TabHostView Pager"
        _idx++;
        this.initViewPager();
        this.initTabHost(savedInstanceState);

        return view;
    }


    public class FakeContent implements TabHost.TabContentFactory {

        Context context;

        public FakeContent(Context context) {
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

        _tabHost = (TabHost) view.findViewById(R.id.tabhost);
        _tabHost.setup();

        for (int i = 0; i < tabNames.length; i++) {
            TabHost.TabSpec tabSpec;
            tabSpec = _tabHost.newTabSpec(tabNames[i]);
            tabSpec.setIndicator(tabNames[i]);
            tabSpec.setContent(new FakeContent(getActivity()));
            _tabHost.addTab(tabSpec);
        }

        _tabHost.setOnTabChangedListener(this);

    }

    @Override
    public void onPageScrolled(int i, float v, int i1) {

    }

    @Override
    public void onPageSelected(int selectedItem) {
        _tabHost.setCurrentTab(selectedItem);
    }

    @Override
    public void onPageScrollStateChanged(int i) {

    }

    @Override
    public void onTabChanged(String tabId) {

        int selectedItem = _tabHost.getCurrentTab();
        _viewPager.setCurrentItem(selectedItem);
        HorizontalScrollView hScrollView = (HorizontalScrollView) view.findViewById(R.id.h_scroll_view);
        View tabView = _tabHost.getCurrentTabView();
        int scrollPos = tabView.getLeft() - (hScrollView.getWidth() - tabView.getWidth()) / 2;
        hScrollView.smoothScrollTo(scrollPos, 0);

    }

    private Bundle makeBundle(String subReddit, String category) {

        Bundle args = new Bundle();
        args.putString("subReddit", subReddit);
        args.putString("category", category);
        return args;
    }


    private void initViewPager() {
        _viewPager = (ViewPager) view.findViewById(R.id.view_pager);
        List<Fragment> listFragments = new ArrayList<Fragment>();
        listFragments = getFragmentList();

        MyFragmentPagerAdapter myFragmentPagerAdapter = new MyFragmentPagerAdapter
                (getChildFragmentManager(), listFragments);

        _viewPager.setAdapter(myFragmentPagerAdapter);
        _viewPager.addOnPageChangeListener(this);
    }


    private Fragment getFragment(String subReddit, String category) {

        Tab1Fragment sampleFragment = new Tab1Fragment();
        Bundle args = makeBundle(subReddit, category);
        sampleFragment.setArguments(args);

        return sampleFragment;
    }

    private List<Fragment> getFragmentList() {

        List<Fragment> listFragments = new ArrayList<Fragment>();

        for (int i = 0; i < tabNames.length; i++) {
            listFragments.add(getFragment(_subReddit, tabNames[i].toLowerCase()));
        }
        return listFragments;

    }
}
