package com.notee.notes.controller;

import java.sql.Timestamp;
import java.util.Optional;
import java.util.UUID;

import com.notee.notes.model.User;
import com.notee.notes.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.notee.notes.model.Note;
import com.notee.notes.repository.NoteRepository;

@RestController
public class NoteController {

    private final NoteRepository noteRepository;
    private final UserRepository userRepository;

    @Autowired
    public NoteController(NoteRepository noteRepository, UserRepository userRepository) {
        this.noteRepository = noteRepository;
        this.userRepository = userRepository;
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
    public ResponseEntity<?> createNote(@RequestParam(name = "content", required = false) String content,
                                        @RequestParam(name = "username") String username,
                                        @RequestParam(name = "type") String type) {
        Note newNote = new Note();
        User user = userRepository.findByUsername(username);
        if (user == null) {
            return new ResponseEntity<Note>(HttpStatus.NOT_FOUND);
        }

        newNote.setContent(content);
        newNote.setUserId(user.getId());
        newNote.setUuid(UUID.randomUUID());
        newNote.setType(type);
        newNote.setCreatedAt(new Timestamp(System.currentTimeMillis()));

        return new ResponseEntity<>(noteRepository.save(newNote), HttpStatus.OK);
    }
}
