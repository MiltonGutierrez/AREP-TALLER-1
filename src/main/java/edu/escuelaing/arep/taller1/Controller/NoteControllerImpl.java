package edu.escuelaing.arep.taller1.Controller;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.stream.Collectors;

import edu.escuelaing.arep.taller1.Model.Note;
import edu.escuelaing.arep.taller1.Model.NoteGroup;
import edu.escuelaing.arep.taller1.Services.NoteServices;

public class NoteControllerImpl implements NoteController {

    private final NoteServices noteServices;

    public NoteControllerImpl(NoteServices noteServices) {
        this.noteServices = noteServices;
    }

    @Override
    public String getNotes() {
        ArrayList<Note> notes = noteServices.getNotes();
        StringBuilder response = new StringBuilder();
        response.append("HTTP/1.1 200 OK\r\n");
        response.append("Content-Type: application/json\r\n");
        response.append("\r\n");
        response.append("[" +
                notes.stream()
                        .map(note -> String.format(
                                "{\"title\":\"%s\", \"group\":\"%s\", \"content\":\"%s\", \"date\":\"%s\"}",
                                note.getTitle(),
                                note.getGroup().name(), // El enum se convierte a String
                                note.getContent(),
                                note.getDate().toString() // Convierte LocalDate a String
                        ))
                        .collect(Collectors.joining(","))
                +
                "]");
        return response.toString();
    }

    @Override
    public String addNote(String payload) {
        String[] elements = payload.split("&");
        String title = elements[0].substring(6); // puede mejorarse la logica
        String group = elements[1].substring(6);
        String content = elements[2].substring(8);
        noteServices.addNote(title, NoteGroup.valueOf(group.toUpperCase()), content);

        StringBuilder response = new StringBuilder();
        response.append("HTTP/1.1 200 OK\r\n");
        response.append("Content-Type: application/json\r\n");
        response.append("\r\n");
        response.append("{ \"title\": " + "\"" + title + "\", " + "\"group\": " + "\"" + group + "\", "
                + "\"content\": " + "\"" + content + "\" " + "}");
        return response.toString();

    }

}
