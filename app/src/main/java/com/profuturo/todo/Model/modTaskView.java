package com.profuturo.todo.Model;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;

import com.profuturo.todo.Data.dbTasks;
import com.profuturo.todo.R;
import com.profuturo.todo.Tasks;

import java.text.DateFormat;
import java.util.Calendar;

/**
 * Created by LA501857 on 20/09/2017.
 */

public class modTaskView extends AppCompatActivity implements DatePickerDialog.OnDateSetListener{
    private EditText TaskName;
    private EditText TaskDescription;
    private TextView TaskDate;
    private Calendar myCalendar;
    private Button btnChangeDate;
    private Button btnNewTask;
    private int day;
    private int month;
    private int year;
    private DateFormat format;
    private int TaskId;
    private final static String taskId = "id";
    private final static String taskName = "task_name";
    private final static String taskDate = "date";
    private final static String taskDescription = "task_description";

    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_adding_task);
        TaskName = (EditText) findViewById(R.id.txtTask);
        TaskDescription = (EditText) findViewById(R.id.txtDescripcion);
        TaskDate = (TextView) findViewById(R.id.txtDate);
        btnChangeDate = (Button) findViewById(R.id.btnAgregarFecha);
        btnNewTask = (Button) findViewById(R.id.btnNewTask);
        btnNewTask.setText("Cambiar");
        Bundle values = getIntent().getExtras();
        if(!values.isEmpty())
        {
            TaskName.setText(values.getString(taskName));
            TaskDate.setText(values.getString(taskDate));
            TaskDescription.setText(values.getString(taskDescription));
            TaskId = values.getInt(taskId);
        }

    }

    @Override
    public void onSaveInstanceState (Bundle outState)
    {
        outState.putString("taskName", TaskName.getText().toString());
        outState.putString("taskDescription", TaskDescription.getText().toString());
        outState.putString("taskDate", TaskDate.getText().toString());


        super.onSaveInstanceState(outState);
    }

    public void onClickDate(View v)
    {
        DatePickerDialog dialog = new DatePickerDialog(this, this,
                myCalendar.get(Calendar.YEAR), myCalendar.get(Calendar.MONTH),
                myCalendar.get(Calendar.DAY_OF_MONTH));
        dialog.show();


    }

    public void onClickNewTask(View v)
    {
        dbTasks db = new dbTasks(this);
        //Cursor select = db.selectTask();
        Tasks item = new Tasks();
        item.setId(TaskId);
        item.setTarea(TaskName.getText().toString());
        item.setFecha(TaskDate.getText().toString());
        item.setDescripcion(TaskDescription.getText().toString());
        db.update(item.getId(),item.getTarea(),item.getDescripcion(),item.getFecha());
        Intent returnIntent = new Intent();
        //returnIntent.putExtra("result",result);
        setResult(RESULT_CANCELED,returnIntent);
        finish();

    }

    @Override
    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth)
    {
        this.day = dayOfMonth;
        this.year = year;
        this.month = month;
        updateTaskDate();
    }
    private void updateTaskDate()
    {
        StringBuilder sbDate = new StringBuilder();
        sbDate.append(day).append("/").append(month).append("/").append(year);
        TaskDate.setText(sbDate);
    }
}
