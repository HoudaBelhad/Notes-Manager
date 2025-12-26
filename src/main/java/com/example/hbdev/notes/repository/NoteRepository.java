package com.example.hbdev.notes.repository;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.hbdev.notes.entity.Note;

@Repository
public interface NoteRepository extends JpaRepository<Note, Long> {

    // Filter the notes between two dates
    List<Note> findByCreatedAtBetween(LocalDateTime startDate, LocalDateTime endDate);

    List<Note> findByCreatedAtAfter(LocalDateTime start);

    List<Note> findByCreatedAtBefore(LocalDateTime end);

}
