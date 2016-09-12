package com.example.aoc.project_arnold.Activities;

import android.app.FragmentManager;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.example.aoc.project_arnold.Adapters.ImageAdapter;
import com.example.aoc.project_arnold.R;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.MobileAds;

import java.util.ArrayList;
import java.util.List;



public class MuscleGroupsActivity extends AppCompatActivity {
    public static final String KEY = "0" ;
    public List<String> listMusculeGroup = new ArrayList<String>();
    private AdView mAdView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_muscle_groups);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        // Get a support ActionBar corresponding to this toolbar
        ActionBar ab = getSupportActionBar();

        // Enable the Up button
        ab.setDisplayHomeAsUpEnabled(true);



        GridView gridview = (GridView) findViewById(R.id.gridview);
        gridview.setAdapter(new ImageAdapter(this));

        gridview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> parent, View v, int position, long id) {
                sendIntent(position);
            }
        });

        //Add initialise
        MobileAds.initialize(getApplicationContext(), "ca-app-pub-0044965499066904~9181100901");
        mAdView = (AdView) findViewById(R.id.activity_muscle_groups_ads);
        AdRequest adRequest = new AdRequest.Builder().build();
        mAdView.loadAd(adRequest);
    }

    @Override
    protected void onResume() {
        super.onResume();
        updateBackground();

        if (mAdView != null) {
            mAdView.resume();
        }
    }

    public void onPause() {
        if (mAdView != null) {
            mAdView.pause();
        }
        super.onPause();
    }

    public void onDestroy() {
        if (mAdView != null) {
            mAdView.destroy();
        }
        super.onDestroy();
    }

    private void updateBackground(){
        int primaryColor = Integer.parseInt(MainActivity.sharedPreferencesPrimaryColors.getString(MainActivity.colorSPKey,Integer.toString(R.color.colorWhite)));
        LinearLayout linearLayout = (LinearLayout) findViewById(R.id.muscle_groups_activity_linear_layout);
        linearLayout.setBackgroundColor(getResources().getColor(primaryColor));
    }

    private void sendIntent(int position){
       // Toast.makeText(this, "" + position, Toast.LENGTH_SHORT).show();
        Intent mIntent = new Intent(this,MuscleGroupExercisesActivity.class);
        mIntent.putExtra(KEY,position);
        startActivity(mIntent);
    }

  /*  @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }*/

    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_main, menu);
        return true;
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {

            case android.R.id.home:
                onBackPressed();
                return true;
        }

        return super.onOptionsItemSelected(item);
    }


}
