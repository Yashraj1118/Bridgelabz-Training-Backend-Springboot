package com.fundoonotes.fundoo_notes.dto;

import java.time.LocalDateTime;

public class ReminderDTO {

    private LocalDateTime reminderTime;

    public LocalDateTime getReminderTime() { return reminderTime; }
    public void setReminderTime(LocalDateTime reminderTime) {
        this.reminderTime = reminderTime;
    }
}
