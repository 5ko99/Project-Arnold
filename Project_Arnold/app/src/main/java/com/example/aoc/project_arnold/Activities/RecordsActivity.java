package com.example.aoc.project_arnold.Activities;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ListView;

import com.example.aoc.project_arnold.Adapters.RecordsAdapter;
import com.example.aoc.project_arnold.Objects.Record;
import com.example.aoc.project_arnold.R;

import java.util.ArrayList;

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



    }

    //TO delete record after delete exercise
    private void fillData(){
        String[] recordsExercises = getResources().getStringArray(R.array.traning_records);
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



}
