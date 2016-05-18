package com.example.aoc.project_arnold.Adapters;


import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.aoc.project_arnold.R;

public class ListViewAdapter extends ArrayAdapter<String> {

    //Declarations
    int[] imgs={};
    String[] exercises={};
    Context c;
    LayoutInflater inflater;

    public ListViewAdapter(Context context, String[] exercises, int[] imgs) {
        super(context, R.layout.exercise_model,exercises);

        this.c=context;
        this.exercises=exercises;
        this.imgs=imgs;
    }

    //Inner class shall hold our views for each row
    public class ViewHolder{
        TextView exercise;
        ImageView img;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if(convertView==null){
            inflater= (LayoutInflater) c.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView=inflater.inflate(R.layout.exercise_model,null);
        }

        //Viewholder object
        final ViewHolder holder = new ViewHolder();

        //Initialise our view
        holder.exercise= (TextView) convertView.findViewById(R.id.txt_exercise);
        holder.img = (ImageView) convertView.findViewById(R.id.img_exercise);

        //Assign data
        holder.exercise.setText(exercises[position]);
        holder.img.setImageResource(imgs[position]);

        return convertView;
    }
}
