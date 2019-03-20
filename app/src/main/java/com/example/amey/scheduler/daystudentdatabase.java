package com.example.amey.scheduler;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TimePicker;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Locale;

public class daystudentdatabase extends AppCompatActivity {
    EditText e1,e2,e3,e4;
    Button b1,b2,b3;
    DatabaseHelperTimetable db;
    Spinner spin;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_daystudentdatabase);
        spin = (Spinner) findViewById(R.id.spinnerteacher);
        db=new DatabaseHelperTimetable(this);
        e1=(EditText) findViewById(R.id.datestudent);
        e2=(EditText) findViewById(R.id.subjectstudent);
        e3=(EditText) findViewById(R.id.subjecttime);
        e4=(EditText) findViewById(R.id.subjectprof);
        b1=(Button) findViewById(R.id.addstudent);
        b2=(Button) findViewById(R.id.updatestudent);
        b3=(Button) findViewById(R.id.deletestudent);
        // capital_textView = (TextView) findViewById(R.id.capital);
        final Calendar myCalendar = Calendar.getInstance();

        //value to be shown in the spinner
        List<String> days = new ArrayList<>();
        days.add(0, "Choose A Day");
        days.add("Monday");
        days.add("Tuesday");
        days.add("wednesday");
        days.add("Thursday");
        days.add("Friday");
        days.add("Saturday");
        days.add("Sunday");
        //array adapter used to bind values in the spinner
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item, days);

        spin.setAdapter(adapter);

        //on item select event handling
        spin.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if (parent.getItemAtPosition(position).equals("Choose A Day"))
                {
                    //do nothing
                }
                else
                {
                    //on selecting a spinner item
                    String item = parent.getItemAtPosition(position).toString();

                    //show selected spinner item
                    Toast.makeText(parent.getContext(), "Selected: " +item, Toast.LENGTH_SHORT).show();

                    //anything else you want to do on item selection do here
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String s1=spin.getSelectedItem().toString();
                String s2=e1.getText().toString();
                String s3=e2.getText().toString();
                String s4=e3.getText().toString();
                String s5=e4.getText().toString();
                if(s2.equals("")||s3.equals("")||s4.equals("")||s5.equals("")){
                    Toast.makeText(getApplicationContext(),"Fields are empty",Toast.LENGTH_SHORT).show();
                }
                else {
                    Boolean chkemail = db.chkday(s1);
                    if (chkemail == true) {
                        Boolean insert = db.insert(s1, s2, s3, s4, s5);
                        if (insert == true) {
                            Toast.makeText(getApplicationContext(), "Succesfull", Toast.LENGTH_SHORT).show();
                        }
                    } else {
                        Toast.makeText(getApplicationContext(), "Days Are Already Present", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });
        b2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String su1=spin.getSelectedItem().toString();
                String su2=e1.getText().toString();
                String su3=e2.getText().toString();
                String su4=e3.getText().toString();
                String su5=e4.getText().toString();

                    Boolean updateData = db.updateData(su1, su2, su3, su4, su5);
                    if (updateData == true) {
                        Toast.makeText(getApplicationContext(), "Timetable Updated Succesfully", Toast.LENGTH_SHORT).show();
                    } else {

                        Toast.makeText(getApplicationContext(), "Error Occured Please Check Again", Toast.LENGTH_SHORT).show();
                    }

            }
        });

        b3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Integer deletedRows = db.deleteData(spin.getSelectedItem().toString());
                if(deletedRows > 0)
                    Toast.makeText(daystudentdatabase.this,"Deleted",Toast.LENGTH_LONG).show();
                else
                    Toast.makeText(daystudentdatabase.this,"Data could not be Deleted",Toast.LENGTH_LONG).show();
            }
        });
        final DatePickerDialog.OnDateSetListener date = new DatePickerDialog.OnDateSetListener() {

            @Override
            public void onDateSet(DatePicker view, int year, int monthOfYear,
                                  int dayOfMonth) {
                // TODO Auto-generated method stub
                myCalendar.set(Calendar.YEAR, year);
                myCalendar.set(Calendar.MONTH, monthOfYear);
                myCalendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);
                updateLabel();
            }

            private void updateLabel() {
                String myFormat = "MM/dd/yy"; //In which you need put here
                SimpleDateFormat sdf = new SimpleDateFormat(myFormat, Locale.US);

                e1.setText(sdf.format(myCalendar.getTime()));
            }

        };
        e1.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                new DatePickerDialog(daystudentdatabase.this, date, myCalendar
                        .get(Calendar.YEAR), myCalendar.get(Calendar.MONTH),
                        myCalendar.get(Calendar.DAY_OF_MONTH)).show();
            }
        });
        e3.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
               Calendar mcurrentTime = Calendar.getInstance();
               int hour = mcurrentTime.get(Calendar.HOUR_OF_DAY);
               int minute = mcurrentTime.get(Calendar.MINUTE);
                TimePickerDialog mTimePicker;
                mTimePicker = new TimePickerDialog(daystudentdatabase.this, new TimePickerDialog.OnTimeSetListener()
                {
                    @Override
                    public void onTimeSet(TimePicker timePicker, int selectedHour, int selectedMinute) {
                        String status = "AM";

                        if(selectedHour> 11)
                        {
                            status = "PM";
                        }
                        int hour_of_12_hour_format;
                        if(selectedHour > 11){
                            hour_of_12_hour_format = selectedHour - 12;
                        }
                        else {
                            hour_of_12_hour_format = selectedHour;
                        }
                        e3.setText( hour_of_12_hour_format + ":" + selectedMinute+ " " + status );
                        e3.setText( hour_of_12_hour_format + ":" + selectedMinute+ " " + status );
                    }
                }, hour, minute, false);//Yes 24 hour time
                mTimePicker.setTitle("Select Time");
                mTimePicker.show();

            }
        });
    }
}