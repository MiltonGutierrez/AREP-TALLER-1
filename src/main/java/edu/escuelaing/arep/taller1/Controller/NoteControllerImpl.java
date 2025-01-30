package edu.escuelaing.arep.taller1.Controller;

import edu.escuelaing.arep.taller1.Services.NoteServices;

public class NoteControllerImpl implements NoteController {

    private final NoteServices notesServices;

    public NoteControllerImpl(NoteServices notesServices) {
        this.notesServices = notesServices;
    }

    @Override
    public String getNotes() {
        throw new UnsupportedOperationException("Unimplemented method 'getNotes'");
    }

    @Override
    public String addNote() {
        throw new UnsupportedOperationException("Unimplemented method 'addNote'");
    }
    
}
