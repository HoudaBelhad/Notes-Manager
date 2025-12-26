package com.example.hbdev.notes.service.impl;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.stereotype.Service;

import com.example.hbdev.notes.dto.NoteFilterDTO;
import com.example.hbdev.notes.dto.NoteRequestDTO;
import com.example.hbdev.notes.dto.NoteResponseDTO;
import com.example.hbdev.notes.entity.Note;
import com.example.hbdev.notes.exception.NoteNotFoundException;
import com.example.hbdev.notes.mapper.NoteMapper;
import com.example.hbdev.notes.repository.NoteRepository;
import com.example.hbdev.notes.service.NoteService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class NoteServiceImpl implements NoteService {

    private final NoteRepository noteRepository;
    private final NoteMapper noteMapper;

    // CREATE
    @Override
    public NoteResponseDTO create(NoteRequestDTO dto) {
        Note note = noteMapper.toEntity(dto);
        note.setCreatedAt(LocalDateTime.now());
        return noteMapper.toResponseDTO(noteRepository.save(note));
    }

    // READ ALL (with date filter)
    @Override
    public List<NoteResponseDTO> findAll(NoteFilterDTO filter) {

        List<Note> notes;

        if (filter == null) {
            notes = noteRepository.findAll();
        }
        else if (filter.getStartDate() != null && filter.getEndDate() != null) {
            notes = noteRepository.findByCreatedAtBetween(
                    filter.getStartDate(),
                    filter.getEndDate()
            );
        }
        else if (filter.getStartDate() != null) {
            notes = noteRepository.findByCreatedAtAfter(filter.getStartDate());
        }
        else if (filter.getEndDate() != null) {
            notes = noteRepository.findByCreatedAtBefore(filter.getEndDate());
        }
        else {
            notes = noteRepository.findAll();
        }

        return noteMapper.toResponseDTOList(notes);
    }

    // READ BY ID
    @Override
    public NoteResponseDTO findById(Long id) {
        Note note = noteRepository.findById(id)
                .orElseThrow(() -> new NoteNotFoundException(id));

        return noteMapper.toResponseDTO(note);
    }

    // UPDATE
    @Override
    public NoteResponseDTO update(Long id, NoteRequestDTO dto) {
        Note note = noteRepository.findById(id)
                .orElseThrow(() -> new NoteNotFoundException(id));

        note.setTitle(dto.getTitle());
        note.setContent(dto.getContent());
        note.setUpdatedAt(LocalDateTime.now());

        return noteMapper.toResponseDTO(noteRepository.save(note));
    }

    // DELETE
    @Override
    public void delete(Long id) {
        Note note = noteRepository.findById(id)
                .orElseThrow(() -> new NoteNotFoundException(id));

        noteRepository.delete(note);
    }
}
