package com.example.amey.scheduler;


import android.content.Intent;
import android.database.Cursor;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.view.View;
import android.widget.Toast;

public class adminforstudents extends AppCompatActivity {
    CardView c,c1,c2,c3;
    DatabaseHelper db;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_adminforstudents);
        c=(CardView)findViewById(R.id.addstud);
        c1=(CardView)findViewById(R.id.updatestud);
        c2=(CardView)findViewById(R.id.deletstud);
        c3=(CardView)findViewById(R.id.viewstuddata);
        db= new DatabaseHelper(this);
        viewAll();
        c.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(adminforstudents.this,Add_Stud.class));

            }
        });
        c1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(adminforstudents.this,stud_update.class));

            }
        });
        c2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(adminforstudents.this,del_stud.class));

            }
        });

    }
    public void viewAll(){
        c3.setOnClickListener(new View.OnClickListener() {
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

