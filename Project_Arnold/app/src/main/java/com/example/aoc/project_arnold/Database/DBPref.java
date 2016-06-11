package com.example.aoc.project_arnold.Database;

import android.content.ContentValues;
import android.content.Context;
import android.content.SharedPreferences;
import android.database.Cursor;

import com.example.aoc.project_arnold.Activities.AddTrainingActivity;
import com.example.aoc.project_arnold.Activities.MainActivity;

import java.text.SimpleDateFormat;
import java.util.Date;


public class DBPref extends DBHelper {

    public DBPref(Context context) {
        super(context);
    }

    public void addRecord(String type, int n, String record_exercise, int record_kg, int record_reps ){
        ContentValues cv = new ContentValues(); //Правим си обек с който да лагаме в базата
        cv.put("type", type);// В полето val в базата слагаме съответната стойност
        cv.put("n", n);
        cv.put("record_exercise",record_exercise);
        cv.put("record_kg",record_kg);
        cv.put("record_reps",record_reps);
        String date = new SimpleDateFormat("dd-MM-yyyy").format(new Date());
        cv.put("date",date);
        this.db.insert(DBHelper.tableActivity,null,cv); //Името на базата  обекта който държи стойностите и изпълняваме записа

        //On addRecord activity +1 of overall activity count
        int count = AddTrainingActivity.sharedPreferences.getInt(AddTrainingActivity.traningCountInt,0);
        count++;
        AddTrainingActivity.edit.putInt(AddTrainingActivity.traningCountInt,count);
        AddTrainingActivity.edit.commit();
        MainActivity.count++;
    }


    public void addRecord(String type, int n){
        ContentValues cv = new ContentValues(); //Правим си обек с който да лагаме в базата
        cv.put("type", type);// В полето val в базата слагаме съответната стойност
        cv.put("n", n);
        String date = new SimpleDateFormat("dd-MM-yyyy").format(new Date());
        cv.put("date",date);
        this.db.insert(DBHelper.tableActivity,null,cv); //Името на базата  обекта който държи стойностите и изпълняваме записа

        //On addRecord activity +1 of overall activity count
        int count = AddTrainingActivity.sharedPreferences.getInt(AddTrainingActivity.traningCountInt,0);
        count++;
        AddTrainingActivity.edit.putInt(AddTrainingActivity.traningCountInt,count);
        AddTrainingActivity.edit.commit();
        MainActivity.count++;
    }

    public Cursor getVals(){
        return this.db.query(DBHelper.tableActivity,new String[]{"type","n","record_exercise","record_kg","_id","date"},null,null,null,null,"_id DESC"); //Изпълняваме sql то си пише кое е where, order by и т.н.
    }

    public Cursor getVals(String type,String date){
        return this.db.query(DBHelper.tableActivity,new String[]{"record_exercise","record_kg","record_reps"},"type=? and date=? ",new String[]{type,date},null,null,null); //Изпълняваме sql то си пише кое е where, order by и т.н.
    }

    public boolean delete(int id)
    {
        int count = AddTrainingActivity.sharedPreferences.getInt(AddTrainingActivity.traningCountInt,0);
        count--;
        AddTrainingActivity.edit.putInt(AddTrainingActivity.traningCountInt,count);
        AddTrainingActivity.edit.commit();
        MainActivity.count--;

        String id_string = Integer.toString(id);
        return db.delete(DBHelper.tableActivity,"_id=?",new String[]{id_string}) > 0;
    }
}