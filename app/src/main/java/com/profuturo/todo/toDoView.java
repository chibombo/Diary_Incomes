package com.profuturo.todo;

import android.content.Intent;
import android.database.Cursor;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.widget.CursorAdapter;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.profuturo.todo.Data.DataBaseHelper;
import com.profuturo.todo.Data.dbTasks;

import java.util.ArrayList;
import java.util.List;

public class toDoView extends AppCompatActivity {
    private RecyclerView recyclerview;
    private RecyclerView.Adapter listaJugadores;
    private RecyclerView.LayoutManager manager;
    private FloatingActionButton addBotton;
    //public final static String taskId = "id";
    public final static String taskName = "task_name";
    public final static String taskDate = "date";
    public final static String taskDescription = "task_description";



    @Override
    protected  void onResume()
    {
        super.onResume();
        List<Tasks> list = new ArrayList<>();
        //list.add();

        dbTasks db = new dbTasks(this);
        //db.deleteAll();
        Cursor select = db.selectTask();
        if(select.getCount()>0)
        {
            while(!select.isAfterLast())
            {
                Tasks item = new Tasks();
                item.setTarea(select.getString(select.getColumnIndex(taskName)));
                item.setFecha(select.getString(select.getColumnIndex(taskDate)));
                item.setDescripcion(select.getString(select.getColumnIndex(taskDescription)));
                //db.insertTask(item.getTarea(),item.getDescripcion(),item.getFecha());


                db.selectTask();
                list.add(item);
                select.moveToNext();
                item = null;
            }

            //item.setId(1);


            recyclerview.setHasFixedSize(true);
            manager = new LinearLayoutManager(this);
            recyclerview.setLayoutManager(manager);
            listaJugadores = new taskAdapter(list);
            recyclerview.setAdapter(listaJugadores);
        }
    }
    @Override
    protected void onRestart()
    {
        super.onRestart();
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_view);

        recyclerview = (RecyclerView) findViewById(R.id.recyclerview);
        addBotton = (FloatingActionButton) findViewById(R.id.addTaskButton);
        List<Tasks> list = new ArrayList<>();
        //list.add();

        dbTasks db = new dbTasks(this);
        //db.deleteAll();
        Cursor select = db.selectTask();
        if(select.getCount()>0)
        {
            while(!select.isAfterLast())
            {
                Tasks item = new Tasks();
                item.setTarea(select.getString(select.getColumnIndex(taskName)));
                item.setFecha(select.getString(select.getColumnIndex(taskDate)));
                item.setDescripcion(select.getString(select.getColumnIndex(taskDescription)));
                //db.insertTask(item.getTarea(),item.getDescripcion(),item.getFecha());


                db.selectTask();
                list.add(item);
                select.moveToNext();
                item = null;
            }

            //item.setId(1);


            recyclerview.setHasFixedSize(true);
            manager = new LinearLayoutManager(this);
            recyclerview.setLayoutManager(manager);
            listaJugadores = new taskAdapter(list);
            recyclerview.setAdapter(listaJugadores);
        }
       /* Tasks item = new Tasks();
        item.setId(1);
        item.setTarea(select.getString(select.getColumnIndex(taskName)));
        item.setFecha(select.getString(select.getColumnIndex(taskDate)));
        item.setDescripcion(select.getString(select.getColumnIndex(taskDescription)));
        db.insertTask(item.getTarea(),item.getDescripcion(),item.getFecha());


        //db.selectTask();
        list.add(item);

        listaJugadores = new taskAdapter(list);
        recyclerview.setAdapter(listaJugadores);
         */
    }
    public void addTask(View v)
    {
        Intent addView = new Intent(this,addingTask.class);
        startActivityForResult(addView,1);
    }
}
