package com.example.amey.scheduler.callbacks;

import com.example.amey.scheduler.model.Note;

public interface NoteEventListener {

    void onNoteClick(Note note);

    void onNoteLongClick(Note note);


}
