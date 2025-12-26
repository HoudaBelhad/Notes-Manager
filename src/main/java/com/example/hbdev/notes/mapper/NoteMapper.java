package com.example.hbdev.notes.mapper;

import java.util.List;

import org.mapstruct.Mapper;

import com.example.hbdev.notes.dto.NoteRequestDTO;
import com.example.hbdev.notes.dto.NoteResponseDTO;
import com.example.hbdev.notes.entity.Note;

@Mapper(componentModel = "spring")
public interface NoteMapper {
    // DTO -> Entity (CREATE / UPDATE)
    Note toEntity(NoteRequestDTO dto);

    // Entity -> DTO (READ)
    NoteResponseDTO toResponseDTO(Note note);

    // List<Entity> -> List<DTO>
    List<NoteResponseDTO> toResponseDTOList(List<Note> notes);
}
