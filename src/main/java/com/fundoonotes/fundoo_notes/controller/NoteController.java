package com.fundoonotes.fundoo_notes.controller;
import com.fundoonotes.fundoo_notes.service.NoteService;
import com.fundoonotes.fundoo_notes.dto.ApiResponse;
import com.fundoonotes.fundoo_notes.dto.NoteDTO;
import com.fundoonotes.fundoo_notes.dto.NoteResponseDTO;
import com.fundoonotes.fundoo_notes.dto.ReminderDTO;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/notes")
public class NoteController {

    @Autowired
    private NoteService noteService;

    private String getEmail() {
        return SecurityContextHolder.getContext()
                .getAuthentication().getName();
    }

    // CREATE NOTE
    @PostMapping
    public ResponseEntity<ApiResponse> createNote(
            @Valid @RequestBody NoteDTO dto) {
        try {
            NoteResponseDTO note = noteService.createNote(dto, getEmail());
            return ResponseEntity.status(HttpStatus.CREATED)
                    .body(new ApiResponse(201, "Note created successfully", note));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(new ApiResponse(400, e.getMessage()));
        }
    }

    // GET ALL NOTES
    @GetMapping
    public ResponseEntity<ApiResponse> getAllNotes() {
        try {
            List<NoteResponseDTO> notes = noteService.getAllNotes(getEmail());
            return ResponseEntity.ok(
                    new ApiResponse(200, "Notes fetched successfully", notes));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(new ApiResponse(400, e.getMessage()));
        }
    }

    // UPDATE NOTE
    @PutMapping("/{id}")
    public ResponseEntity<ApiResponse> updateNote(
            @PathVariable Long id,
            @RequestBody NoteDTO dto) {
        try {
            NoteResponseDTO note = noteService.updateNote(id, dto, getEmail());
            return ResponseEntity.ok(
                    new ApiResponse(200, "Note updated successfully", note));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(new ApiResponse(400, e.getMessage()));
        }
    }

    // DELETE NOTE
    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse> deleteNote(
            @PathVariable Long id) {
        try {
            String message = noteService.deleteNote(id, getEmail());
            return ResponseEntity.ok(new ApiResponse(200, message));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(new ApiResponse(400, e.getMessage()));
        }
    }

    // TOGGLE PIN
    @PatchMapping("/{id}/pin")
    public ResponseEntity<ApiResponse> togglePin(
            @PathVariable Long id) {
        try {
            String message = noteService.togglePin(id, getEmail());
            return ResponseEntity.ok(new ApiResponse(200, message));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(new ApiResponse(400, e.getMessage()));
        }
    }

    // TOGGLE ARCHIVE
    @PatchMapping("/{id}/archive")
    public ResponseEntity<ApiResponse> toggleArchive(
            @PathVariable Long id) {
        try {
            String message = noteService.toggleArchive(id, getEmail());
            return ResponseEntity.ok(new ApiResponse(200, message));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(new ApiResponse(400, e.getMessage()));
        }
    }

    // TOGGLE TRASH
    @PatchMapping("/{id}/trash")
    public ResponseEntity<ApiResponse> toggleTrash(
            @PathVariable Long id) {
        try {
            String message = noteService.toggleTrash(id, getEmail());
            return ResponseEntity.ok(new ApiResponse(200, message));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(new ApiResponse(400, e.getMessage()));
        }
    }

    // GET PINNED NOTES
    @GetMapping("/pinned")
    public ResponseEntity<ApiResponse> getPinnedNotes() {
        try {
            List<NoteResponseDTO> notes = noteService.getPinnedNotes(getEmail());
            return ResponseEntity.ok(
                    new ApiResponse(200, "Pinned notes fetched", notes));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(new ApiResponse(400, e.getMessage()));
        }
    }

    // GET ARCHIVED NOTES
    @GetMapping("/archived")
    public ResponseEntity<ApiResponse> getArchivedNotes() {
        try {
            List<NoteResponseDTO> notes = noteService.getArchivedNotes(getEmail());
            return ResponseEntity.ok(
                    new ApiResponse(200, "Archived notes fetched", notes));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(new ApiResponse(400, e.getMessage()));
        }
    }

    // GET TRASHED NOTES
    @GetMapping("/trash")
    public ResponseEntity<ApiResponse> getTrashedNotes() {
        try {
            List<NoteResponseDTO> notes = noteService.getTrashedNotes(getEmail());
            return ResponseEntity.ok(
                    new ApiResponse(200, "Trashed notes fetched", notes));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(new ApiResponse(400, e.getMessage()));
        }
    }

    // SEARCH NOTES
    @GetMapping("/search")
    public ResponseEntity<ApiResponse> searchNotes(
            @RequestParam String keyword) {
        try {
            List<NoteResponseDTO> notes =
                    noteService.searchNotes(keyword, getEmail());
            return ResponseEntity.ok(
                    new ApiResponse(200, "Search results", notes));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(new ApiResponse(400, e.getMessage()));
        }
    }

    // FILTER BY COLOR
    @GetMapping("/color")
    public ResponseEntity<ApiResponse> filterByColor(
            @RequestParam String color) {
        try {
            List<NoteResponseDTO> notes =
                    noteService.filterByColor(color, getEmail());
            return ResponseEntity.ok(
                    new ApiResponse(200, "Notes filtered by color", notes));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(new ApiResponse(400, e.getMessage()));
        }
    }

    // SET REMINDER
    @PatchMapping("/{id}/reminder")
    public ResponseEntity<ApiResponse> setReminder(
            @PathVariable Long id,
            @RequestBody ReminderDTO dto) {
        try {
            NoteResponseDTO note =
                    noteService.setReminder(id, dto, getEmail());
            return ResponseEntity.ok(
                    new ApiResponse(200, "Reminder set successfully", note));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(new ApiResponse(400, e.getMessage()));
        }
    }

    // REMOVE REMINDER
    @DeleteMapping("/{id}/reminder")
    public ResponseEntity<ApiResponse> removeReminder(
            @PathVariable Long id) {
        try {
            NoteResponseDTO note =
                    noteService.removeReminder(id, getEmail());
            return ResponseEntity.ok(
                    new ApiResponse(200, "Reminder removed", note));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(new ApiResponse(400, e.getMessage()));
        }
    }
}