package com.myapplicationdev.android.taskmanager;

import android.app.Activity;
import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.Calendar;

import androidx.appcompat.app.AppCompatActivity;

public class AddActivity extends AppCompatActivity {
    int reqCode = 12345;
    EditText etName,etDescription,etTime;
    Button btnAdd,btnCancel;
    String name,des;
    Integer time;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add);

        etName =(EditText)findViewById(R.id.etName);
        etDescription = (EditText)findViewById(R.id.etDescription);
        etTime = (EditText)findViewById(R.id.etTime);

        btnAdd = (Button)findViewById(R.id.btnAdd);
        btnCancel = (Button)findViewById(R.id.btnCancel);

        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Calendar cal = Calendar.getInstance();
                time = Integer.parseInt(etTime.getText().toString());
                cal.add(Calendar.SECOND, time);
                DBHelper dbh = new DBHelper(AddActivity.this);
                name = etName.getText().toString().trim();
                des = etDescription.getText().toString().trim();

                dbh.insertTasks(name,des);
                dbh.close();
                Intent intent = new Intent(AddActivity.this,
                        ScheduledNotificationReceiever.class);
                intent.putExtra("data",name);

                PendingIntent pendingIntent = PendingIntent.getBroadcast(
                        AddActivity.this, reqCode,
                        intent, PendingIntent.FLAG_CANCEL_CURRENT);

                AlarmManager am = (AlarmManager)
                        getSystemService(Activity.ALARM_SERVICE);
                am.set(AlarmManager.RTC_WAKEUP, cal.getTimeInMillis(),
                        pendingIntent);


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
