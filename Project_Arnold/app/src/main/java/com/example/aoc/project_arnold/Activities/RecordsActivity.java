package com.example.aoc.project_arnold.Activities;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.ContextMenu;
import android.view.KeyEvent;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.aoc.project_arnold.Adapters.RecordsAdapter;
import com.example.aoc.project_arnold.Database.DBPref;
import com.example.aoc.project_arnold.Objects.Record;
import com.example.aoc.project_arnold.R;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Locale;

public class RecordsActivity extends AppCompatActivity {
    private ListView lv;
    private ArrayList<Record> records = new ArrayList<Record>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_records);

        fillData();
        //Set records to list view for exercises
        lv = (ListView) findViewById(R.id.lv_records);
        //Create custom adapter
        RecordsAdapter recordsAdapter = new RecordsAdapter(this, R.layout.record_model, records);
        lv.setAdapter(recordsAdapter); // set adapter to lv

      /*  //Register listView for long click
        registerForContextMenu(lv);
*/
        lv.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> arg0, View arg1, int pos, long id) {
                final String exerciseKey;
                Log.v("long clicked","pos: " + pos);
                switch (pos){
                    case 0: exerciseKey ="Barbell Bench Press"; break;
                    case 1: exerciseKey ="Barbell Squat"; break;
                    case 2: exerciseKey ="Dead Lift"; break;
                    case 3: exerciseKey ="Barbell Curl"; break;
                    case 4: exerciseKey ="Preacher Curl"; break;
                    case 5: exerciseKey ="Close Grip Bench Press"; break;
                    case 6: exerciseKey ="FrenchTricepsExtensions"; break;
                    case 7: exerciseKey ="Leg Press for Legs"; break;
                    case 8: exerciseKey ="Basic Dumbbell Curl"; break;
                    case 9: exerciseKey ="Barbell Bent- Over Row"; break;
                    default: exerciseKey="ERROR: Not Valid pos"; break;
                }

                new AlertDialog.Builder(RecordsActivity.this).setIcon(android.R.drawable.ic_dialog_alert).setTitle(getString(R.string.deleteword))
                        .setMessage(getString(R.string.deletemsg))
                        .setPositiveButton(getString(R.string.yes), new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                deleteTraningFromDB(exerciseKey);
                            }
                        }).setNegativeButton(getString(R.string.no), null).show();

                return true;
            }
        });


    }

    //TO delete record after delete exercise
    private void fillData(){
        String[] recordsExercises = getResources().getStringArray(R.array.records);
        for(int i=1;i<recordsExercises.length;i++){
            Record temp=new Record();
            temp.setExercises(recordsExercises[i]);
            switch (i){
                case 1: temp.setImgs(R.drawable.gyrdi_lejanka); break;
                case 2: temp.setImgs(R.drawable.bedra_klek_shtanga); break;
                case 3: temp.setImgs(R.drawable.gryb_myrtva_tqga); break;
                case 4: temp.setImgs(R.drawable.biceps_shtanga); break;
                case 5: temp.setImgs(R.drawable.biceps_skotovo); break;
                case 6: temp.setImgs(R.drawable.triceps_lejanka); break;
                //To change to triceps_frensko
                case 7: temp.setImgs(R.drawable.triceps_frensko); break;
                case 8: temp.setImgs(R.drawable.bedra_leg_presa); break;
                case 9: temp.setImgs(R.drawable.biceps_sgyvane_dymbeli); break;
                case 10: temp.setImgs(R.drawable.gryb_chukcheta); break;
            }
            SharedPreferences recordSharedPref = this.getSharedPreferences(AddTrainingActivity.recordPref,MODE_PRIVATE);
            if(i==7){
                int recordKG = recordSharedPref.getInt("FrenchTricepsExtensions",0);
                temp.setKg(Integer.toString(recordKG));
                int recordReps = recordSharedPref.getInt("FrenchTricepsExtensions"+"reps",0);
                temp.setReps(Integer.toString(recordReps));
                String date = recordSharedPref.getString(recordsExercises[i]+"date",getString(R.string.no_record_msg));
                temp.setDate(date);
                records.add(temp);
            }else{
                int recordKG = recordSharedPref.getInt(recordsExercises[i],0);
                temp.setKg(Integer.toString(recordKG));
                int recordReps = recordSharedPref.getInt(recordsExercises[i]+"reps",0);
                temp.setReps(Integer.toString(recordReps));
                String date = recordSharedPref.getString(recordsExercises[i]+"date",getString(R.string.no_record_msg));
                temp.setDate(date);
                records.add(temp);
            }

        }
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (Integer.parseInt(android.os.Build.VERSION.SDK) > 5
                && keyCode == KeyEvent.KEYCODE_BACK
                && event.getRepeatCount() == 0) {
            Intent intent = new Intent(this, HistoryActivity.class);
            startActivity(intent);
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }


    private void deleteTraningFromDB(String exerciseKey){
        String exercise = exerciseKey;
        String language = Locale.getDefault().getDisplayLanguage();
        if(language.equals("български")) {
            // do your stuff for bulgarian
            exercise = toEnglish(exercise);
        }
        SharedPreferences recordSharedPref = this.getSharedPreferences(AddTrainingActivity.recordPref, Context.MODE_PRIVATE);
        SharedPreferences.Editor recordEdit = recordSharedPref.edit();
        int recordKgPref = 0;
            recordEdit.putInt(exercise,recordKgPref);
            recordEdit.putInt(exercise+"reps",0);
            String date = getString(R.string.no_record_msg);
            recordEdit.putString(exercise+"date",date);
            recordEdit.commit();

        //Calling on create to reload new records
       Intent intent = new Intent(this,RecordsActivity.class);
        startActivity(intent);

    }


    private String toEnglish(String exercise) {
        String exerciseEnglish="";
        String[] records = getResources().getStringArray(R.array.records);
        String[] training_records = getResources().getStringArray(R.array.traning_records);
        for(int i=0;i<records.length;i++){
            if(training_records[i].equals(exercise)){
                exerciseEnglish=records[i];
                break;
            }
        }

        return exerciseEnglish;
    }


}
