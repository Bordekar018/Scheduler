package com.example.amey.scheduler;

import android.content.Intent;
import android.database.Cursor;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.view.View;
import android.widget.Button;

public class teacher extends AppCompatActivity {
DatabaseHelperTimetableTeacher db;
CardView c1;
    Button b2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_teacher);
        db= new DatabaseHelperTimetableTeacher(this);
        c1=(CardView) findViewById(R.id.indexteacher);
        b2=(Button)findViewById(R.id.buttonlogoutteacher);
        viewAll();
        logout();
    }
    public void logout(){
        b2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent endintent = new Intent(teacher.this,loginstud.class);
                startActivity(endintent);
                finish();
            }
        });

    }

    public void viewAll(){
        c1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Cursor res=db.getAllData();
                if(res.getCount() == 0) {
                    // show message
                    showMessage("Error","Nothing found");
                    return;
                }
                StringBuffer buffer=new StringBuffer();
                while(res.moveToNext()){
                    buffer.append("Day - "+res.getString(0)+"\n\n");
                    buffer.append("Date - "+ res.getString(1)+"\n\n");
                    buffer.append("Subject - "+res.getString(2)+"\n\n");
                    buffer.append("Time - "+ res.getString(3)+"\n\n");
                    buffer.append("Batch - "+ res.getString(4)+"\n\n");
                    buffer.append("Branch - "+ res.getString(5)+"\n\n----------------------------------------------------------\n\n");

                }
                showMessage("TimeTable",buffer.toString());
            }
        });
    }
    private void showMessage(String title, String message) {
        AlertDialog.Builder builder= new AlertDialog.Builder(this);
        builder.create();
        builder.setCancelable(true);
        builder.setTitle(title);
        builder.setMessage(message);
        builder.show();
    }
}