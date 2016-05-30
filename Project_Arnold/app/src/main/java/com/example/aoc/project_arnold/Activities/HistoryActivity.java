package com.example.aoc.project_arnold.Activities;

import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;
import android.widget.TextView;
import android.widget.Toast;

import com.example.aoc.project_arnold.Adapters.ActivitySummaryAdapter;
import com.example.aoc.project_arnold.Database.DBPref;
import com.example.aoc.project_arnold.Objects.ActivitySummary;
import com.example.aoc.project_arnold.R;

import java.util.ArrayList;
import java.util.Collections;

//TODO: Recreate database with image fild that stores pphotos from users
//TODO: If you not have any records in db to not crash
public class HistoryActivity extends Activity {
    ImageView imageView;
    public static int countHistory;
    ListView listView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_history);
        listView = (ListView) findViewById(R.id.list_history);

        countHistory=MainActivity.count;

        if(countHistory==0){
            Toast.makeText(this, R.string.toast_history_null,Toast.LENGTH_LONG).show();
            Intent intent = new Intent(this,AddTrainingActivity.class);
            startActivity(intent);
        }else {
            countHistory++;
            DBPref pref = new DBPref(this);
            Cursor c = pref.getVals();
            startManagingCursor(c);
            String[] from = new String[]{"date", "type"};
            int[] to = new int[]{R.id.history_summary_tv_date, R.id.history_summary_tv_type};
            SimpleCursorAdapter adapter = new SimpleCursorAdapter(this, R.layout.history_summary, c, from, to);
            listView.setAdapter(adapter);
            imageView = (ImageView) findViewById(R.id.history_summary_img);

        }



        /*ArrayList<ActivitySummary> activitySummaries = new ArrayList<ActivitySummary>();
        for(int i=1;i<10;i++){
            ActivitySummary activitySummary = new ActivitySummary();
            activitySummary.setTraningType("Kraka"+String.valueOf(i));
            activitySummary.setDate("26.06.199"+String.valueOf(i));
            activitySummary.setImage(R.drawable.gryb);
            activitySummaries.add(activitySummary);
        }
        ListView listView = (ListView) findViewById(R.id.list_history);
        ActivitySummaryAdapter adapter = new ActivitySummaryAdapter(this,R.layout.history_summary,activitySummaries);
        listView.setAdapter(adapter);*/

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener()
        {
            @Override
            public void onItemClick(AdapterView<?> arg0, View arg1, int position, long arg3)
            {
                Toast.makeText(HistoryActivity.this, "" + position, Toast.LENGTH_SHORT).show();
            }
        });
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






}
