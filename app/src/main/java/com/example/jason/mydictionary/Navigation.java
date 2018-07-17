package com.example.jason.mydictionary;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;

import com.example.jason.mydictionary.Database.KamusHelper;
import com.example.jason.mydictionary.Fragment.English2Indonesia;
import com.example.jason.mydictionary.Fragment.Indonesia2English;
import com.example.jason.mydictionary.adapter.KamusAdapter;

import butterknife.BindView;
import butterknife.ButterKnife;

public class Navigation extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.drawer_layout)
    DrawerLayout drawer;
    @BindView(R.id.nav_view)
    NavigationView navigationView;

    KamusAdapter adapter;
    KamusHelper kamusHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_navigation);
        ButterKnife.bind(this);
        setSupportActionBar(toolbar);

        getSupportActionBar().setTitle("My Dictionary");
        adapter = new KamusAdapter(this);
        kamusHelper = new KamusHelper(this);

        navigationView.setNavigationItemSelectedListener(this);
        if(savedInstanceState==null){
            Fragment currentFragment = new English2Indonesia();
            getSupportFragmentManager().beginTransaction().replace(R.id.content_main, currentFragment).commit();
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();
    }

    @Override
    public void onBackPressed() {
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        int id = item.getItemId();

        Fragment fragment = null;

        String title = "";

        if (id == R.id.nav_En_to_In) {
            title = "English to Indonesia";
            fragment = new English2Indonesia();
        } else if (id == R.id.nav_In_to_En) {
            title = "Indonesia to English";
            fragment = new Indonesia2English();
        }

        if (fragment != null){
            getSupportFragmentManager().beginTransaction().replace(R.id.content_main, fragment).commit();
        }

        getSupportActionBar().setTitle(title);

        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}
