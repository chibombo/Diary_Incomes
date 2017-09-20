package com.profuturo.todo;

import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.widget.CursorAdapter;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.style.UpdateLayout;
import android.util.Log;
import android.view.View;

import com.profuturo.todo.Data.DataBaseHelper;
import com.profuturo.todo.Data.dbTasks;
import com.profuturo.todo.Model.modTaskView;

import java.util.ArrayList;
import java.util.List;

public class toDoView extends AppCompatActivity{
    private RecyclerView recyclerview;
    private RecyclerView.Adapter listaJugadores;
    private RecyclerView.LayoutManager manager;
    private FloatingActionButton addBotton;
    public final static String taskId = "id";
    public final static String taskName = "task_name";
    public final static String taskDate = "date";
    public final static String taskDescription = "task_description";
    private int TaskIdForRemove;

    final DialogInterface.OnClickListener dialogClickListener = new DialogInterface.OnClickListener() {
        @Override
        public void onClick(DialogInterface dialog, int which) {
            switch (which){
                case DialogInterface.BUTTON_POSITIVE:
                    removeTask();
                    break;

                case DialogInterface.BUTTON_NEGATIVE:
                    //No button clicked
                    break;
            }
        }
    };

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
                item.setId(select.getInt(select.getColumnIndex(taskId)));
                //db.insertTask(item.getTarea(),item.getDescripcion(),item.getFecha());


                db.selectTask();
                list.add(item);
                select.moveToNext();

            }

            //item.setId(1);


            recyclerview.setHasFixedSize(true);
            manager = new LinearLayoutManager(this);
            recyclerview.setLayoutManager(manager);


            recyclerview.setAdapter(new taskAdapter(list, new taskAdapter.OnItemClickListener() {
                @Override
                public void onItemClick(Tasks item) {
                    try
                    {
                        Intent modTask = new Intent(toDoView.this,modTaskView.class);
                        modTask.putExtra(taskId,item.getId());
                        modTask.putExtra(taskName,item.getTarea());
                        modTask.putExtra(taskDate,item.getFecha());
                        modTask.putExtra(taskDescription,item.getDescripcion());
                        startActivityForResult(modTask,1);
                    }
                    catch(Exception ex)
                    {
                        Log.d("Error",ex.getMessage());
                    }
                }

                @Override
                public void onItemLongClick(Tasks item) {
                    TaskIdForRemove = item.getId();
                    AlertDialog askDeleteTask = new AlertDialog.Builder(toDoView.this).create();
                    askDeleteTask.setTitle("Advertencia");
                    askDeleteTask.setMessage("¿Seguro que quiere borrar esta tarea?");
                    askDeleteTask.setButton(Dialog.BUTTON_NEGATIVE,"Cancel",dialogClickListener);
                    askDeleteTask.setButton(Dialog.BUTTON_POSITIVE,"Remove",dialogClickListener);
                    askDeleteTask.show();
                }
            }));
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
                item.setId(select.getInt(select.getColumnIndex(taskId)));
                //db.insertTask(item.getTarea(),item.getDescripcion(),item.getFecha());


                db.selectTask();
                list.add(item);
                select.moveToNext();

            }
            recyclerview.setHasFixedSize(true);
            manager = new LinearLayoutManager(this);
            recyclerview.setLayoutManager(manager);



            recyclerview.setAdapter(new taskAdapter(list, new taskAdapter.OnItemClickListener() {
                @Override
                public void onItemClick(Tasks item) {
                    Intent modTask = new Intent(toDoView.this,modTaskView.class);
                    modTask.putExtra(taskId,item.getId());
                    modTask.putExtra(taskName,item.getTarea());
                    modTask.putExtra(taskDate,item.getFecha());
                    modTask.putExtra(taskDescription,item.getDescripcion());
                    startActivityForResult(modTask,1);
                }

                @Override
                public void onItemLongClick(Tasks item) {
                    TaskIdForRemove = item.getId();
                    AlertDialog askDeleteTask = new AlertDialog.Builder(toDoView.this).create();
                    askDeleteTask.setTitle("Advertencia");
                    askDeleteTask.setMessage("¿Seguro que quiere borrar esta tarea?");
                    askDeleteTask.setButton(Dialog.BUTTON_NEGATIVE,"Cancel",dialogClickListener);
                    askDeleteTask.setButton(Dialog.BUTTON_POSITIVE,"Remove",dialogClickListener);
                    askDeleteTask.show();
                }
            }));
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

    public void removeTask()
    {
        dbTasks db = new dbTasks(this);
        db.delete(TaskIdForRemove);
        TaskIdForRemove = 0;
        recreate();
        /*
        Intent refresh = getIntent();
        refresh.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
        finish();
        overridePendingTransition(0, 0);
        startActivity(refresh);
        overridePendingTransition(0, 0);
        */
    }


}
