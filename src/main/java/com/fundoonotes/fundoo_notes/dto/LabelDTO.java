package com.fundoonotes.fundoo_notes.dto;

import jakarta.validation.constraints.NotBlank;

public class LabelDTO {

    @NotBlank(message = "Label name cannot be empty")
    private String name;

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
}