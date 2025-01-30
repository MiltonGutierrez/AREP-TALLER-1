package edu.escuelaing.arep.taller1.Controller;

import java.util.Arrays;

import edu.escuelaing.arep.taller1.Model.NoteGroup;
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
    public String addNote(String payload) {
        String[] elements = payload.split("&");
        String title = elements[0].substring(6); // puede mejorarse la logica 
        String group = elements[1].substring(6);
        String content = elements[2].substring(8);
        notesServices.addNote(title, NoteGroup.valueOf(group.toUpperCase()), content);

        StringBuilder response = new StringBuilder();
        response.append("HTTP/1.1 200 OK\r\n");
        response.append("Content-Type: application/json\r\n");
        response.append("\r\n");
        response.append("{ \"title\": "+ "\""+ title +"\", " + "\"group\": "+ "\"" + group + "\", " + "\"content\": "+ "\""+ content + "\" " + "}");
        return response.toString();

    }
    
}
