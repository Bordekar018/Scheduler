package com.example.amey.scheduler;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;


public class teach_add extends AppCompatActivity {
DatabaseHelperTwo dbtwo;
EditText ea1,ea2,ea3;
Button ba1;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_teach_add);
        dbtwo=new DatabaseHelperTwo(this);
        ea1=(EditText) findViewById(R.id.tid);
        ea2=(EditText)findViewById(R.id.tpass);
        ea3=(EditText)findViewById(R.id.tcpass);
        ba1=(Button) findViewById(R.id.tadd);
        ba1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String a1=ea1.getText().toString();
                String a2=ea2.getText().toString();
                String a3=ea3.getText().toString();
                if(a1.equals("")||a2.equals("")||a3.equals(""))
                {
                    Toast.makeText(getApplicationContext(),"Fields Are Empty",Toast.LENGTH_SHORT).show();
                }
                else{
                    if(a2.equals(a3)){
                        Boolean chktemail =dbtwo.chktemail(a1);
                        if(chktemail==true){
                           Boolean insert=dbtwo.insert(a1,a2);
                           if(insert==true){
                               Toast.makeText(getApplicationContext(),"Succesfull",Toast.LENGTH_SHORT).show();
                           }
                        }
                        else{
                            Toast.makeText(getApplicationContext(),"Email already",Toast.LENGTH_SHORT).show();;
                        }
                    }
                      else {
                          Toast.makeText(getApplicationContext(),"Password Dont Match",Toast.LENGTH_SHORT).show();
                    }
                }



            }
        });



    }
}
