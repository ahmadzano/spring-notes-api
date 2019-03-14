
CREATE TABLE user (
	id BIGINT NOT NULL AUTO_INCREMENT,
	username varchar(100) NOT NULL UNIQUE,
	email varchar(100) NULL,
	is_active smallint not null default 0,
 	created_at datetime not null default current_timestamp,
	CONSTRAINT user_pk PRIMARY KEY (id)
);


CREATE TABLE note (
  id bigint(20) NOT NULL AUTO_INCREMENT,
  content varchar(999) DEFAULT NULL,
  type varchar(20) null,
  user_id bigint(20) NOT NULL,
  created_at datetime not null default current_timestamp,
  uuid varchar(50) null,
  PRIMARY KEY (id),
  CONSTRAINT note_note_fk FOREIGN KEY (user_id) REFERENCES note (id)
);
