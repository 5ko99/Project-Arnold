package com.example.aoc.project_arnold.Activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;

import com.example.aoc.project_arnold.R;

public class ExerciseActivity extends AppCompatActivity {
    public static final String KEY = MuscleGroupsActivity.KEY ;
    public static final String  KEYEXERCISE = MuscleGroupExercisesActivity.KEYEXERCISE;
    public int musculeGroupN; // int that say number of muscule
    public int exerciseN; //int that say number of exerise
    public static final String KEYIMAGE = MuscleGroupExercisesActivity.KEYIMAGE;
    public static final String KEYEXERCISE_TEXT = MuscleGroupExercisesActivity.KEYEXERCISE_TEXT;
    private int img;
    private String exercise_text;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_exercise);
        //Get bundle from intent
        Bundle bundle = getIntent().getExtras();
        musculeGroupN= bundle.getInt(KEY);
        exerciseN = bundle.getInt(KEYEXERCISE);
        img=bundle.getInt(KEYIMAGE);
        exercise_text =bundle.getString(KEYEXERCISE_TEXT);

        ImageView exerciseImage =(ImageView) findViewById(R.id.img_exerciseActivity_exercise);//Find image view
        exerciseImage.setImageResource(img); // set image
        setTitle(exercise_text); //set activity title


    }

}
