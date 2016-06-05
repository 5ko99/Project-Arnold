package com.example.aoc.project_arnold.Activities;

import android.content.Intent;
import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import com.example.aoc.project_arnold.Database.DBHelper;
import com.example.aoc.project_arnold.Database.DBPref;
import com.example.aoc.project_arnold.R;

public class TraningInfoActivity extends AppCompatActivity {
    private DBPref dbPref;
    private Cursor mCursor;

    //Fields with info about training
    private int position;
    private String type;
    private String date;
    private String recordExercise;
    private  int  recordKg;
    private int recordReps;

    //View with info about training
    private TextView tv_type;
    private TextView tv_date;
    private TextView tv_recordExercise;
    private TextView tv_recordKg;
    private TextView tv_recordReps;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_traning_info);
        Intent intent = getIntent();
        Bundle bundle = intent.getExtras();

        position = bundle.getInt("position");
        type = bundle.getString("type");
        date = bundle.getString("date");

        dbPref = new DBPref(this);
        mCursor = dbPref.getVals(type,date);

        //TODO: Fix error with reading form db
        if (mCursor.moveToFirst()){
            do{
                recordExercise=mCursor.getString(mCursor.getColumnIndexOrThrow("record_exercise"));
                recordKg=mCursor.getInt(mCursor.getColumnIndex("record_kg"));
                recordReps=mCursor.getInt(mCursor.getColumnIndex("record_reps"));
            }while(mCursor.moveToNext());
        }


        mCursor.close();
        dbPref.close();


        StringBuilder info = new StringBuilder();
        info.append(position + " " + type + " " + date + " " + recordExercise + "  " + recordKg+ "  "+ recordReps);

        Toast.makeText(this,info.toString(), Toast.LENGTH_LONG).show();

        //TODO: Show info in TextView

        //Initialise all tv
        tv_type= (TextView) findViewById(R.id.tv_info_type);
        tv_date = (TextView) findViewById(R.id.tv_info_date);

        //Set info to tv
        tv_type.setText(type);
        tv_date.setText(date);

    }
}
