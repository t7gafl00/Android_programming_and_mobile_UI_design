package com.example.lab_5_2.model;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.TextView;

import com.example.lab_5_2.R;

import java.util.ArrayList;

public class ToDoItemArrayAdapter extends ArrayAdapter<ToDoItem> {

    public ToDoItemArrayAdapter(Context context, ArrayList<ToDoItem> todoitems) {
        super(context, 0, todoitems);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        ToDoItem todoitem = getItem(position);

        convertView = LayoutInflater.from(getContext()).inflate(R.layout.todoitem_list_item, parent, false);

        TextView item_name = convertView.findViewById(R.id.todoitem_list_name_CheckedTextView);
        item_name.setText(todoitem.getName());
        TextView item_description = convertView.findViewById(R.id.todoitem_list_description_TextView);
        item_description.setText(todoitem.getDescription());
        TextView item_date = convertView.findViewById(R.id.todoitem_list_date_TextView);
        item_date.setText(todoitem.getDate());
        CheckBox item_checked = convertView.findViewById(R.id.todoitem_list_CheckBox);
        if (todoitem.getChecked() == 1) {
            item_checked.setChecked(true);
        } else {
            item_checked.setChecked(false);
        }



        return convertView;
    }
}
