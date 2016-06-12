package com.example.aoc.project_arnold.Activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;

import com.example.aoc.project_arnold.Adapters.RecordsAdapter;
import com.example.aoc.project_arnold.Objects.Record;
import com.example.aoc.project_arnold.R;

import java.util.ArrayList;

public class RecordsActivity extends AppCompatActivity {
    private  ListView lv;
    private  Record record = new Record();
    private ArrayList<Record> records =new ArrayList<Record>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_records);

        record.setExercises("Lejanka");
        record.setImgs(R.drawable.gyrdi_lejanka);
        record.setKg("100");
        record.setReps("10");
        record.setDate("5.5.2015");

        records.add(record);
        record=new Record();
        record.setExercises("Klek");
        record.setImgs(R.drawable.sedalishte_klek_shtanga);
        record.setKg("120");
        record.setReps("8");
        record.setDate("4.3.2015");
        records.add(record);

       //Set records to list view for exercises
        lv= (ListView) findViewById(R.id.lv_records);
        //Create custom adapter
        RecordsAdapter recordsAdapter = new RecordsAdapter(this,R.layout.record_model, records);
        lv.setAdapter(recordsAdapter); // set adapter to lv

    }
}
