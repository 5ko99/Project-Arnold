package com.example.aoc.project_arnold.Activities;

import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.ContextMenu;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;
import android.widget.TextView;
import android.widget.Toast;

import com.example.aoc.project_arnold.Database.DBPref;
import com.example.aoc.project_arnold.R;

import java.util.ArrayList;

//TODO: Recreate database with image fild that stores pphotos from users
public class HistoryActivity extends AppCompatActivity {
    ImageView imageView;
    public static int countHistory;
    ListView listView;
    private Cursor c;
    private DBPref pref;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_history);


        listView = (ListView) findViewById(R.id.list_history);
        //Register listView for long click
        registerForContextMenu(listView);
        setViewFromDb();

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> arg0, View arg1, int position, long arg3) {
                //TODO: Create and start new activity on click
                Intent intent = new Intent(HistoryActivity.this, TraningInfoActivity.class);
                intent.putExtra("position", position);
                TextView textView = (TextView) arg1.findViewById(R.id.history_summary_tv_type);
                String type = textView.getText().toString();
                intent.putExtra("type", type);
                textView = (TextView) arg1.findViewById(R.id.history_summary_tv_date);
                String date = textView.getText().toString();
                intent.putExtra("date", date);
                startActivity(intent);
            }
        });


    }

    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_history, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {

            case android.R.id.home:
                onBackPressed();
                return true;

            case R.id.action_records:
                Intent intent = new Intent(this,RecordsActivity.class);
                startActivity(intent);
                return true;
        }

        return super.onOptionsItemSelected(item);
    }


    private void setViewFromDb() {
        countHistory = MainActivity.count;
        if (countHistory == 0) {
            Toast.makeText(this, R.string.toast_history_null, Toast.LENGTH_LONG).show();
            Intent intent = new Intent(this, AddTrainingActivity.class);
            startActivity(intent);
        } else {
            countHistory++;
            DBPref pref = new DBPref(this);
            Cursor mC = pref.getVals();
            startManagingCursor(mC);
            String[] from = new String[]{"date", "type", "n", "_id"};
            int[] to = new int[]{R.id.history_summary_tv_date, R.id.history_summary_tv_type, R.id.history_summary_n, R.id.history_summary_id};
            SimpleCursorAdapter adapter = new SimpleCursorAdapter(this, R.layout.history_summary, mC, from, to);
            listView.setAdapter(adapter);
        }
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (Integer.parseInt(android.os.Build.VERSION.SDK) > 5
                && keyCode == KeyEvent.KEYCODE_BACK
                && event.getRepeatCount() == 0) {
            Intent intent = new Intent(this, MainActivity.class);
            startActivity(intent);
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }

    private void show() {
        pref = new DBPref(getApplicationContext());
        c = pref.getVals();
        ArrayList<String> arrayList = new ArrayList<String>();
        if (c.moveToFirst()) {  // Слагаме курсора от начало ако няма записи това ще даде false ако има записи ще даде true
            do { // Обхождаме курсора
                arrayList.add(getString(c.getColumnIndex("type")));
            } while (c.moveToNext()); // Докато се премести на следващот

            c.close(); //Затваряме курсора
            pref.close(); // Затвяряме базата данни
        }
    }


    /**
     * MENU
     */

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);
        if (v.getId() == R.id.list_history) {
            MenuInflater inflater = getMenuInflater();
            inflater.inflate(R.menu.menu_hisory_on_long_press, menu);
        }
    }

    @Override
    public boolean onContextItemSelected(MenuItem item) {
        AdapterView.AdapterContextMenuInfo info = (AdapterView.AdapterContextMenuInfo) item.getMenuInfo();
        switch (item.getItemId()) {
           //case R.id.edit:
                // TODO: edit traning
            case R.id.delete:
                new AlertDialog.Builder(this).setIcon(android.R.drawable.ic_dialog_alert).setTitle(getString(R.string.deleteword))
                        .setMessage(getString(R.string.deletemsg))
                        .setPositiveButton(getString(R.string.yes), new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                deleteTraningFromDB();
                            }
                        }).setNegativeButton(getString(R.string.no), null).show();

            default:
                return super.onContextItemSelected(item);
        }
    }

    private void deleteTraningFromDB(){
        TextView tvId = (TextView) findViewById(R.id.history_summary_id);
        int id = Integer.parseInt(tvId.getText().toString());
        DBPref pref = new DBPref(HistoryActivity.this);
        pref.delete(id);
        setViewFromDb();
        noTraningsCheck();
    }


    private void noTraningsCheck() {
        countHistory = MainActivity.count;
        if (countHistory == 0) {
            Toast.makeText(this, R.string.toast_history_null, Toast.LENGTH_LONG).show();
            Intent intent = new Intent(this, MainActivity.class);
            startActivity(intent);
        }
    }


}