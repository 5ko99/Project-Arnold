package com.example.aoc.project_arnold.Adapters;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.aoc.project_arnold.Objects.ActivitySummary;
import com.example.aoc.project_arnold.R;

import java.util.List;

public class ActivitySummaryAdapter extends ArrayAdapter<ActivitySummary> {
    private Context context;
    private int layoutId;
    private List<ActivitySummary> data;

    public ActivitySummaryAdapter(Context context, int textViewResourceId, List<ActivitySummary> objects) {
        super(context, textViewResourceId, objects);
        this.context = context;
        layoutId=textViewResourceId;
        data=objects;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = ((Activity)context).getLayoutInflater();
        View row = inflater.inflate(layoutId,parent,false);

        TextView  tv_type = (TextView) row.findViewById(R.id.history_summary_tv_type);
        TextView  tv_date = (TextView) row.findViewById(R.id.history_summary_tv_date);

       tv_type.setText(data.get(position).getTraningType());
       tv_date.setText(data.get(position).getDate());

        return row;
    }
}
