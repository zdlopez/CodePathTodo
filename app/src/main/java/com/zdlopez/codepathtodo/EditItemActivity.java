package com.zdlopez.codepathtodo;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.EditText;

public class EditItemActivity extends AppCompatActivity {
    EditText editTask;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_item);

        String editText = getIntent().getStringExtra("task");
        Integer position = getIntent().getIntExtra("position", 0);
        Log.d("edit view", Integer.toString(position));
        editTask = (EditText) findViewById(R.id.editTask);
        editTask.setText(editText);
        editTask.setSelection(editText.length());
    }
}
