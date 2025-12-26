package com.example.hbdev.notes.controller;

import java.util.List;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.example.hbdev.notes.dto.NoteFilterDTO;
import com.example.hbdev.notes.dto.NoteRequestDTO;
import com.example.hbdev.notes.dto.NoteResponseDTO;
import com.example.hbdev.notes.service.NoteService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/notes")
@RequiredArgsConstructor
public class NoteController {

    private final NoteService noteService;

    // CREATE
    @PostMapping
    public ResponseEntity<NoteResponseDTO> create(@Valid @RequestBody NoteRequestDTO dto) {
        return new ResponseEntity<>(noteService.create(dto), HttpStatus.CREATED);
    }

    // READ ALL (with date filter)
    @GetMapping
    public ResponseEntity<List<NoteResponseDTO>> findAll(
            @RequestParam(required = false)
            @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
            String startDate,

            @RequestParam(required = false)
            @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
            String endDate
    ) {

        NoteFilterDTO filter = new NoteFilterDTO();
        if (startDate != null) {
            filter.setStartDate(java.time.LocalDateTime.parse(startDate));
        }
        if (endDate != null) {
            filter.setEndDate(java.time.LocalDateTime.parse(endDate));
        }

        return ResponseEntity.ok(noteService.findAll(filter));
    }

    // READ BY ID
    @GetMapping("/{id}")
    public ResponseEntity<NoteResponseDTO> findById(@PathVariable Long id) {
        return ResponseEntity.ok(noteService.findById(id));
    }

    // UPDATE
    @PutMapping("/{id}")
    public ResponseEntity<NoteResponseDTO> update(
            @PathVariable Long id,
            @Valid @RequestBody NoteRequestDTO dto
    ) {
        return ResponseEntity.ok(noteService.update(id, dto));
    }

    // DELETE
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        noteService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
