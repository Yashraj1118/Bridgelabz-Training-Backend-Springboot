package com.fundoonotes.fundoo_notes.dto;

import java.time.LocalDateTime;
import java.util.List;

public class NoteResponseDTO {

    private Long id;
    private String title;
    private String content;
    private String color;
    private boolean isPinned;
    private boolean isArchived;
    private boolean isTrashed;
    private LocalDateTime reminder;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    private List<LabelResponseDTO> labels;

    // CONSTRUCTOR
    public NoteResponseDTO() {}

    // GETTERS AND SETTERS
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getTitle() { return title; }
    public void setTitle(String title) { this.title = title; }

    public String getContent() { return content; }
    public void setContent(String content) { this.content = content; }

    public String getColor() { return color; }
    public void setColor(String color) { this.color = color; }

    public boolean isPinned() { return isPinned; }
    public void setPinned(boolean pinned) { isPinned = pinned; }

    public boolean isArchived() { return isArchived; }
    public void setArchived(boolean archived) { isArchived = archived; }

    public boolean isTrashed() { return isTrashed; }
    public void setTrashed(boolean trashed) { isTrashed = trashed; }

    public LocalDateTime getReminder() { return reminder; }
    public void setReminder(LocalDateTime reminder) { this.reminder = reminder; }

    public LocalDateTime getCreatedAt() { return createdAt; }
    public void setCreatedAt(LocalDateTime createdAt) { this.createdAt = createdAt; }

    public LocalDateTime getUpdatedAt() { return updatedAt; }
    public void setUpdatedAt(LocalDateTime updatedAt) { this.updatedAt = updatedAt; }

    public List<LabelResponseDTO> getLabels() { return labels; }
    public void setLabels(List<LabelResponseDTO> labels) { this.labels = labels; }
}