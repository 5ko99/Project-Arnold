package com.example.aoc.project_arnold;

import android.content.Context;
import android.content.res.Resources;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;

public class ImageAdapter extends BaseAdapter {
    private static final  int imgView_X = 465;
    private static final  int imgView_Y = 187;

    private Context mContext;

    public ImageAdapter(Context c) {
        mContext = c;
    }

    public int getCount() {
        return mThumbIds.length;
    }

    public Object getItem(int position) {
        return null;
    }

    public long getItemId(int position) {
        return 0;
    }

    // create a new ImageView for each item referenced by the Adapter
    public View getView(int position, View convertView, ViewGroup parent) {
        ImageView imageView;
        if (convertView == null) {
            // if it's not recycled, initialize some attributes
            imageView = new ImageView(mContext);
            imageView.setLayoutParams(new GridView.LayoutParams(imgView_X, imgView_Y));
            imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
            imageView.setPadding(8, 8, 8, 8);
        } else {
            imageView = (ImageView) convertView;
        }

        imageView.setImageResource(mThumbIds[position]);



        return imageView;
    }

    // references to our images
    private Integer[] mThumbIds = {
            R.drawable.bedra, R.drawable.biceps,
            R.drawable.gryb, R.drawable.gyrdi,
            R.drawable.korem, R.drawable.prasec,
            R.drawable.predmishnica, R.drawable.ramo,
            R.drawable.sedalishte, R.drawable.triceps

    };

    /*private String[] mThumbtext = {
            Resources.getSystem().getString(R.string.Bedra),
            Resources.getSystem().getString(R.string.Biceps),
            Resources.getSystem().getString(R.string.Gryb),
            Resources.getSystem().getString(R.string.Gyrdi),
            Resources.getSystem().getString(R.string.Korem),
            Resources.getSystem().getString(R.string.Prasec),
            Resources.getSystem().getString(R.string.Predmishnici),
            Resources.getSystem().getString(R.string.Ramo),
            Resources.getSystem().getString(R.string.Sedalishte),
            Resources.getSystem().getString(R.string.Triceps)
    };*/
}