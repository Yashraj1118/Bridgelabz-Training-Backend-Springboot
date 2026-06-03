package com.fundoonotes.fundoo_notes.dto;

import jakarta.validation.constraints.NotBlank;

public class NoteDTO {

    private String title;

    @NotBlank(message = "Content cannot be empty")
    private String content;

    private String color;

    // GETTERS AND SETTERS
    public String getTitle() { return title; }
    public void setTitle(String title) { this.title = title; }

    public String getContent() { return content; }
    public void setContent(String content) { this.content = content; }

    public String getColor() { return color; }
    public void setColor(String color) { this.color = color; }
}
