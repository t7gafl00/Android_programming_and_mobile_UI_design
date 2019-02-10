package com.example.lab_5_2.model.db;

import android.provider.BaseColumns;

public class ToDoItemContract {
    // To prevent someone from accidentally instantiating the contract class,
    // make the constructor private.
    private ToDoItemContract() {}

    /* Inner class that defines the table contents */
    public static class ToDoItem implements BaseColumns {
        public static final String TABLE_NAME = "toDoItems";
        public static final String COLUMN_NAME_NAME = "name";
        public static final String COLUMN_NAME_DESCRIPTION = "description";
        public static final String COLUMN_NAME_DATE = "date";
    }
}
