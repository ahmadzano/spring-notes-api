package com.notee.notes.controller;

import java.sql.Timestamp;
import java.util.Optional;
import java.util.UUID;

import com.notee.notes.NotesApplication;
import com.notee.notes.model.Payload;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.notee.notes.model.Note;
import com.notee.notes.repository.NoteRepository;

@RestController
@CrossOrigin
public class NoteController {

    private final NoteRepository noteRepository;

    @Autowired
    public NoteController(NoteRepository noteRepository) {
        this.noteRepository = noteRepository;
    }

    @RequestMapping(value = "/notes/{id}", method = RequestMethod.GET)
    public ResponseEntity<?> getNote(@PathVariable("id") Long noteId) {
        Optional<Note> note = noteRepository.findById(noteId);
        if (note.isPresent()) {
            return new ResponseEntity<>(note.get(), HttpStatus.OK);
        }

        return new ResponseEntity<Note>(HttpStatus.NOT_FOUND);
    }

    @RequestMapping(path = "/notes", method = RequestMethod.POST)
    public ResponseEntity<?> createNote(@RequestBody Payload payload) {
        Note newNote = new Note();
        try {
            newNote.setContent(payload.getContent());
            newNote.setUserId(payload.getUserId());
            newNote.setUuid(UUID.randomUUID());
            newNote.setType(payload.getType());
            newNote.setCreatedAt(new Timestamp(System.currentTimeMillis()));

            return new ResponseEntity<>(noteRepository.save(newNote), HttpStatus.OK);

        } catch (Exception exception) {
            NotesApplication.logger.error(exception.getMessage());
            return new ResponseEntity<>(newNote, HttpStatus.BAD_REQUEST);
        }
    }

    @RequestMapping(path = "/notes", method = RequestMethod.GET)
    public ResponseEntity<?> createNote() {
        return new ResponseEntity<>(noteRepository.findAll(), HttpStatus.OK);
    }
}
