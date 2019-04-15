package com.notee.notes.repository;

import org.springframework.data.repository.CrudRepository;

import com.notee.notes.model.Note;

public interface NoteRepository extends CrudRepository<Note, Long> {
    Iterable<Note> findByUserId(Long userId);
}
