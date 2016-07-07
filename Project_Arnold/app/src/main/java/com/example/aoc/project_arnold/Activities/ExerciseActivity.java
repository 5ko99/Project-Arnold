package com.example.aoc.project_arnold.Activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.util.Linkify;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

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
    private TextView tv_text;
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
        tv_text =(TextView) findViewById(R.id.tv_activity_exercise_text);
        ImageView exerciseImage =(ImageView) findViewById(R.id.img_exerciseActivity_exercise);//Find image view
        exerciseImage.setImageResource(img); // set image
        setTitle(exercise_text); //set activity title

        //TODO: Remove test of tv_text
        String[] text =  setText(musculeGroupN);
        if(exerciseN!=12) tv_text.setAutoLinkMask(Linkify.EMAIL_ADDRESSES);
        else tv_text.setAutoLinkMask(Linkify.ALL);
        tv_text.setText(text[exerciseN]);


    }

    private String[] setText(int musculeGroupN) {
        String[] arr = new String[20];
        switch (musculeGroupN){
            case 0 : arr=getResources().getStringArray(R.array.exercises_bedra_text); break;
            case 1 : arr=getResources().getStringArray(R.array.exercises_biceps_text); break;
            case 2 : arr=getResources().getStringArray(R.array.exercises_gryb_text); break;
            case 3 : arr=getResources().getStringArray(R.array.exercises_gyrdi_text); break;
            case 4 : arr=getResources().getStringArray(R.array.exercises_korem_text); break;
            case 5 : arr=getResources().getStringArray(R.array.exercises_prasec_text); break;
            case 6 : arr=getResources().getStringArray(R.array.exercises_predmishnici_text); break;
            case 7 : arr=getResources().getStringArray(R.array.exercises_ramo_text); break;
            case 8 : arr=getResources().getStringArray(R.array.exercises_sedalishte_text); break;
            case 9 : arr=getResources().getStringArray(R.array.exercises_triceps_text); break;
            default: break;
        }

        return arr;
    }

    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {

            case android.R.id.home:
                onBackPressed();
                return true;
        }

        return super.onOptionsItemSelected(item);
    }

}
