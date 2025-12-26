package com.example.hbdev.notes.exception;

public class NoteNotFoundException extends RuntimeException {
//when user do a findById and the note does not exist.
    public NoteNotFoundException(Long id) {
        super("Note not found with id: " + id);
    }
}
