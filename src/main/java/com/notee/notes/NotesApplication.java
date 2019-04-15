package com.notee.notes;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class NotesApplication {
	public static final Logger logger = LoggerFactory.getLogger(NotesApplication.class);

	public static void main(String[] args) {
		SpringApplication.run(NotesApplication.class, args);
	}

}
