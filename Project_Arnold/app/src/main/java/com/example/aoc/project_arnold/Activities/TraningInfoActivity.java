package com.example.aoc.project_arnold.Activities;

import android.content.Intent;
import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

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
        int position = bundle.getInt("key");
        String type = bundle.getString("key");
        //Get other data for training
    }
}
