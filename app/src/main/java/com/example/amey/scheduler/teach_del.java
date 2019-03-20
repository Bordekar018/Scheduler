package com.example.amey.scheduler;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class teach_del extends AppCompatActivity {
Button tde;
EditText tedi;
DatabaseHelperTwo dbtwo;
   @Override
   protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_teach_del);
        tedi=(EditText)findViewById(R.id.tdid);
        tde=(Button)findViewById(R.id.tdel);
         dbtwo=new DatabaseHelperTwo(this);
         tde.setOnClickListener(new View.OnClickListener() {
             @Override
             public void onClick(View v) {
                 Integer deletedRows = dbtwo.deleteData(tedi.getText().toString());
                 if(deletedRows > 0)
                     Toast.makeText(teach_del.this,"Data Deleted",Toast.LENGTH_LONG).show();
                 else
                     Toast.makeText(teach_del.this,"Data could not be Deleted",Toast.LENGTH_LONG).show();
             }
         });
    }

}
