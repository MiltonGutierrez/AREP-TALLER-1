package edu.escuelaing.arep.taller1.Services;

import java.util.ArrayList;
import edu.escuelaing.arep.taller1.Model.Note;
import edu.escuelaing.arep.taller1.Services.Exception.NoteServicesException;


public interface NoteServices {
    ArrayList<Note> getNotes();
    void addNote(String title, String group, String content) throws NoteServicesException;
}
