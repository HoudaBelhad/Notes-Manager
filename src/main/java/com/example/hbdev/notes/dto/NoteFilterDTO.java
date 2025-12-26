package com.example.hbdev.notes.dto;

import java.time.LocalDateTime;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class NoteFilterDTO { //To filter by date
    private LocalDateTime StartDate;
    private LocalDateTime endDate;
}
