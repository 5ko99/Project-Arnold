package com.example.aoc.project_arnold.Activities;

import android.app.Activity;
import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;
import android.widget.TextView;

import com.example.aoc.project_arnold.Adapters.ActivitySummaryAdapter;
import com.example.aoc.project_arnold.Database.DBPref;
import com.example.aoc.project_arnold.Objects.ActivitySummary;
import com.example.aoc.project_arnold.R;

import java.util.ArrayList;

//TODO: Recreate database with image fild that stores pphotos from users
//TODO: If you not have any records in db to not crash
public class HistoryActivity extends Activity {
    ImageView imageView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_history);

        ListView listView = (ListView) findViewById(R.id.list_history);
        DBPref pref = new DBPref(this);
        Cursor c = pref.getVals();
        startManagingCursor(c);
        String[] from = new String[]{"date","type"};
        int[] to = new int[]{R.id.history_summary_tv_date,R.id.history_summary_tv_type};
        SimpleCursorAdapter adapter = new SimpleCursorAdapter(this,R.layout.history_summary,c,from,to);
        listView.setAdapter(adapter);
        imageView = (ImageView) findViewById(R.id.history_summary_img);


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
    }


}
