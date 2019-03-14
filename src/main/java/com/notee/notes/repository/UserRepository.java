package com.notee.notes.repository;

import org.springframework.data.repository.CrudRepository;

import com.notee.notes.model.User;

public interface UserRepository extends CrudRepository<User, Long> {
    User findByUsername(String username);
}
