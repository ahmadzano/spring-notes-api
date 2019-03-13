package com.notee.notes.controller;

import java.util.ArrayList;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.notee.notes.model.Note;
import com.notee.notes.repository.NoteRepository;

@RestController
public class NoteController {

    @Autowired
    private NoteRepository noteRepository;

    @RequestMapping(path = "/notes", method = RequestMethod.GET)
    public Iterable<Note> getNote(@RequestParam(name = "id", required = false) Long id) {
        ArrayList<Note> notes = new ArrayList<Note>();
        if (id != null) {
            Optional<Note> note = noteRepository.findById(id);
            if (note.isPresent()) {
                notes.add(note.get());
            }
        } else {
            notes = (ArrayList<Note>) noteRepository.findAll();
        }

        return notes;
    }
}
