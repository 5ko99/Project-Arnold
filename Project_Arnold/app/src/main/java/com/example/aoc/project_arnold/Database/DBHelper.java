package com.example.aoc.project_arnold.Database;

import android.content.Context;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import com.example.aoc.project_arnold.Activities.AddTrainingActivity;
import com.example.aoc.project_arnold.Activities.MainActivity;

import java.io.IOException;

public class DBHelper extends SQLiteOpenHelper {
    static final String DB_NAME="Exercises";
    static final int DB_CURRENT_VERSION = 1;
    protected SQLiteDatabase db;
    protected static String tableActivity = "activity";
    public DBHelper(Context context) {
        super(context, DB_NAME, null, DB_CURRENT_VERSION);
        open();
    }

    //Когато се опитаме да използваме базата но тя не е  създадена Инициализация на базата данни
    @Override
    public void onCreate(SQLiteDatabase db) {
        Log.d("D1","Before Create DB");
        StringBuilder sb = new StringBuilder();
        try {
            do{
                sb.append(AddTrainingActivity.DB_CREATE_TXT.readLine().toString());
            }while (AddTrainingActivity.DB_CREATE_TXT.readLine()!=null);
            AddTrainingActivity.DB_CREATE_TXT.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
        Log.d("D1",sb.toString());
        db.execSQL(sb.toString());//Изпълняваме sql  за създаване на базата данни заявката е изнесена в ресурси
        Log.d("D1","Created DB");
        AddTrainingActivity.edit.putInt(AddTrainingActivity.traningCountInt,0);
        AddTrainingActivity.edit.commit();
    }

    //Когато базата дани вече е създадена и има нова версия примерно да добавим нови полета
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    public void open() throws SQLException{ // Метод за отваряне на базата данни
        db=getWritableDatabase();
    }

    public void close(){ //Метод за затваряне на базата данни
        db.close();
    }
}

