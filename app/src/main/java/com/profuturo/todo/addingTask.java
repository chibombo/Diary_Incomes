package com.profuturo.todo;

import android.app.DatePickerDialog;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.icu.text.DateTimePatternGenerator;
import android.icu.text.SimpleDateFormat;
import android.support.annotation.StyleRes;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;

import com.profuturo.todo.Data.dbTasks;

import java.text.DateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.TimeZone;

public class addingTask extends AppCompatActivity implements DatePickerDialog.OnDateSetListener {
    private EditText TaskName;
    private EditText TaskDescription;
    private TextView TaskDate;
    private Calendar myCalendar;
    private Button btnChangeDate;
    private int day;
    private int month;
    private int year;
    private DateFormat format;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_adding_task);
        TaskName = (EditText) findViewById(R.id.txtTask);
        TaskDescription = (EditText) findViewById(R.id.txtDescripcion);
        TaskDate = (TextView) findViewById(R.id.txtDate);
        btnChangeDate = (Button) findViewById(R.id.btnAgregarFecha);

        //Guardar referencias para que al cambiar la orientacion no se borre
        if (savedInstanceState != null)
        {
            TaskName.setText(savedInstanceState.getString("taskName"));
            TaskDescription.setText(savedInstanceState.getString("taskDescription"));
            TaskDate.setText(savedInstanceState.getString("taskDate"));
        }

        myCalendar = Calendar.getInstance(TimeZone.getDefault());
        format = new java.text.SimpleDateFormat("dd/MMM/yyyy");

        TaskDate.setText(format.format(myCalendar.getTime()));

        //TaskDate.setText(TimeZone.getDefault().getDisplayName());

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
        //item.setId(1);
        item.setTarea(TaskName.getText().toString());
        item.setFecha(TaskDate.getText().toString());
        item.setDescripcion(TaskDescription.getText().toString());
        db.insertTask(item.getTarea(),item.getDescripcion(),item.getFecha());
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
