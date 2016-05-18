package com.example.aoc.project_arnold.Activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.Toast;

import com.example.aoc.project_arnold.Adapters.ImageAdapter;
import com.example.aoc.project_arnold.R;

import java.util.ArrayList;
import java.util.List;



public class MainActivity extends AppCompatActivity {
    public static final String KEY = "0" ;
    public List<String> listMusculeGroup = new ArrayList<String>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });


        GridView gridview = (GridView) findViewById(R.id.gridview);
        gridview.setAdapter(new ImageAdapter(this));

        gridview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> parent, View v, int position, long id) {
                sendIntent(position);
            }
        });


    }
    public void sendIntent(int position){
       // Toast.makeText(this, "" + position, Toast.LENGTH_SHORT).show();
        Intent mIntent = new Intent(this,MuscleGroupActivity.class);
        mIntent.putExtra(KEY,position);
        startActivity(mIntent);
    }

    private void fillListWithMuscleGroup() {

        listMusculeGroup.add(getString(R.string.Bedra));
        listMusculeGroup.add(getString(R.string.Biceps));
        listMusculeGroup.add(getString(R.string.Gryb));
        listMusculeGroup.add(getString(R.string.Gyrdi));
        listMusculeGroup.add(getString(R.string.Korem));
        listMusculeGroup.add(getString(R.string.Prasec));
        listMusculeGroup.add(getString(R.string.Predmishnici));
        listMusculeGroup.add(getString(R.string.Ramo));
        listMusculeGroup.add(getString(R.string.Sedalishte));
        listMusculeGroup.add(getString(R.string.Triceps));
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
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
