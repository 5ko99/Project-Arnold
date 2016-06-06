package com.example.aoc.project_arnold.Activities;

import android.content.Intent;
import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
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
    private int recordKg;
    private int recordReps;

    //View with info about training
    private TextView tv_type;
    private TextView tv_date;
    private TextView tv_record;
    private TextView tv_recordExercise;
    private TextView tv_recordKg;
    private TextView tv_recordReps;
    private TextView tv_recordExerciseTittle;
    private TextView tv_recordKgTittle;
    private TextView tv_recordRepsTittle;

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
        mCursor = dbPref.getVals(type, date);

        //TODO: Fix error with reading form db
        if (mCursor.moveToFirst()) {
            do {
                recordExercise = mCursor.getString(mCursor.getColumnIndexOrThrow("record_exercise"));
                recordKg = mCursor.getInt(mCursor.getColumnIndex("record_kg"));
                recordReps = mCursor.getInt(mCursor.getColumnIndex("record_reps"));
            } while (mCursor.moveToNext());
        }


        mCursor.close();
        dbPref.close();

        setInfoFromDB();

    }

    private void setInfoFromDB() {
        //Extract in method

        //Initialise all tv
        tv_type = (TextView) findViewById(R.id.tv_info_type);
        tv_date = (TextView) findViewById(R.id.tv_info_date);
        tv_record = (TextView) findViewById(R.id.tv_info_record);
        tv_recordExercise = (TextView) findViewById(R.id.tv_info_record_exercise);
        tv_recordKg = (TextView) findViewById(R.id.tv_info_record_kg);
        tv_recordReps = (TextView) findViewById(R.id.tv_info_record_reps);
        tv_recordExerciseTittle = (TextView) findViewById(R.id.tv_info_record_exercise_tittle);
        tv_recordKgTittle = (TextView) findViewById(R.id.tv_info_record_kg_title);
        tv_recordRepsTittle = (TextView) findViewById(R.id.tv_info_record_reps_tittle);
        //Set info to tv
        tv_type.setText(type);
        tv_date.setText(date);

        //Check if this traning have record
        if (recordExercise.matches("No record")) {
            //If there is no record hide view
            tv_record.setVisibility(View.GONE);
            tv_recordExercise.setVisibility(View.GONE);
            tv_recordKg.setVisibility(View.GONE);
            tv_recordReps.setVisibility(View.GONE);
            tv_recordExerciseTittle.setVisibility(View.GONE);
            tv_recordKgTittle.setVisibility(View.GONE);
            tv_recordRepsTittle.setVisibility(View.GONE);
        } else {
            //If is record show info in views
            tv_recordExercise.setText(recordExercise);
            tv_recordKg.setText(Integer.toString(recordKg));
            tv_recordReps.setText(Integer.toString(recordReps));
        }
    }

}
