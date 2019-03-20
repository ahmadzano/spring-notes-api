package com.notee.notes.repository;

import com.notee.notes.model.File;
import org.springframework.data.repository.CrudRepository;

public interface FileRepository extends CrudRepository<File, Long> {
}
