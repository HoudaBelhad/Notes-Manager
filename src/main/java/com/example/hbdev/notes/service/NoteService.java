package com.example.hbdev.notes.service;

import java.util.List;

import com.example.hbdev.notes.dto.NoteFilterDTO;
import com.example.hbdev.notes.dto.NoteRequestDTO;
import com.example.hbdev.notes.dto.NoteResponseDTO;

public interface NoteService {

    NoteResponseDTO create(NoteRequestDTO dto);

    List<NoteResponseDTO> findAll(NoteFilterDTO filter);

    NoteResponseDTO findById(Long id);

    NoteResponseDTO update(Long id, NoteRequestDTO dto);

    void delete(Long id);
}
