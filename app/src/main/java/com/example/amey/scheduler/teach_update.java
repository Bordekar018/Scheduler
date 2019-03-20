package com.example.amey.scheduler;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class teach_update extends AppCompatActivity {
EditText tu1,tu2;
Button tbu1;
DatabaseHelperTwo dbtwo;
    @Override
   protected void onCreate(Bundle savedInstanceState) {
       super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_teach_update);
        dbtwo= new DatabaseHelperTwo(this);
        tu1=(EditText)findViewById(R.id.uptid);
        tu2=(EditText)findViewById(R.id.uptpass);
        tbu1=(Button)findViewById(R.id.uptb);
        tbu1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String tup1=tu1.getText().toString();
                String tup2=tu2.getText().toString();
                if(tup1.equals("")||tup2.equals("")){
                    Toast.makeText(getApplicationContext(),"Fields are empty",Toast.LENGTH_SHORT).show();
                }
                else{
                    Boolean chktpass=dbtwo.chktpass(tup2);
                    if(chktpass==true) {
                        Boolean updateData = dbtwo.updatetData(tup1,tup2);
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

