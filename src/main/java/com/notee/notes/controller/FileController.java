package com.notee.notes.controller;

import com.notee.notes.model.File;
import com.notee.notes.repository.FileRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
@RequestMapping(path = "/files")
public class FileController {
    private final FileRepository fileRepository;

    @Autowired
    public FileController(FileRepository fileRepository) {
        this.fileRepository = fileRepository;
    }

    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity<?> index(@PathVariable(name = "id", required = false) Long fileId) {
        if (null != fileId) {
            Optional<File> file = fileRepository.findById(fileId);
            if (file.isPresent()) {
                return new ResponseEntity<>(file.get(), HttpStatus.NOT_FOUND);
            }
        }

        return new ResponseEntity<>(fileRepository.findAll(), HttpStatus.NOT_FOUND);
    }
}
