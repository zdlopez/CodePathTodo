package com.zdlopez.codepathtodo;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

public class EditItemActivity extends AppCompatActivity {
    String editTask;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_item);

        editTask = getIntent().getStringExtra("task");
        Integer position = getIntent().getIntExtra("position", 0);
        Log.d("up in here", editTask);
        Log.d("edit view", Integer.toString(position));
    }
}
