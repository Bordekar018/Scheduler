package com.example.amey.scheduler;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class loginstud extends AppCompatActivity {
    EditText v1,v2;
    Button b1;
    DatabaseHelper db;


    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_loginstud);
        db=new DatabaseHelper(this);
        v1=(EditText) findViewById(R.id.studlogin);
        v2=(EditText) findViewById(R.id.studpass);
        b1=(Button)findViewById(R.id.btnstudlog);
        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String studemail=v1.getText().toString();
                String studpass=v2.getText().toString();
                Boolean chkemailpass=db.chkemailpass(studemail,studpass);
                if(chkemailpass==true) {
                        Intent startlogin = new Intent(loginstud.this, student.class);
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
