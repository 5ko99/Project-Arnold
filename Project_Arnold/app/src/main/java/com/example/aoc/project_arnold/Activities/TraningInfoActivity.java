package com.example.aoc.project_arnold.Activities;

import android.content.Intent;
import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;

import com.example.aoc.project_arnold.Database.DBPref;
import com.example.aoc.project_arnold.R;

public class TraningInfoActivity extends AppCompatActivity {
    Cursor c;
    DBPref pref;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_traning_info);
        Intent intent = getIntent();
        Bundle bundle = intent.getExtras();
        int position = bundle.getInt("position");
        String type = bundle.getString("type");
        String date = bundle.getString("date");

        Toast.makeText(this,position+" "+type+" "+date,Toast.LENGTH_SHORT).show();
        //Get other data for training
    }
}
