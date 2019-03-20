package com.example.amey.scheduler;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class loginteach extends AppCompatActivity {
    EditText vt1,vt2;
    DatabaseHelperTwo dbtwo;
    Button b;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_loginteach);
        dbtwo=new DatabaseHelperTwo(this);
        vt1=(EditText) findViewById(R.id.teachlogin);
        vt2=(EditText) findViewById(R.id.teachpass);
        b=(Button)findViewById(R.id.btnteachlog);
        b.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String teachereamil= vt1.getText().toString();
                String teacherpass= vt2.getText().toString();
                Boolean chktemailpass=dbtwo.chktemailpass(teachereamil,teacherpass);
                if(chktemailpass==true){
                    Intent startlogin = new Intent(loginteach.this, teacher.class);
                    startActivity(startlogin);
                    finish();
                }
                else {
                    Toast.makeText(getApplicationContext(), "Invalid Login", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}
