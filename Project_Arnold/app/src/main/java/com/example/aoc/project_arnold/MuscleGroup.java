package com.example.aoc.project_arnold;

import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;

public class MuscleGroup extends AppCompatActivity {
    public static final String KEY = MainActivity.KEY ;
    private static final String TAG = "Muscule Group";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_muscle_group);

        Bundle bundle = getIntent().getExtras();
        int musculeGroupN= bundle.getInt(KEY); // Getting number of muscule group from intent
        ImageView image = getImage(musculeGroupN); //Set image to img_main from R.drawable depends on musculeGroupN
        setHeader(musculeGroupN); //Set header from R.string depends on musculeGroupN
    }

    private ImageView getImage(int musculeGroupN) { //Set image depends musculeGroupNimage
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

    private void setHeader(int musculeGroupN){ //Set text to TextView Header and set layout header

        switch (musculeGroupN){
            case 0:  setTitle(R.string.Bedra); break;
            case 1:  setTitle(R.string.Biceps); break;
            case 2:  setTitle(R.string.Gryb); break;
            case 3:  setTitle(R.string.Gyrdi); break;
            case 4:  setTitle(R.string.Korem); break;
            case 5:  setTitle(R.string.Prasec); break;
            case 6:  setTitle(R.string.Predmishnici); break;
            case 7:  setTitle(R.string.Ramo); break;
            case 8:  setTitle(R.string.Sedalishte); break;
            case 9:  setTitle(R.string.Triceps); break;
        }

    }



    public void fullScreen() {

        // BEGIN_INCLUDE (get_current_ui_flags)
        // The UI options currently enabled are represented by a bitfield.
        // getSystemUiVisibility() gives us that bitfield.
        int uiOptions = getWindow().getDecorView().getSystemUiVisibility();
        int newUiOptions = uiOptions;
        // END_INCLUDE (get_current_ui_flags)
        // BEGIN_INCLUDE (toggle_ui_flags)
        boolean isImmersiveModeEnabled =
                ((uiOptions | View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY) == uiOptions);
        if (isImmersiveModeEnabled) {
            Log.i(TAG, "Turning immersive mode mode off. ");
        } else {
            Log.i(TAG, "Turning immersive mode mode on.");
        }

        // Navigation bar hiding:  Backwards compatible to ICS.
        if (Build.VERSION.SDK_INT >= 14) {
            newUiOptions ^= View.SYSTEM_UI_FLAG_HIDE_NAVIGATION;
        }

        // Status bar hiding: Backwards compatible to Jellybean
        if (Build.VERSION.SDK_INT >= 16) {
            newUiOptions ^= View.SYSTEM_UI_FLAG_FULLSCREEN;
        }

        // Immersive mode: Backward compatible to KitKat.
        // Note that this flag doesn't do anything by itself, it only augments the behavior
        // of HIDE_NAVIGATION and FLAG_FULLSCREEN.  For the purposes of this sample
        // all three flags are being toggled together.
        // Note that there are two immersive mode UI flags, one of which is referred to as "sticky".
        // Sticky immersive mode differs in that it makes the navigation and status bars
        // semi-transparent, and the UI flag does not get cleared when the user interacts with
        // the screen.
        if (Build.VERSION.SDK_INT >= 18) {
            newUiOptions ^= View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY;
        }

        getWindow().getDecorView().setSystemUiVisibility(newUiOptions);
        //END_INCLUDE (set_ui_flags)
    }



}
