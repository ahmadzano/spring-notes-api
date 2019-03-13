package com.notee.notes.controller;

import java.util.ArrayList;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.notee.notes.model.User;
import com.notee.notes.repository.UserRepository;

@RestController
public class UserController {

    @Autowired
    UserRepository userRepository;

    @RequestMapping(path = "/users", method = RequestMethod.GET)
    public Iterable<User> getUser(@RequestParam(name = "id", required = false) Long id) {
        ArrayList<User> users = new ArrayList<User>();

        if (id != null) {
            Optional<User> user = userRepository.findById(id);
            if (user.isPresent()) {
                users.add(user.get());
            }
        } else {
            users = (ArrayList<User>) userRepository.findAll();
        }

        return users;
    }

    @RequestMapping(path = "/users", method = RequestMethod.POST)
    public User createUser(@RequestParam(name = "username") String username,
                                     @RequestParam(name = "email", required = false) String email) {
        User newUser = new User(username, email);

        return userRepository.save(newUser);
    }
}
