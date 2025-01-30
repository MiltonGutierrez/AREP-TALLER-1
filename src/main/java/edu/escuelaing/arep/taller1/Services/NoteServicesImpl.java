package edu.escuelaing.arep.taller1.Services;

import edu.escuelaing.arep.taller1.Model.Note;
import edu.escuelaing.arep.taller1.Model.NoteGroup;

import java.time.LocalDate;
import java.util.ArrayList;

public class NoteServicesImpl implements NoteServices{

    private ArrayList<Note> notes = new ArrayList<Note>();


    @Override
    public ArrayList<Note> getNotes() {
        return notes;
    }

    @Override
    public void addNote(String title, NoteGroup group, String content) {
        notes.add(new Note(title, group, content, LocalDate.now()));
    }

        
    
}
