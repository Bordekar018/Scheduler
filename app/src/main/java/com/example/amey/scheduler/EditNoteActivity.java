package com.example.amey.scheduler;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;

import com.example.amey.scheduler.db.NotesDB;
import com.example.amey.scheduler.db.NotesDao;
import com.example.amey.scheduler.model.Note;

import java.util.Date;

public class EditNoteActivity extends AppCompatActivity {
private EditText inputNote;
private NotesDao dao;
private Note temp;
public static final String NOTE_EXTRA_KEY="note_id";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_note);
        inputNote=(EditText)findViewById(R.id.input_note);
        dao = NotesDB.getInstance(this).notesDao();
        if(getIntent().getExtras()!=null){
            int id=getIntent().getExtras().getInt(NOTE_EXTRA_KEY,0);
            temp=dao.getNoteById(id);
            inputNote.setText(temp.getNoteText());
        }

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.edit_note_menu,menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if(id==R.id.save_note)
            onSaveNote();
        return super.onOptionsItemSelected(item);
    }

    private void onSaveNote() {
        String text = inputNote.getText().toString();
        if (!text.isEmpty()) {
            long date = new Date().getTime(); // get  system time
            // if  exist update els crete new
            if (temp == null) {
                temp = new Note(text, date);
                dao.insertNote(temp); // create new note and inserted to database
            } else {
                temp.setNoteText(text);
                temp.setNoteDate(date);
                dao.updatenote(temp); // change text and date and update note on database
            }

            finish(); // return to the MainActivity
    }

    }
}
