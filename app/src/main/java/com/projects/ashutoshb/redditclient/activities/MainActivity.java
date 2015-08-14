package com.projects.ashutoshb.redditclient.activities;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.RelativeLayout;

import com.projects.ashutoshb.redditclient.R;
import com.projects.ashutoshb.redditclient.adapters.NavListAdapter;
import com.projects.ashutoshb.redditclient.fragments.MyAbout;
import com.projects.ashutoshb.redditclient.fragments.MyHome;
import com.projects.ashutoshb.redditclient.fragments.MySettings;
import com.projects.ashutoshb.redditclient.models.NavItem;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    DrawerLayout drawerLayout;
    RelativeLayout drawerPane;
    ListView lvNav;
    List<NavItem> listNavItems;
    List<Fragment> listFragments;
    ActionBarDrawerToggle actionBarDrawerToggle;
    public static final int FIRST_FRAGMENT = 0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        assignUIReferences();

        listNavItems = new ArrayList<NavItem>();
        populateNavList();

        listFragments = new ArrayList<Fragment>();
        populateFragmentList();

        loadFragmentAtPosition(FIRST_FRAGMENT); //load first fragment as default

        lvNav.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view,
                                    int position, long id) {
                //replace the fragment with the selection correspondingly:
                loadFragmentAtPosition(position);

            }
        });

        //create listener for drawer layout
        actionBarDrawerToggle = new ActionBarDrawerToggle(this, drawerLayout,
                R.string.drawer_opened, R.string.drawer_closed )

        {
            @Override
            public void onDrawerOpened(View drawerView) {
                invalidateOptionsMenu();
                super.onDrawerOpened(drawerView);
            }

            @Override
            public void onDrawerClosed(View drawerView) {
                invalidateOptionsMenu();
                super.onDrawerClosed(drawerView);
            }
        };

        drawerLayout.setDrawerListener(actionBarDrawerToggle);
    }

    private void loadFragmentAtPosition(int position) {
        FragmentManager fragmentManager = getSupportFragmentManager();
        fragmentManager.beginTransaction().replace(R.id.main_content,
                listFragments.get(position)).commit();
        setTitle(listNavItems.get(position).getTitle());
        lvNav.setItemChecked(position, true);
        drawerLayout.closeDrawer(drawerPane);
    }

    private void populateFragmentList() {
        listFragments.add(new MyHome());
        listFragments.add(new MySettings());
        listFragments.add(new MyAbout());
    }

    private void populateNavList() {
        listNavItems.add(new NavItem("Home", "Home page", R.drawable.home));
        listNavItems.add(new NavItem("Settings", "Change Something", R.drawable.settings));
        listNavItems.add(new NavItem("About", "Info", R.drawable.info));
        NavListAdapter navListAdapter = new NavListAdapter(getApplicationContext(),
                R.layout.item_nav_list,listNavItems);
        lvNav.setAdapter(navListAdapter);
    }

    private void assignUIReferences() {
        drawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawerPane = (RelativeLayout) findViewById(R.id.drawer_pane);
        lvNav = (ListView) findViewById(R.id.nav_list);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the MyHome/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        if(actionBarDrawerToggle.onOptionsItemSelected(item))
            return true;
        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onPostCreate(Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        actionBarDrawerToggle.syncState();
    }
}
