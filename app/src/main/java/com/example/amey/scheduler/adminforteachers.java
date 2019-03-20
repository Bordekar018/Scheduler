package com.example.amey.scheduler;

import android.content.Intent;

import android.database.Cursor;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.view.View;


public class adminforteachers extends AppCompatActivity {
    CardView t1, t2, t3, t4;
    DatabaseHelperTwo dbtwo;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_adminforteachers);
        t1 = (CardView) findViewById(R.id.addteach);
        t2 = (CardView) findViewById(R.id.updateteach);
        t3 = (CardView) findViewById(R.id.deleteteach);
        t4 = (CardView) findViewById(R.id.viewteachdata);
        dbtwo=new DatabaseHelperTwo(this);
        viewAll();
        t1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(adminforteachers.this, teach_add.class));
            }
        });
        t2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(adminforteachers.this, teach_update.class));
            }
        });
        t3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(adminforteachers.this, teach_del.class));
            }
        });

    }
    public void viewAll(){
        t4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Cursor res=dbtwo.getAllData();
                if(res.getCount() == 0) {
                    // show message
                    showMessage("Error","Nothing found");
                    return;
                }
                StringBuffer buffer=new StringBuffer();
                while(res.moveToNext()){
                    buffer.append("ID:"+res.getString(0)+"\n");
                    buffer.append("Password :"+ res.getString(1)+"\n\n");

                }
                showMessage("Data",buffer.toString());
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