package com.myapplicationdev.android.taskmanager;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class AddActivity extends AppCompatActivity {
    EditText etName,etDescription;
    Button btnAdd,btnCancel;
    String name,des;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add);
        etName =(EditText)findViewById(R.id.etName);
        etDescription = (EditText)findViewById(R.id.etDescription);
        btnAdd = (Button)findViewById(R.id.btnAdd);
        btnCancel = (Button)findViewById(R.id.btnCancel);

        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DBHelper dbh = new DBHelper(AddActivity.this);
                name = etName.getText().toString().trim();
                des = etDescription.getText().toString().trim();
                dbh.insertTasks(name,des);
                dbh.close();
                Intent i = new Intent(AddActivity.this, MainActivity.class);
                startActivity(i);
            }
        });
        btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(AddActivity.this, MainActivity.class);
                startActivity(i);
            }
        });
    }

}
