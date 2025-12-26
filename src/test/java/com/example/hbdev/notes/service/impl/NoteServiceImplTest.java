package com.example.hbdev.notes.service.impl;

import com.example.hbdev.notes.dto.NoteFilterDTO;
import com.example.hbdev.notes.dto.NoteRequestDTO;
import com.example.hbdev.notes.dto.NoteResponseDTO;
import com.example.hbdev.notes.entity.Note;
import com.example.hbdev.notes.exception.NoteNotFoundException;
import com.example.hbdev.notes.mapper.NoteMapper;
import com.example.hbdev.notes.repository.NoteRepository;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class NoteServiceImplTest {

    @Mock
    private NoteRepository noteRepository;

    @Mock
    private NoteMapper noteMapper;

    @InjectMocks
    private NoteServiceImpl noteService;

    private Note note;
    private NoteRequestDTO requestDTO;
    private NoteResponseDTO responseDTO;

    @BeforeEach
    void setup() {
        note = new Note();
        note.setId(1L);
        note.setTitle("Test title");
        note.setContent("Test content");
        note.setCreatedAt(LocalDateTime.now());

        requestDTO = new NoteRequestDTO();
        requestDTO.setTitle("Test title");
        requestDTO.setContent("Test content");

        responseDTO = new NoteResponseDTO();
        responseDTO.setId(1L);
        responseDTO.setTitle("Test title");
        responseDTO.setContent("Test content");
    }

    // ================= CREATE =================

    @Test
    void shouldCreateNote() {
        when(noteMapper.toEntity(requestDTO)).thenReturn(note);
        when(noteRepository.save(any(Note.class))).thenReturn(note);
        when(noteMapper.toResponseDTO(note)).thenReturn(responseDTO);

        NoteResponseDTO result = noteService.create(requestDTO);

        assertNotNull(result);
        assertEquals("Test title", result.getTitle());
        verify(noteRepository).save(any(Note.class));
    }

    // ================= FIND ALL =================

    @Test
    void shouldFindAllNotesWithoutFilter() {
        when(noteRepository.findAll()).thenReturn(List.of(note));
        when(noteMapper.toResponseDTOList(anyList())).thenReturn(List.of(responseDTO));

        List<NoteResponseDTO> result = noteService.findAll(null);

        assertEquals(1, result.size());
        verify(noteRepository).findAll();
    }

    @Test
    void shouldFindNotesWithDateBetween() {
        NoteFilterDTO filter = new NoteFilterDTO();
        filter.setStartDate(LocalDateTime.now().minusDays(1));
        filter.setEndDate(LocalDateTime.now());

        when(noteRepository.findByCreatedAtBetween(
                filter.getStartDate(), filter.getEndDate()
        )).thenReturn(List.of(note));

        when(noteMapper.toResponseDTOList(anyList())).thenReturn(List.of(responseDTO));

        List<NoteResponseDTO> result = noteService.findAll(filter);

        assertEquals(1, result.size());
        verify(noteRepository).findByCreatedAtBetween(
                filter.getStartDate(), filter.getEndDate()
        );
    }

    // ================= FIND BY ID =================

    @Test
    void shouldFindNoteById() {
        when(noteRepository.findById(1L)).thenReturn(Optional.of(note));
        when(noteMapper.toResponseDTO(note)).thenReturn(responseDTO);

        NoteResponseDTO result = noteService.findById(1L);

        assertNotNull(result);
        assertEquals(1L, result.getId());
    }

    @Test
    void shouldThrowExceptionWhenNoteNotFound() {
        when(noteRepository.findById(99L)).thenReturn(Optional.empty());

        assertThrows(NoteNotFoundException.class,
                () -> noteService.findById(99L));
    }

    // ================= UPDATE =================

    @Test
    void shouldUpdateNote() {
        when(noteRepository.findById(1L)).thenReturn(Optional.of(note));
        when(noteRepository.save(any(Note.class))).thenReturn(note);
        when(noteMapper.toResponseDTO(note)).thenReturn(responseDTO);

        NoteResponseDTO result = noteService.update(1L, requestDTO);

        assertNotNull(result);
        verify(noteRepository).save(note);
    }

    // ================= DELETE =================

    @Test
    void shouldDeleteNote() {
        when(noteRepository.findById(1L)).thenReturn(Optional.of(note));
        doNothing().when(noteRepository).delete(note);

        assertDoesNotThrow(() -> noteService.delete(1L));
        verify(noteRepository).delete(note);
    }

    @Test
    void shouldThrowExceptionWhenDeleteNoteNotFound() {
        when(noteRepository.findById(1L)).thenReturn(Optional.empty());

        assertThrows(NoteNotFoundException.class,
                () -> noteService.delete(1L));
    }
}
