package com.zdlopez.codepathtodo;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.appindexing.Action;
import com.google.android.gms.appindexing.AppIndex;
import com.google.android.gms.common.api.GoogleApiClient;

import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import android.content.Context;

public class MainActivity extends AppCompatActivity {
    ArrayList<Todo> items;
    TodosAdapter itemsAdapter;
    ListView lvItems;

    private final int REQUEST_CODE = 200;

    Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Todo mytodo = new Todo();
        // Construct the data source
        items = new ArrayList<Todo>();
        //readItems();
        // Create the adapter to convert the array to views
        itemsAdapter = new TodosAdapter(this, items);
        // Attach the adapter to a ListView
        lvItems = (ListView) findViewById(R.id.lvItems);
        lvItems.setAdapter(itemsAdapter);


//        lvItems = (ListView) findViewById(R.id.lvItems);
//        items = new ArrayList<>();
//        readItems();
//        itemsAdapter = new ArrayAdapter<>(this,
//                android.R.layout.simple_list_item_1, items);
//        lvItems.setAdapter(itemsAdapter);
        setupListViewListener();
        lvItems.setOnItemClickListener(new ListClickHandler());
    }

    public void onAddItem(View v) {
        EditText etNewItem = (EditText) findViewById(R.id.etNewItem);
        String itemText = etNewItem.getText().toString();
        //Log.d("blah", "what");
        Todo myTodo = new Todo();
        myTodo.setName(itemText);
        items.add(myTodo);
        itemsAdapter.notifyDataSetChanged();
        etNewItem.setText("");
       // writeItems();
    }

    public void setupListViewListener() {
        lvItems.setOnItemLongClickListener(
                new AdapterView.OnItemLongClickListener() {
                    @Override
                    public boolean onItemLongClick(AdapterView<?> adapter,
                                                   View item, int pos, long id) {
                        items.remove(pos);
                        itemsAdapter.notifyDataSetChanged();
                       // writeItems();
                        return true;
                    }
                });
    }

    public class ListClickHandler implements AdapterView.OnItemClickListener{

        @Override
        public void onItemClick(AdapterView<?> adapter, View view, int position, long id) {
            Todo selectedTask = items.get(position);
            Log.d("the selection is ", selectedTask.getName());
            Intent editIntent = new Intent(MainActivity.this, EditItemActivity.class);
            editIntent.putExtra("task", selectedTask);
            editIntent.putExtra("position", position);
            Log.d("what is position", Integer.toString(position));
            startActivityForResult(editIntent, REQUEST_CODE);
        }

    }
//
//    private void readItems() {
//
//
//        try {
//            FileInputStream fis = context.openFileInput("todos.txt");
//            ObjectInputStream is = new ObjectInputStream(fis);
//            items = (ArrayList<Todo>) is.readObject();
//            is.close();
//            fis.close();
//        } catch (IOException e) {
//            items = new ArrayList<Todo>();
//        }
//    }
//
//    private void writeItems() {
//        FileOutputStream fos = context.openFileOutput("todos.txt", Context.MODE_PRIVATE);
//        ObjectOutputStream os = new ObjectOutputStream(fos);
//
//
//
//
//
////        File filesDir = getFilesDir();
////        File todoFile = new File(filesDir, "todos.txt");
//        try {
////            for (Todo one: items) {
////                os.writeObject(this);
////            }
//            os.writeObject(items);
//            os.close();
//            fos.close();
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        // REQUEST_CODE is defined above
        if (resultCode == RESULT_OK && requestCode == REQUEST_CODE) {
            Todo newText = (Todo) data.getSerializableExtra("task");
            int position = data.getExtras().getInt("position", 0);

            items.set(position, newText);
            itemsAdapter.notifyDataSetChanged();
            //writeItems();
        }
    }
}
