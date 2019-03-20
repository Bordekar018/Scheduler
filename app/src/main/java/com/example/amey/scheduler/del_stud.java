package com.example.amey.scheduler;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class del_stud extends AppCompatActivity {
    DatabaseHelper db;
   Button studel;
    EditText dels;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_del_stud);
        dels=(EditText)findViewById(R.id.dels);
        studel=(Button)findViewById(R.id.studel);
        db = new DatabaseHelper(this);


       studel.setOnClickListener(
               new View.OnClickListener() {
                  @Override
                   public void onClick(View v) {
                       Integer deletedRows = db.deleteData(dels.getText().toString());
                        if(deletedRows > 0)
                            Toast.makeText(del_stud.this,"Data Deleted",Toast.LENGTH_LONG).show();
                       else
                           Toast.makeText(del_stud.this,"Data could not be Deleted",Toast.LENGTH_LONG).show();
                   }
               }
       );
    }

}

