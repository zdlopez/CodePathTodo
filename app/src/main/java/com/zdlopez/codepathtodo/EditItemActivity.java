package com.zdlopez.codepathtodo;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;

public class EditItemActivity extends AppCompatActivity {
    EditText editTask;
    Integer position;
    Todo editText;

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
