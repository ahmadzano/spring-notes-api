
CREATE TABLE user (
	id BIGINT NOT NULL AUTO_INCREMENT,
	username varchar(100) NOT NULL,
	email varchar(100) NULL,
	CONSTRAINT user_pk PRIMARY KEY (id)
);


CREATE TABLE note (
  id bigint(20) NOT NULL AUTO_INCREMENT,
  content varchar(999) DEFAULT NULL,
  user_id bigint(20) NOT NULL,
  PRIMARY KEY (id),
  CONSTRAINT note_note_fk FOREIGN KEY (user_id) REFERENCES note (id)
);
