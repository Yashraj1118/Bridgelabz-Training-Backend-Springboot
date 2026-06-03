package com.fundoonotes.fundoo_notes.controller;
import com.fundoonotes.fundoo_notes.service.LabelService;
import com.fundoonotes.fundoo_notes.dto.ApiResponse;
import com.fundoonotes.fundoo_notes.dto.LabelDTO;
import com.fundoonotes.fundoo_notes.dto.LabelResponseDTO;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/labels")
public class LabelController {

    @Autowired
    private LabelService labelService;

    private String getEmail() {
        return SecurityContextHolder.getContext()
                .getAuthentication().getName();
    }

    // CREATE LABEL
    @PostMapping
    public ResponseEntity<ApiResponse> createLabel(
            @Valid @RequestBody LabelDTO dto) {
        try {
            LabelResponseDTO label =
                    labelService.createLabel(dto, getEmail());
            return ResponseEntity.status(HttpStatus.CREATED)
                    .body(new ApiResponse(201, "Label created successfully", label));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(new ApiResponse(400, e.getMessage()));
        }
    }

    // GET ALL LABELS
    @GetMapping
    public ResponseEntity<ApiResponse> getAllLabels() {
        try {
            List<LabelResponseDTO> labels =
                    labelService.getAllLabels(getEmail());
            return ResponseEntity.ok(
                    new ApiResponse(200, "Labels fetched successfully", labels));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(new ApiResponse(400, e.getMessage()));
        }
    }

    // UPDATE LABEL
    @PutMapping("/{id}")
    public ResponseEntity<ApiResponse> updateLabel(
            @PathVariable Long id,
            @Valid @RequestBody LabelDTO dto) {
        try {
            LabelResponseDTO label =
                    labelService.updateLabel(id, dto, getEmail());
            return ResponseEntity.ok(
                    new ApiResponse(200, "Label updated successfully", label));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(new ApiResponse(400, e.getMessage()));
        }
    }

    // DELETE LABEL
    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse> deleteLabel(
            @PathVariable Long id) {
        try {
            String message = labelService.deleteLabel(id, getEmail());
            return ResponseEntity.ok(new ApiResponse(200, message));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(new ApiResponse(400, e.getMessage()));
        }
    }

    // ADD LABEL TO NOTE
    @PostMapping("/{labelId}/notes/{noteId}")
    public ResponseEntity<ApiResponse> addLabelToNote(
            @PathVariable Long labelId,
            @PathVariable Long noteId) {
        try {
            String message =
                    labelService.addLabelToNote(noteId, labelId, getEmail());
            return ResponseEntity.ok(new ApiResponse(200, message));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(new ApiResponse(400, e.getMessage()));
        }
    }

    // REMOVE LABEL FROM NOTE
    @DeleteMapping("/{labelId}/notes/{noteId}")
    public ResponseEntity<ApiResponse> removeLabelFromNote(
            @PathVariable Long labelId,
            @PathVariable Long noteId) {
        try {
            String message =
                    labelService.removeLabelFromNote(noteId, labelId, getEmail());
            return ResponseEntity.ok(new ApiResponse(200, message));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(new ApiResponse(400, e.getMessage()));
        }
    }

    // GET ALL NOTES BY LABEL
    @GetMapping("/{labelId}/notes")
    public ResponseEntity<ApiResponse> getNotesByLabel(
            @PathVariable Long labelId) {
        try {
            return ResponseEntity.ok(
                    new ApiResponse(200, "Notes fetched by label",
                            labelService.getNotesByLabel(labelId, getEmail())));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(new ApiResponse(400, e.getMessage()));
        }
    }
}