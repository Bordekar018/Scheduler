package com.example.amey.scheduler;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.view.View;

public class adminrights extends AppCompatActivity {

CardView cardView;
  protected void onCreate(Bundle savedInstanceState) {
      super.onCreate(savedInstanceState);
      setContentView(R.layout.activity_adminrights);


   cardView=(CardView) findViewById(R.id.cd1);
    cardView.setOnClickListener(new View.OnClickListener() {
        @Override
       public void onClick(View v) {
            startActivity(new Intent(adminrights.this,adminforstudents.class));
        }
    });
      cardView=(CardView) findViewById(R.id.cd2);
      cardView.setOnClickListener(new View.OnClickListener() {
          @Override
          public void onClick(View v) {
              startActivity(new Intent(adminrights.this,adminforteachers.class));
          }
      });
      cardView=(CardView) findViewById(R.id.cd3);
      cardView.setOnClickListener(new View.OnClickListener() {
          @Override
          public void onClick(View v) {
              startActivity(new Intent(adminrights.this, daystudentdatabase.class));
          }
      });
      cardView=(CardView) findViewById(R.id.cd4);
      cardView.setOnClickListener(new View.OnClickListener() {
          @Override
          public void onClick(View v) {
              startActivity(new Intent(adminrights.this, dayteacherdatabase.class));
          }
      });

  }
}

