package com.example.amey.scheduler;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.example.amey.scheduler.adapters.NotesAdapter;
import com.example.amey.scheduler.callbacks.NoteEventListener;
import com.example.amey.scheduler.db.NotesDB;
import com.example.amey.scheduler.db.NotesDao;
import com.example.amey.scheduler.model.Note;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static com.example.amey.scheduler.EditNoteActivity.NOTE_EXTRA_KEY;

public class keepnotes extends AppCompatActivity implements NoteEventListener {
    private static final String TAG = "keepnotes";
    private RecyclerView recyclerView;
    private ArrayList<Note> notes;
    private NotesAdapter adapter;
    private NotesDao dao;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_keepnotes);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        recyclerView=findViewById(R.id.notes_list);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));


        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onAddNewNote();
            }
        });
        dao= NotesDB.getInstance(this).notesDao();
    }

    private void loadNotes() {
        this.notes=new ArrayList<>();
        List<Note> list = dao.getNotes();
        this.notes.addAll(list);
        this.adapter=new NotesAdapter(this, this.notes);
        //set listener to adapter

        this.adapter.setListener(this);
        this.recyclerView.setAdapter(adapter);

    }

    private void onAddNewNote() {
        startActivity(new Intent(this,EditNoteActivity.class));
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if(id==R.id.stud)
        {
            Intent searchIntent = new Intent(keepnotes.this,loginstud.class);
            startActivity(searchIntent);

        }
        else if(id == R.id.teacher){
            Intent searchIntent = new Intent(keepnotes.this, loginteach.class);
            startActivity(searchIntent);

        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onResume() {
        super.onResume();
        loadNotes();
    }

    @Override
    public void onNoteClick(Note note) {
    Intent edit=new Intent(this,EditNoteActivity.class);
    edit.putExtra(NOTE_EXTRA_KEY,note.getId());
    startActivity(edit);
    }

    @Override
    public void onNoteLongClick(final Note note) {
        new AlertDialog.Builder(this)
                .setTitle(R.string.delete).setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
               dialogInterface.dismiss();
            }
        }).setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
               dao.deletenote(note);
                loadNotes();//refresh notes

            }
        }).create().show();



    }
}
