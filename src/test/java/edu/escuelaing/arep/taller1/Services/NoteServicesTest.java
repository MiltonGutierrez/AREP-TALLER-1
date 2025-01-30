package edu.escuelaing.arep.taller1.Services;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import edu.escuelaing.arep.taller1.Services.Exception.NoteServicesException;

import static org.junit.jupiter.api.Assertions.*;

class NoteServicesTest{

    private NoteServices noteServices;

    @BeforeEach
    public void setUp() {
        noteServices = new NoteServicesImpl();
    }

    @Test
    void shouldThrowNotesServicesExceptionSomeParametersAreEmpty(){

        assertThrows(NoteServicesException.class, () -> {
            noteServices.addNote("", "", "");
        });

        assertThrows(NoteServicesException.class, () -> {
            noteServices.addNote("", "work", "");
        });

        assertThrows(NoteServicesException.class, () -> {
            noteServices.addNote("", "work", "test");
        });

        assertThrows(NoteServicesException.class, () -> {
            noteServices.addNote("test1", "", "");
        });
    }

    @Test
    void shouldThrowNotesServicesExceptionInvalidGroup(){
        assertThrows(NoteServicesException.class, () -> {
            noteServices.addNote("test1", "invalid", "test");
        });

        assertThrows(NoteServicesException.class, () -> {
            noteServices.addNote("test1", "personal1", "test");
        });
    }

    @Test
    void shouldAddNotes() throws NoteServicesException {
        noteServices.addNote("test1", "work", "test");
        noteServices.addNote("test1", "work", "test");
        assertEquals(2, noteServices.getNotes().size());
    }

}
