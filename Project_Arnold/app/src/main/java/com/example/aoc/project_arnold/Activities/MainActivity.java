package com.example.aoc.project_arnold.Activities;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.Gravity;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

import com.example.aoc.project_arnold.R;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {
    public DrawerLayout drawer;
    public NavigationView navigationView;
    public static int count;
    TextView tvCount;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
        tvCount = (TextView) findViewById(R.id.main_activity_tv_count);
    }

    @Override
    protected void onResume() {
        super.onResume();
/*        drawer.openDrawer(Gravity.LEFT);*/
        SharedPreferences sharedPreferences = getSharedPreferences(AddTrainingActivity.traningCountPreferneces,MODE_PRIVATE);
        count = sharedPreferences.getInt(AddTrainingActivity.traningCountInt,0);
        tvCount.setText(Integer.toString(count));
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // TODO: Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_muscule_groups_acttivity) {
            Intent mIntent = new Intent(this,MuscleGroupsActivity.class);
            startActivity(mIntent);

        } else if (id==R.id.nav_home){
            drawer.closeDrawer(Gravity.LEFT);
        } else if(id==R.id.nav_history){
            Intent mIntent = new Intent(this,HistoryActivity.class);
            startActivity(mIntent);
        }else if(id==R.id.nav_add_traning){
            Intent mIntent = new Intent(this,AddTrainingActivity.class);
            startActivity(mIntent);
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }


}
