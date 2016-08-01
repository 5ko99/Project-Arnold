package com.example.aoc.project_arnold.Activities;

import android.annotation.TargetApi;
import android.content.SharedPreferences;
import android.os.Build;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.aoc.project_arnold.R;

public class SettingsActivity extends AppCompatActivity {
    Spinner spinnerColor;
    String colorString;
    int primaryColor;
    View exampleBox;
    private SharedPreferences sharedPreferencesColor;
    String colorSPKey = MainActivity.colorSPKey;

    @TargetApi(Build.VERSION_CODES.M)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);

        //Initialise spinner and example box
        spinnerColor = (Spinner) findViewById(R.id.settings_spinner_colors);
        ArrayAdapter<CharSequence> adapterColor = ArrayAdapter.createFromResource(this, R.array.colorApp, android.R.layout.simple_spinner_item);
        spinnerColor.setAdapter(adapterColor);
        exampleBox= findViewById(R.id.settings_example_box);
        exampleBox.invalidate();

        //Setting default  value to primaryColor
        sharedPreferencesColor = MainActivity.sharedPreferencesPrimaryColors;

        //On item selected catch event
        spinnerColor.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @TargetApi(Build.VERSION_CODES.M)
            @Override
            public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id) {
                //Get string for selected color from spinner
                colorString = spinnerColor.getSelectedItem().toString();

                //Setting Color
                primaryColor = setColor(colorString);

                //Setting color to example box
                exampleBox.setBackgroundColor(getColor(primaryColor));
                exampleBox.invalidate();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parentView) {
                // your code here
            }

        });





    }



    private int setColor(String colorString) {
        int color=0;
        switch(colorString){
            case "Red" : color=android.R.color.holo_red_light; break;
            case "Blue" : color=android.R.color.holo_blue_light; break;
            case "Purple" : color=android.R.color.holo_purple; break;
            case "Orange" : color=android.R.color.holo_orange_dark; break;
            case "Green" : color=android.R.color.holo_green_light; break;
            case "Light Orange" : color=android.R.color.holo_orange_light; break;
            default: color=android.R.color.holo_red_light; break;
        }
        return color;
    }

    public void onClickSave(View view) {
        //Set selected color in sharedPreferencesColor
        SharedPreferences.Editor editor = sharedPreferencesColor.edit();
        editor.putInt(colorSPKey,primaryColor);
        editor.putString(colorSPKey,colorString);
        editor.commit();
        Toast.makeText(SettingsActivity.this, R.string.colorChange,Toast.LENGTH_SHORT).show();
    }
}
