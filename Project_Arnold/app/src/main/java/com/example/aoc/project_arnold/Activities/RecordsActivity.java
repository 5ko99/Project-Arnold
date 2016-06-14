package com.example.aoc.project_arnold.Activities;

import android.content.res.AssetManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.util.Xml;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.aoc.project_arnold.Adapters.RecordsAdapter;
import com.example.aoc.project_arnold.Objects.Record;
import com.example.aoc.project_arnold.R;

import org.w3c.dom.Document;
import org.xml.sax.InputSource;
import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

public class RecordsActivity extends AppCompatActivity {
    private ListView lv;
    private Record record = new Record();
    private ArrayList<Record> records = new ArrayList<Record>();

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


        //Set records to list view for exercises
        lv = (ListView) findViewById(R.id.lv_records);
        //Create custom adapter
        RecordsAdapter recordsAdapter = new RecordsAdapter(this, R.layout.record_model, records);
        lv.setAdapter(recordsAdapter); // set adapter to lv



    }


    private void fillData(){
        Record temp = new Record();
        String[] recordsExercises = getResources().getStringArray(R.array.traning_records);
        for(int i=1;i<recordsExercises.length;i++){
            temp.setExercises(recordsExercises[0]);
            switch (i){
                case 1: temp.setImgs(R.drawable.gyrdi_lejanka); break;
                case 2: temp.setImgs(R.drawable.bedra_klek_shtanga); break;
                case 3: temp.setImgs(R.drawable.gryb_myrtva_tqga); break;
                case 4: temp.setImgs(R.drawable.biceps_shtanga); break;
                case 5: temp.setImgs(R.drawable.biceps_skotovo); break;
                case 6: temp.setImgs(R.drawable.triceps_lejanka); break;
                case 7: temp.setImgs(R.drawable.triceps_frensko); break;
                case 8: temp.setImgs(R.drawable.bedra_leg_presa); break;
                case 9: temp.setImgs(R.drawable.biceps_sgyvane_dymbeli); break;
                case 10: temp.setImgs(R.drawable.gryb_chukcheta); break;
            }
        }
    }



}
