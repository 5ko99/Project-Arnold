package com.example.aoc.project_arnold.Adapters;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.aoc.project_arnold.Objects.Record;
import com.example.aoc.project_arnold.R;

import java.util.List;

public class RecordsAdapter extends ArrayAdapter<Record> {
    //Declarations
    private Context c;
    private int layoutId;
    private LayoutInflater inflater;
    private List<Record> data;


    public RecordsAdapter(Context context, int layoutResourceId, List<Record> objects) {
        super(context,layoutResourceId,objects);
        this.c=context;
        this.layoutId=layoutResourceId;
        this.data=objects;
    }

    //Inner class shall hold our views for each row
    public class ViewHolder{
        TextView exercise;
        ImageView img;
        TextView reps;
        TextView kg;
        TextView date;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = ((Activity)c).getLayoutInflater();
        View row = inflater.inflate(layoutId,parent,false);

        TextView  tv_exercise = (TextView) row.findViewById(R.id.tv_records_exercise);
        ImageView  img = (ImageView) row.findViewById(R.id.img_records);
        TextView tv_reps = (TextView) row.findViewById(R.id.tv_records_reps);
        TextView tv_kg = (TextView) row.findViewById(R.id.tv_records_kg);
        TextView  tv_date = (TextView) row.findViewById(R.id.tv_records_date);

        tv_exercise.setText(data.get(position).getExercises());
        img.setImageResource(data.get(position).getImgs());
        tv_reps.setText(data.get(position).getReps());
        tv_kg.setText(data.get(position).getKg());
        tv_date.setText(data.get(position).getDate());


        return row;
    }
}
