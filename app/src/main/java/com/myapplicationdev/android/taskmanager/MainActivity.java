package com.myapplicationdev.android.taskmanager;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    Task data;
    ListView lv;
    Button btnAddTask;
    ArrayList<Task> tasks;
    Spinner aSpinner;
    ArrayAdapter aa;
    DBHelper dbh;
    TextView tvID,tvDes;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        tasks = new ArrayList<Task>();
        setContentView(R.layout.activity_main);
        lv = (ListView)this.findViewById(R.id.lv);
        btnAddTask = (Button)this.findViewById(R.id.btnAddTask);
        dbh = new DBHelper(this);
        aa = new ArrayAdapter<Task>(this,
                android.R.layout.simple_list_item_1,tasks);
        lv.setAdapter(aa);
        tasks.clear();
        tasks.addAll(dbh.getTasks());
        dbh.close();

        btnAddTask.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(MainActivity.this, AddActivity.class);
                startActivity(i);
            }
        });

    }
}
