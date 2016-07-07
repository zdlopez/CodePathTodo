package com.zdlopez.codepathtodo;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.InputType;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.DatePicker;
import android.widget.EditText;
import android.app.DatePickerDialog;
import android.app.DatePickerDialog.OnDateSetListener;
import android.view.View.OnClickListener;
import android.widget.Spinner;
import android.widget.AdapterView.OnItemSelectedListener;

import java.util.Calendar;

public class EditItemActivity extends AppCompatActivity {
    EditText editTask;
    EditText editDate;
    Integer position;
    Todo editText;
    Spinner spinner;

    private DatePickerDialog dateDialog;
    private static final String[] PRIORITY_STRINGS = {"Low", "Medium", "High"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_item);

        editText = (Todo) getIntent().getSerializableExtra("task");
        position = getIntent().getIntExtra("position", 0);
        Log.d("edit view", Integer.toString(position));
        editTask = (EditText) findViewById(R.id.editTask);
        editTask.setText(editText.getName());
        editTask.setSelection(editText.getName().length());

        editDate = (EditText) findViewById(R.id.editDate);
        editDate.setText(editText.getDueDateFormated());
        editDate.setInputType(InputType.TYPE_NULL);

        Calendar newCalendar = Calendar.getInstance();
        dateDialog = new DatePickerDialog(this, new OnDateSetListener() {

            public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                Calendar newDate = Calendar.getInstance();
                newDate.set(year, monthOfYear, dayOfMonth);
                editText.setDueDate(newDate);
                editDate.setText(editText.getDueDateFormated());
            }

        },newCalendar.get(Calendar.YEAR), newCalendar.get(Calendar.MONTH), newCalendar.get(Calendar.DAY_OF_MONTH));


        editDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dateDialog.show();
            }
        });

        spinner = (Spinner) findViewById(R.id.spinner);
        ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_spinner_item, PRIORITY_STRINGS);
        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(dataAdapter);
        spinner.setOnItemSelectedListener(new OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id) {
                // your code here
                editText.setPriority(spinner.getSelectedItemPosition());
            }

            @Override
            public void onNothingSelected(AdapterView<?> parentView) {
                // your code here
            }

        });
    }

    public void onSubmit(View v) {
        Intent data = new Intent();
        // Pass relevant data back as a result
        editText.setName(editTask.getText().toString());
        data.putExtra("task", editText);
//        data.putExtra("task", editTask.getText().toString());
        data.putExtra("position", position);
        data.putExtra("code", 201); // ints work too
        // Activity finished ok, return the data
        setResult(RESULT_OK, data); // set result code and bundle data for response
        finish(); // closes the activity, pass data to parent
    }
}
