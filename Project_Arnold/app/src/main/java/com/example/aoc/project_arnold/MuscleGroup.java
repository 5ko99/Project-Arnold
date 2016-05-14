package com.example.aoc.project_arnold;

import android.graphics.drawable.Drawable;
import android.media.Image;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;

public class MuscleGroup extends AppCompatActivity {
    public static final String KEY = MainActivity.KEY ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_muscle_group);

        Bundle bundle = getIntent().getExtras();
        int musculeGroupN= bundle.getInt(KEY); // Getting number of muscule group from intent
        ImageView image = getImage(musculeGroupN); //Set image to img_main from R.drawable depends on musculeGroupN
    }

    private ImageView getImage(int musculeGroupN) { //Set image depends musculeGroupN
        ImageView image = (ImageView) findViewById(R.id.img_main);
        switch (musculeGroupN){
            case 0: image.setImageResource(R.drawable.bedra); break;
            case 1: image.setImageResource(R.drawable.biceps); break;
            case 2: image.setImageResource(R.drawable.gryb); break;
            case 3: image.setImageResource(R.drawable.gyrdi); break;
            case 4: image.setImageResource(R.drawable.korem); break;
            case 5: image.setImageResource(R.drawable.prasec); break;
            case 6: image.setImageResource(R.drawable.predmishnica); break;
            case 7: image.setImageResource(R.drawable.ramo); break;
            case 8: image.setImageResource(R.drawable.sedalishte); break;
            case 9: image.setImageResource(R.drawable.triceps); break;
        }

        return image;
    }

    public static void fullScreen(){

    }
}
