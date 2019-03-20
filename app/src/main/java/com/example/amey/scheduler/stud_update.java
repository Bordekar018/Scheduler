package com.example.amey.scheduler;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class stud_update extends AppCompatActivity {
    EditText u1,u2;
    Button bu1;
    DatabaseHelper db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_stud_update);
        db=new DatabaseHelper(this);
        u1=(EditText) findViewById(R.id.upid);
        u2=(EditText) findViewById(R.id.upass);
        bu1=(Button) findViewById(R.id.upstud);
        bu1.setOnClickListener(new View.OnClickListener() {
                   @Override
                  public void onClick(View v) {
                       String su1=u1.getText().toString();
                       String su2=u2.getText().toString();
                       if(su1.equals("")||su2.equals("")){
                           Toast.makeText(getApplicationContext(),"Fields are empty",Toast.LENGTH_SHORT).show();
                       }
                              else{
                                  Boolean chkpass=db.chkpass(su2);
                                  if(chkpass==true) {
                                      Boolean updateData = db.updateData(su1, su2);
                                      if (updateData == true) {
                                          Toast.makeText(getApplicationContext(), "Succesfull", Toast.LENGTH_SHORT).show();
                                      }
                                  }
                                  else {
                               Toast.makeText(getApplicationContext(), "Password Already Exist ", Toast.LENGTH_SHORT).show();
                           }
                       }
                   }

        });

    }
}

