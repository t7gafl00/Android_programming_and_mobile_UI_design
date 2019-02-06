package com.example.myapplicationlab_5_1;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;

import model.WorkoutPart;
import model.WorkoutPartBase;

public class WorkoutArrayAdapter extends ArrayAdapter<WorkoutPartBase> {

    static final int VIEW_TYPE_PAUSE = 0;
    static final int VIEW_TYPE_WORKOUT = 1;
    static final int VIEW_TYPE_COUNT = 2;

    public WorkoutArrayAdapter(Context context, ArrayList<WorkoutPartBase> activities) {
        super(context,0 , activities);
    }

    @Override public int getViewTypeCount() {
        return VIEW_TYPE_COUNT;
    }

    @Override
    public int getItemViewType(int position) {
        WorkoutPartBase activity = getItem(position);

        if (activity instanceof WorkoutPart) {
            return VIEW_TYPE_WORKOUT;
        }
        else {
            return VIEW_TYPE_PAUSE;
        }
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        WorkoutPartBase activity = getItem(position);

        if (convertView == null) {
            int layoutId = 0;
            if (getItemViewType(position) == VIEW_TYPE_WORKOUT) {
                layoutId = R.layout.list_row_workout;
            }
            else {
                layoutId = R.layout.list_row_pause;
            }
            convertView = LayoutInflater.from(getContext()).inflate(layoutId, parent, false);
        }

        TextView duration_TextView = convertView.findViewById(R.id.duration_TextView);
        duration_TextView.setText(Integer.toString(activity.getDuration()));

        return convertView;
    }
}
