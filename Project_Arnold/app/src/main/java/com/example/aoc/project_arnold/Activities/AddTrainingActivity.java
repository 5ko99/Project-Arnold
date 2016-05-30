package com.example.aoc.project_arnold.Activities;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.aoc.project_arnold.Database.DBPref;
import com.example.aoc.project_arnold.R;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class AddTrainingActivity extends Activity implements AdapterView.OnItemSelectedListener {
    Spinner spinnerTraningType;
    EditText etNummberExercises;
    Spinner spinnerRecord;
    EditText etRecordKg;
    EditText etRecordReps;
    public static String traningCountPreferneces = "traningsCount";
    public static SharedPreferences sharedPreferences;
    public static SharedPreferences.Editor edit;
    public static String traningCountInt = "int";
/*    public static  String DB_CREATE;*/
    public static BufferedReader DB_CREATE_TXT;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_traning);

        try {
            DB_CREATE_TXT = new BufferedReader(
                   new InputStreamReader(getAssets().open("db_create.txt")));
        } catch (IOException e) {
            e.printStackTrace();
        }
        // DB_CREATE =  getString(R.string.DB_CREATE); // Set to string sql that will create database

        sharedPreferences = getSharedPreferences(traningCountPreferneces,MODE_PRIVATE);
        edit = sharedPreferences.edit();

        spinnerTraningType = (Spinner) findViewById(R.id.add_training_spinner_training_types);
        etNummberExercises = (EditText) findViewById(R.id.add_training_et_number_exercises);
        spinnerRecord = (Spinner) findViewById(R.id.add_training_spinner_records);
        etRecordKg = (EditText) findViewById(R.id.add_training_et_record_kg);
        etRecordReps = (EditText) findViewById(R.id.add_training_et_record_reps);
// Create an ArrayAdapter using the string array and a default spinnerTraningType layout
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.traning_type, android.R.layout.simple_spinner_item);
// Specify the layout to use when the list of choices appears
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
// Apply the adapter to the spinnerTraningType

        // Create an ArrayAdapter using the string array and a default spinnerTraningType layout
        ArrayAdapter<CharSequence> adapterRecord = ArrayAdapter.createFromResource(this,
                R.array.traning_records, android.R.layout.simple_spinner_item);
// Specify the layout to use when the list of choices appears
        adapterRecord.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
// Apply the adapter to the spinnerTraningType

        spinnerRecord.setAdapter(adapterRecord);
        spinnerTraningType.setAdapter(adapter);
        spinnerRecord.setOnItemSelectedListener(this);
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event)  {
        if (Integer.parseInt(android.os.Build.VERSION.SDK) > 5
                && keyCode == KeyEvent.KEYCODE_BACK
                && event.getRepeatCount() == 0) {
            Intent intent = new Intent(this,MainActivity.class);
            startActivity(intent);
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }



    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        hideLayouts();
    }

    @Override
    protected void onResume() {
        super.onResume();
        hideLayouts();
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }


    public void onClickSave(View view) {
        save();

    }



    private void save() {
        DBPref pref = new DBPref(getApplicationContext());
        String traningType = spinnerTraningType.getSelectedItem().toString();
        String recordExercise = spinnerRecord.getSelectedItem().toString();
        hideLayouts();

        if(etNummberExercises.getText().toString()==null || etNummberExercises.getText().toString().matches("")){
            Toast.makeText(this, "Please Enter Number of Exercises", Toast.LENGTH_SHORT).show();
        }else {
            int numberOfExercises = Integer.parseInt(etNummberExercises.getText().toString());
            if (recordExercise.equals("No Record") || recordExercise.equals("Няма рекорд")) {
                pref.addRecord(traningType, numberOfExercises);
                Toast.makeText(this,"Successful save",Toast.LENGTH_SHORT).show();
                pref.close();
                HistoryActivity.countHistory++;
                Intent intent = new Intent(this,HistoryActivity.class);
                startActivity(intent);
            } else {
                if (etRecordKg.getText().toString()==null || etRecordKg.getText().toString().matches("")) {
                    Toast.makeText(this, "Please Enter KG", Toast.LENGTH_SHORT).show();
                } else {
                    int recordKg = Integer.parseInt(etRecordKg.getText().toString());
                    if (etRecordReps.getText().toString()==null || etRecordReps.getText().toString().matches("")) {
                        Toast.makeText(this, "Please Enter Reps", Toast.LENGTH_SHORT).show();
                    } else {
                        int recordRepsExercises = Integer.parseInt(etNummberExercises.getText().toString());
                        pref.addRecord(traningType, numberOfExercises, recordExercise, recordKg, recordRepsExercises);
                        Toast.makeText(this,"Successful save",Toast.LENGTH_SHORT).show();
                        pref.close();
                        HistoryActivity.countHistory++;
                        Intent intent = new Intent(this,HistoryActivity.class);
                        startActivity(intent);
                    }
                }

            }
        }

    }

    private void hideLayouts(){
        String recordExercise = spinnerRecord.getSelectedItem().toString();
        LinearLayout lyRecordReps = (LinearLayout) findViewById(R.id.add_training_ly_record_reps);
        LinearLayout lyRecordKg = (LinearLayout) findViewById(R.id.add_training_ly_record_kg);
        if(recordExercise.equals("No Record")||recordExercise.equals("Няма рекорд")){
            lyRecordKg.setVisibility(View.INVISIBLE);
            lyRecordReps.setVisibility(View.INVISIBLE);
        }else{
            lyRecordKg.setVisibility(View.VISIBLE);
            lyRecordReps.setVisibility(View.VISIBLE);
        }
    }
}
