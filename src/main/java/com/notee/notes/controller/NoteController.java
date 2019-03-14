package com.notee.notes.controller;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Optional;
import java.util.UUID;

import com.notee.notes.model.User;
import com.notee.notes.repository.UserRepository;
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
    @Autowired
    private UserRepository userRepository;

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

    @RequestMapping(path = "notes", method = RequestMethod.POST)
    public Note createNote(@RequestParam(name = "content", required = false) String content, @RequestParam(name = "username") String username) {
        Note newNote = new Note();
        User user = userRepository.findByUsername(username);
        if (user == null) {
            return newNote;
        }

        newNote.setContent(content);
        newNote.setUserId(user.getId());
        UUID uuid = UUID.randomUUID();
        newNote.setUuid(uuid);
        newNote.setCreatedAt(new Timestamp(System.currentTimeMillis()));

        return noteRepository.save(newNote);
    }
}
