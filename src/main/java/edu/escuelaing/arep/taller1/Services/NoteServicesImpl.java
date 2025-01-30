package edu.escuelaing.arep.taller1.Services;

import java.time.LocalDate;
import java.util.ArrayList;

import edu.escuelaing.arep.taller1.Model.Note;
import edu.escuelaing.arep.taller1.Model.NoteGroup;
import edu.escuelaing.arep.taller1.Services.Exception.NoteServicesException;

public class NoteServicesImpl implements NoteServices{

    private ArrayList<Note> notes = new ArrayList<>();


    @Override
    public ArrayList<Note> getNotes() {
        return notes;
    }

    @Override
    public void addNote(String title, String group, String content) throws NoteServicesException {
        if (title.isEmpty() || group.isEmpty() || content.isEmpty()) {
            throw new NoteServicesException(NoteServicesException.EMPTY_PARAMETERS);
        }
        try {
            NoteGroup noteGroup = NoteGroup.valueOf(group.toUpperCase());
            notes.add(new Note(title, noteGroup, content, LocalDate.now()));
        } catch (IllegalArgumentException e) {
            throw new NoteServicesException(NoteServicesException.INVALID_GROUP);
        }
       
    }

        
    
}
