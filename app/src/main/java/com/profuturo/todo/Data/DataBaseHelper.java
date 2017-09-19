package com.profuturo.todo.Data;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class DataBaseHelper extends SQLiteOpenHelper {

    private static final String databaseName = "toDoDB.db";
    private static final int databaseVersion = 1;

    //Creacion de la base de datos

    private static final String databaseCreate = "create table toDoDB(id integer primary key autoincrement, task_name text not null,task_description text null, date text not null);";

    public DataBaseHelper(Context context)
    {
        super(context,databaseName,null,databaseVersion);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(databaseCreate);
    }



    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        Log.w(DataBaseHelper.class.getName(),
                "Upgrading database from version " + oldVersion + " to "
                        + newVersion + ", which will destroy all old data");
        db.execSQL("DROP TABLE IF EXISTS MyEmployees");
        onCreate(db);
    }
}
