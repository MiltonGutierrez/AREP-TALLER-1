package edu.escuelaing.arep.taller1.Services;

import java.util.ArrayList;

import edu.escuelaing.arep.taller1.Model.Note;
import edu.escuelaing.arep.taller1.Model.NoteGroup;

public interface NoteServices {
    ArrayList<Note> getNotes();
    void addNote(String title, NoteGroup group, String content);
}
