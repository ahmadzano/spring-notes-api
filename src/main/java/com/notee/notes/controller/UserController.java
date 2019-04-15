package com.notee.notes.controller;

import java.util.ArrayList;
import java.util.Optional;

import com.notee.notes.NotesApplication;
import com.notee.notes.repository.NoteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;

import com.notee.notes.model.User;
import com.notee.notes.repository.UserRepository;

@RestController
public class UserController {

    final private
    UserRepository userRepository;
    private final NoteRepository noteRepository;

    @Autowired
    public UserController(UserRepository userRepository, NoteRepository noteRepository) {
        this.userRepository = userRepository;
        this.noteRepository = noteRepository;
    }

    @RequestMapping(value = {"/users", "/users/{userId}"}, method = RequestMethod.GET)
    public ResponseEntity<?> getUser(@PathVariable(value = "userId", required = false) Long userId) {
        ArrayList<User> users = new ArrayList<>();

        try {
            if (userId != null) {
                Optional<User> user = userRepository.findById(userId);
                if (user.isPresent()) {
                    users.add(user.get());
                }
            } else {
                users = (ArrayList<User>) userRepository.findAll();
            }

            return new ResponseEntity<>(users, HttpStatus.OK);
        } catch (Exception exception) {
            NotesApplication.logger.info(exception.getMessage());
            return new ResponseEntity<>(users, HttpStatus.BAD_REQUEST);
        }
    }

    @RequestMapping(value = "/users/{userId}/notes", method = RequestMethod.GET)
    public ResponseEntity<?> getUserNotes(@PathVariable Long userId) {
        Optional<User> user = userRepository.findById(userId);
        if (user.isPresent()) {
            return new ResponseEntity<>(noteRepository.findByUserId(user.get().getId()), HttpStatus.OK);
        }

        NotesApplication.logger.warn("User with ID " + userId + " not found");
        return new ResponseEntity<>(new String[]{"user not found"}, HttpStatus.NOT_FOUND);
    }

    @RequestMapping(path = "/users", method = RequestMethod.POST)
    public ResponseEntity<?> createUser(@RequestParam(name = "username") String username,
                                        @RequestParam(name = "email", required = false) String email) {
        User newUser = new User(username, email);

        return new ResponseEntity<>(userRepository.save(newUser), HttpStatus.CREATED);
    }
}
