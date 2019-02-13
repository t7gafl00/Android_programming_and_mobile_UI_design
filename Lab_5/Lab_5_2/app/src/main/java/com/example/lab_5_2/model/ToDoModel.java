package com.example.lab_5_2.model;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.provider.BaseColumns;

import com.example.lab_5_2.model.db.ToDoItemContract;
import com.example.lab_5_2.model.db.ToDoItemDbHelper;

public class ToDoModel {

    ToDoItemDbHelper mDbHelper = null;

    public ToDoModel(Context context) {
        this.mDbHelper = new ToDoItemDbHelper(context);
    }

    public void addToDoItemToDb(ToDoItem addable) {
        // Gets the data repository in write mode
        SQLiteDatabase db = mDbHelper.getWritableDatabase();

        // Create a new map of values, where column names are the keys
        ContentValues values = new ContentValues();
        values.put(ToDoItemContract.ToDoItem.COLUMN_NAME_NAME, addable.name);
        values.put(ToDoItemContract.ToDoItem.COLUMN_NAME_DESCRIPTION, addable.description);
        values.put(ToDoItemContract.ToDoItem.COLUMN_NAME_DATE, addable.date);
        values.put(ToDoItemContract.ToDoItem.COLUMN_NAME_CHECKED, addable.checked);

        // Insert the new row, returning the primary key value of the new row
        long newRowId = db.insert(ToDoItemContract.ToDoItem.TABLE_NAME, null, values);
    }

    public void deleteToDoItemFromDb(ToDoItem deletable) {
        // Gets the data repository in write mode
        SQLiteDatabase db = mDbHelper.getWritableDatabase();
        // Define 'where' part of query.
        String selection = ToDoItemContract.ToDoItem.COLUMN_NAME_NAME + " LIKE ?";
        // Specify arguments in placeholder order.
        String[] selectionArgs = { deletable.getName() };
        // Issue SQL statement.
        int deletedRows = db.delete(ToDoItemContract.ToDoItem.TABLE_NAME, selection, selectionArgs);
    }

    public Cursor getToDoItemsList(int sort_value) {
        SQLiteDatabase db = mDbHelper.getReadableDatabase();

        Cursor cursor = null;

        switch (sort_value) {
            case 0:
                cursor = db.rawQuery(
                        "SELECT name, description, (strftime('%d.%m.%Y', date)) AS date, checked FROM toDoItems ", new String[0]);
                break;
            case 1:
                cursor = db.rawQuery(
                        "SELECT name, description, (strftime('%d.%m.%Y', date)) AS date, checked FROM toDoItems " +
                                "ORDER BY strftime('%Y.%m.%d', date) ASC ", new String[0]);
                break;
        }

        return cursor;
    }

    public Cursor getTodayToDoItemsList() {
        SQLiteDatabase db = mDbHelper.getReadableDatabase();

        Cursor cursor = db.rawQuery(
                "SELECT name, description, (strftime('%d.%m.%Y', date)) AS date, checked FROM toDoItems " +
                        "WHERE date = date('now')", new String[0]);

        return cursor;
    }

    public void setChecked(ToDoItem toDoItem) {
        SQLiteDatabase db = mDbHelper.getWritableDatabase();

        ContentValues cv = new ContentValues();
        cv.put("checked","1");

        String where = ToDoItemContract.ToDoItem.COLUMN_NAME_NAME + " LIKE ?";
        String[] whereArgs = { toDoItem.getName() };

        db.update(ToDoItemContract.ToDoItem.TABLE_NAME, cv, where, whereArgs);
    }

    public void setUnchecked(ToDoItem toDoItem) {
        SQLiteDatabase db = mDbHelper.getWritableDatabase();

        ContentValues cv = new ContentValues();
        cv.put("checked","0");

        String where = ToDoItemContract.ToDoItem.COLUMN_NAME_NAME + " LIKE ?";
        String[] whereArgs = { toDoItem.getName() };

        db.update(ToDoItemContract.ToDoItem.TABLE_NAME, cv, where, whereArgs);
    }
}
