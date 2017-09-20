package com.profuturo.todo.Data;


import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

public class dbTasks
{
    private DataBaseHelper dbHelper;
    private SQLiteDatabase database;

    public final static String taskTable = "toDoDB";
    public final static String taskId = "id";
    public final static String taskName = "task_name";
    public final static String taskDate = "date";
    public final static String taskDescription = "task_description";

    public dbTasks(Context context)
    {
        dbHelper = new DataBaseHelper(context);
        database = dbHelper.getWritableDatabase();
    }

    public long insertTask(String Task, String Description ,String Date)
    {
        ContentValues values = new ContentValues();
        values.put(taskName, Task);
        values.put(taskDate, Date);
        values.put(taskDescription, Description);
        return database.insert(taskTable,null,values);
    }
    public Cursor selectTask()
    {
        Cursor cursor;
        try
        {
            String [] cols = new String[]{taskId,taskName,taskDescription,taskDate};
            cursor = database.query(taskTable,cols,null,null,null,null,null,null);
            if(cursor != null)
            {
                cursor.moveToFirst();
            }
        }
        catch(Exception ex)
        {
            Log.d("Error",ex.getMessage());
            cursor = null;
        }
        database.close();
        return cursor;
    }
    public void deleteAll()
    {
       database.delete(taskTable,null,null);
        //database.rawQuery("drop table toDoBD",null);
    }
    public int update(int TaskId, String TaskName, String TaskDescription, String TaskDate)
    {
        ContentValues values = new ContentValues();
        values.put(taskName,TaskName);
        values.put(taskDate,TaskDate);
        values.put(taskId,TaskId);
        values.put(taskDescription,TaskDescription);
        return database.update(taskTable,values,taskId+"="+String.valueOf(TaskId),null);
    }
}
