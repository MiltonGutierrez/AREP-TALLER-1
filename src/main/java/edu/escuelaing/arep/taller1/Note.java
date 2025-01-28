package edu.escuelaing.arep.taller1;

import java.time.LocalDate;

public class Note {
    private String title;
    private String group;
    private String content;
    private LocalDate date;

    public Note(String title, String group, String content, LocalDate date) {
        this.title = title;
        this.group = group;
        this.content = content;
        this.date = date;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getGroup() {
        return group;
    }
    
    public void setGroup(String group) {
        this.group = group;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }
    
}
